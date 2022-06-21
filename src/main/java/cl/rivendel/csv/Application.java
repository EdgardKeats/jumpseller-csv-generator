package cl.rivendel.csv;

import cl.rivendel.csv.helper.CSVHelper;
import cl.rivendel.csv.helper.CardImageHelper;
import cl.rivendel.csv.helper.FtpHelper;
import cl.rivendel.csv.helper.ScryfallHelper;
import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@EnableFeignClients
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CardImageHelper cardImageHelper;
    @Autowired
    private ScryfallHelper scryfallHelper;
    @Autowired
    private CSVHelper csvHelper;
    @Autowired
    private FtpHelper ftpHelper;

    @Value("${csvGenProperty.testRun:false}")
    private boolean testRun;

    @Value("${csvGenProperty.createImage:false}")
    private boolean createImages;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        if (testRun) {
            testGenerateCSV();
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Cargando listado de cartas");
            Map<String, List<Set>> mapOfSetLists = scryfallHelper.getSetsDividedByType();

            int contador = 1;
            String setType = "";
            Map<Integer, String> tempMap = new HashMap<>();
            for (Map.Entry<String, List<Set>> entry : mapOfSetLists.entrySet()){
                System.out.print(contador+") ");
                System.out.println(entry.getKey());
                tempMap.put(contador, entry.getKey());
                contador++;
            }
            setType = tempMap.get(menuSelector(scanner, tempMap));

            List<Card> cardsList = showGetCardListMenu(scanner, mapOfSetLists.get(setType));

            if (!cardsList.isEmpty()) {
                Map<String, String> cardNames;
                System.out.println("getting " + cardsList.size() + " cards images");
                cardNames = scryfallHelper.getOracleCardsImages(cardsList);
                System.out.println("processing card images");
                cardImageHelper.createJumpsellerImages(cardNames);
                System.out.println("uploading jumpseller images to image server");
                ftpHelper.uploadImages(cardNames);
                System.out.println("updating csv file with card images urls");
                csvHelper.updateImagesUris(cardsList, cardNames);
                System.out.println("creating csv models");
                List<CSVModel> listCSVModel = csvHelper.cardListToCsvModelList(cardsList);
                System.out.println("creating jumpseller csv file");
                String nowName = LocalDateTime.now().toString();
                csvHelper.generateJumpSellerCSV(listCSVModel, ".\\"+nowName+".csv");
            }
            System.out.println("Fin de generacion! :D");

        }
    }

    private int menuSelector(Scanner scanner, Map<Integer, String> menuValues){
        boolean continueLoop = true;
        while (continueLoop){
            System.out.print("Pick a valid number: ");
            String value = scanner.nextLine();
            if(StringUtils.isNotEmpty(value)){
                try {
                    int validNumber = Integer.parseInt(value);
                    if((validNumber > menuValues.size()) || (validNumber<=0)){
                        System.out.println("\""+value+"\" was not a valid number, try again...");
                    } else {
                        return validNumber;
                    }
                } catch (NumberFormatException numberFormatException){
                    System.out.println("\""+value+"\" was not a valid number, try again...");
                }
            }
        }
        return 0;
    }

    private List<Card> showGetCardListMenu(Scanner scanner, List<Set> setList){
        int contador = 1;
        String set = "";
        Map<Integer, String> tempMap = new HashMap<>();
        String baseJsonUrl = scryfallHelper.getOracleCardsURL();
        for (Set entry :setList) {
            System.out.println(contador+") Key : " + entry.getCode() + ", Value : " + entry.getName());
            tempMap.put(contador, entry.getCode());
            contador++;
        }

        set = tempMap.get(menuSelector(scanner, tempMap));

        return scryfallHelper.getSetCards(baseJsonUrl, set);
    }

    private void testGenerateCSV() {
        Map<String, String> cardNames;
        log.info("getting sets");
        Map<String, String> setMap = scryfallHelper.getSets();

        log.info("getting base json");
        String baseJsonUrl = scryfallHelper.getOracleCardsURL();
        String firstFoundSet = "snc";

        log.info("getting cards from set: {}", setMap.get(firstFoundSet));
        List<Card> cardsList = scryfallHelper.testGetSetCards(baseJsonUrl, firstFoundSet);

        if (!cardsList.isEmpty()) {
            if (createImages) {
                log.info("getting {} cards images", cardsList.size());
                cardNames = scryfallHelper.getOracleCardsImages(cardsList);

                log.info("processing card images");
                cardImageHelper.createJumpsellerImages(cardNames);

                log.info("uploading jumpseller images to image server");
                ftpHelper.uploadImages(cardNames);

                log.info("updating csv file with card images urls");
                csvHelper.updateImagesUris(cardsList, cardNames);
            }

            log.info("creating csv models");
            List<CSVModel> listCSVModel = csvHelper.cardListToCsvModelList(cardsList);

            log.info("creating jumpseller csv file");
            csvHelper.generateJumpSellerCSV(listCSVModel, "C:\\SimpleSolution\\SNC.csv");
        }
    }
}
