package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.scryfall.BulkData;
import cl.rivendel.csv.model.scryfall.BulkMetaData;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import cl.rivendel.csv.service.ScryfallClient;
import cl.rivendel.csv.utils.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;


@Component
public class ScryfallHelper {

    private static final Logger log = LoggerFactory.getLogger(ScryfallHelper.class);
    private static final String JSON_GZ_FILE_NAME = "Cards.json.gz";
    private static final String JSON_FILE_NAME = "Cards.json";
    private final ScryfallClient scryfallClient;

    public ScryfallHelper(@Autowired ScryfallClient scryfallClient){
        this.scryfallClient = scryfallClient;
    }

    public String getAllCardsURL(){
        log.info("Attempting to get ALL cards");
        BulkMetaData bulkMetadata = scryfallClient.getBulkData();
        for (BulkData bulkData : bulkMetadata.getData()){
            log.info(bulkData.toString());
        }
        return bulkMetadata.getData().stream().filter(bulk -> bulk.getType().equalsIgnoreCase(Constants.ALL_CARDS)).findFirst().map(BulkData::getJsonlDownloadUri).get();
    }

    public List<Card> getCardsFromJsonURL(String jsonUrl, String set) throws IOException {
        log.info("Attempting to download cards json file");
        String compressedJsonFileName = saveJSONFile(jsonUrl);
        String jsonFileName = decompressedGZFile(compressedJsonFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        List<Card> cards = new ArrayList<>();
        File jsonFile = new File(jsonFileName);
        try (LineIterator it = FileUtils.lineIterator(jsonFile, "UTF-8")){
            while (it.hasNext()) {
                try{
                    String line = it.nextLine();
                    Card card = objectMapper.readValue(line, new TypeReference<Card>() {});
                    if(card.getSet().trim().equalsIgnoreCase(set) && (card.getLang().equalsIgnoreCase(Constants.ENGLISH))) cards.add(card);
                } catch (Exception e){
                    log.error("Error during file line to card object parsing");
                    log.error(e.getMessage());
                }
            }
        } catch (IOException e){
            log.error("Error creating the card list");
            log.error(e.getMessage());
        } 
        return cards;
    }

    private String decompressedGZFile(String compressedJsonFileName) {
        // Automatically manages and closes streams to prevent memory leaks
        try (FileInputStream fis = new FileInputStream(compressedJsonFileName);
             GZIPInputStream gzis = new GZIPInputStream(fis);
             FileOutputStream fos = new FileOutputStream(JSON_FILE_NAME)) {

            byte[] buffer = new byte[1024];
            int length;

            // Read from the GZIP stream and write to the output file
            while ((length = gzis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            log.info("File uncompressed successfully!");
            return JSON_FILE_NAME;
        } catch (IOException e) {
            log.error("An error occurred during decompression: " + e.getMessage());
            return "";
        }
    }

    public String saveJSONFile(String jsonUrl) throws IOException {
        File file = new File(JSON_GZ_FILE_NAME);
        if(!file.exists()){
            log.info("Creating new json file with name {}", JSON_GZ_FILE_NAME);
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(jsonUrl).openStream());
            try(FileOutputStream fileOutputStream = new FileOutputStream(JSON_GZ_FILE_NAME)){
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
            log.info("File downloaded");
        } else {
            log.info("File {} already exists! ", JSON_GZ_FILE_NAME);
        }
        return JSON_GZ_FILE_NAME;
    }

    private void deleteBaseJsonFile() {
        log.info("Attempting to erase existing base file");
        File file = new File(JSON_FILE_NAME);
        if (file.delete())
            log.info("Base file deleted!");
        else
            log.info("The base file has not been deleted");
    }

    public Map<String, String> getOracleCardsImages(List<Card> cardList) throws ExecutionException, InterruptedException {
        log.info("CardList size: {}", cardList.size());
        Map<String, String> downloadedCardsNames = new HashMap<>();
        new File(Constants.ORACLE_CARDS_FOLDER).mkdir();
        try(ForkJoinPool customPool = new ForkJoinPool(5);) {
            // Submit the stream execution into the custom pool context
            customPool.submit(() ->
                    cardList.parallelStream().forEach(
                            card -> {
                                if (card.getImageUris() != null) {
                                    downloadImage(downloadedCardsNames, card, card.getImageUris().get(Constants.PNG));
                                } else {
                                    log.info("The card {} might be a double-faced card, downloading only one face", card.getName());
                                    downloadImage(downloadedCardsNames, card, card.getCardFaces().get(0).getImageUris().get(Constants.PNG));
                                }
                            }
                    )

            ).get(); // Waits for completion

            log.info("Downloaded cardnames size: {}", downloadedCardsNames.size());

            return downloadedCardsNames;
        }

    }

    private void downloadImage(Map<String, String> downloadedCardsNames, Card card, String imageURL){
        try {
            log.trace("Image URL = {}", imageURL);
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(imageURL).openStream());
            String fileName = createCardName(card);
            try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
            downloadedCardsNames.put(card.getCollectorNumber(), fileName);
        } catch (IOException ioException) {
            log.error("IoException while downloading card image", ioException);
        }
    }

    /**
     * Will return the card name with the following format
     * oracleCards/nameWithoutSlashes-setName-collectorNumber-languages.png
     * @param card
     * @return
     */
    private String createCardName(Card card) {
        return Constants.ORACLE_CARDS_FOLDER +
                card.getName().replace(Constants.FORWARD_SLASH, Constants.HYPHEN).replace(Constants.COLON, Constants.HYPHEN) +
                Constants.HYPHEN +
                card.getSet() +
                Constants.HYPHEN  +
                card.getCollectorNumber() +
                Constants.HYPHEN +
                card.getLang() +
                Constants.PNG_EXTENSION;
    }

    public Map<String, List<Set>> getSetsDividedByType() {
        return scryfallClient.getAllSets().getData().stream().collect(Collectors.groupingBy(Set::getSetType));
    }


}
