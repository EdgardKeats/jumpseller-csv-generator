package cl.rivendel.csv;

import cl.rivendel.csv.helper.CSVHelper;
import cl.rivendel.csv.helper.CardImageHelper;
import cl.rivendel.csv.helper.FtpHelper;
import cl.rivendel.csv.helper.ScryfallHelper;
import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
    public void run(String... args) throws Exception {
        if (testRun) {
            testGenerateCSV();
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Cargando listado de cartas");
            Map<String, String> setMap = scryfallHelper.getSets();
            String baseJsonUrl = scryfallHelper.getOracleCardsURL();
            int contador = 1;
            boolean notContinue = true;
            boolean skip = false;
            String set = "";
            for (Map.Entry<String, String> entry : setMap.entrySet()) {
                contador++;
                System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());

                if(contador == 10) {
                    System.out.println("Ingrese set o ingrese 1 para continuar");
                    set = scanner.nextLine();
                    while(notContinue) {
                        if (set.equalsIgnoreCase("1")) {
                            System.out.println("continuando...");
                            set = "";
                            contador = 1;
                            notContinue = false;
                        } else if (set.length() == 3) {
                            skip = true;
                            notContinue = false;
                        } else {
                            System.out.println("Por favor, ingrese un valor válido");
                            notContinue = false;
                        }
                    }
                    if (skip)
                        break;
                }
            }

            List<Card> cardsList = scryfallHelper.getSetCards(baseJsonUrl, set);

            if (!cardsList.isEmpty()) {
                Map<String, String> cardNames;
                log.info("getting " + cardsList.size() + " cards images");
                cardNames = scryfallHelper.getOracleCardsImages(cardsList);
                log.info("processing card images");
                cardImageHelper.createJumpsellerImages(cardNames);
                log.info("uploading jumpseller images to image server");
                ftpHelper.uploadImages(cardNames);
                log.info("updating csv file with card images urls");
                csvHelper.updateImagesUris(cardsList, cardNames);
                log.info("creating csv models");
                List<CSVModel> listCSVModel = csvHelper.cardListToCsvModelList(cardsList);
                log.info("creating jumpseller csv file");
                csvHelper.generateJumpSellerCSV(listCSVModel, "C:\\SimpleSolution\\SNC.csv");
            }
            System.out.println("Fin de generacion! :D");


            //TODO: Instanciar JavaFX
        }
    }

    public void testGenerateCSV() {
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
