package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.LogicDTO;
import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.service.client.ImgBBFtpHelper;
import cl.rivendel.csv.utils.Constants;
import cl.rivendel.csv.utils.DownloadUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@Component
public class LogicHelper {
    @Autowired
    private final ScryfallHelper scryfallHelper;
    private final CardImageHelper cardImageHelper;
    private final ImgBBFtpHelper ftpHelper;
    private final CSVHelper csvHelper;
    private final MTGJsonHelper mtgJsonHelper;
    private final DownloadUtils downloadUtils;

    @Autowired
    public LogicHelper(ScryfallHelper scryfallHelper, CardImageHelper cardImageHelper, ImgBBFtpHelper ftpHelper, CSVHelper csvHelper, MTGJsonHelper mtgJsonHelper, DownloadUtils downloadUtils) {
        this.scryfallHelper = scryfallHelper;
        this.cardImageHelper = cardImageHelper;
        this.ftpHelper = ftpHelper;
        this.csvHelper = csvHelper;
        this.mtgJsonHelper = mtgJsonHelper;
        this.downloadUtils = downloadUtils;
    }

    private static final Logger log = LoggerFactory.getLogger(LogicHelper.class);

    public void generateCSV(LogicDTO logicDTO) throws IOException, ExecutionException, InterruptedException {
        log.info("Starting... please wait a few minutes");
        List<Card> cardsList = scryfallHelper.getCardsFromJsonURL(scryfallHelper.getAllCardsURL(), logicDTO.getSetValue());
        if (!cardsList.isEmpty()) {
            Map<String, String> cardNames;
            if(logicDTO.isUploadImages()){
                log.info("Generating images...");
                log.info("getting {} cards images", cardsList.size());
                cardNames = scryfallHelper.getOracleCardsImages(cardsList);
                log.info("processing card images");
                cardImageHelper.createJumpsellerImages(cardNames);
                log.info("uploading jumpseller images to image server");
                ftpHelper.uploadImages(cardNames, logicDTO.getImbBBKey());
                log.info("updating csv file with card images urls");
                csvHelper.updateImagesUris(cardsList, cardNames);
            }
            if(logicDTO.isAddPrices()){
                List<cl.rivendel.csv.model.mtgjson.Card> cards = mtgJsonHelper.getCardsFromSetJSON(logicDTO.getSetValue());
                log.info("Got {} from MTG Json DB", cards.size());
                mtgJsonHelper.replaceUUID(cardsList, cards);
                log.info("Retrieving prices");
                Map<String, Float> cardPrices = mtgJsonHelper.getCardPrices();
                log.info("Got {} cards with prices from MTG Json", cardPrices.size());
                log.info("Merging card info with prices");
                mtgJsonHelper.mergePrices(cardsList, cardPrices, Integer.parseInt(logicDTO.getDolarValue()));
                log.info("Cantidad de cartas con precio recuperadas: {}", cards.size());
            }
            log.info("Creating csv models");
            List<CSVModel> listCSVModel = csvHelper.cardListToCsvModelList(cardsList);
            log.info("Creating jumpseller csv file");
            csvHelper.generateJumpSellerCSV(listCSVModel, "." + File.separator + cardsList.getFirst().getSet() + "-list.csv");
            log.info("File name: {}-list.csv", logicDTO.getSetValue());
        }
        cleanupFiles();
        log.info("CSV File generation finished! :D");
    }

    private void cleanupFiles() {
        Path dirPath = Paths.get(Constants.ORACLE_CARDS_FOLDER);

        // Try-with-resources ensures the directory stream gets closed properly
        try (Stream<Path> stream = Files.list(dirPath)) {
            stream.filter(Files::isRegularFile) // Select only files, ignore folders
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            log.error("Could not delete file: {}", path);
                        }
                    });
            log.info("images inside oracleCards deleted");
        } catch (IOException e) {
            log.error("Error during cleanup while deleting images");
        }
        log.info("Was image folder deleted?: {}", new File(Constants.ORACLE_CARDS_FOLDER).delete());
    }

    public void downloadCSV(){
        try{
            log.info("Updating base file");
            downloadUtils.saveJSONFile(scryfallHelper.getAllCardsURL());
            log.info("File downloaded successfully");
        } catch (Exception ex){
            log.info("Something happened while downloading the new file");
            log.info(ex.getMessage());
        }
    }
}
