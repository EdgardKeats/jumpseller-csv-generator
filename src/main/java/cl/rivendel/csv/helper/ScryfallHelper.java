package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.scryfall.BulkData;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import cl.rivendel.csv.service.ScryfallClient;
import cl.rivendel.csv.utils.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ScryfallHelper {

    private static final Logger log = LoggerFactory.getLogger(ScryfallHelper.class);
    private static final String JSON_FILE_NAME = "Cards.json";

    private final ScryfallClient scryfallClient;
    private final boolean skipHighRes;

    public ScryfallHelper(@Autowired ScryfallClient scryfallClient, @Value("${csvGenProperty.skipHighResImage:true}") boolean skipHighRes){
        this.scryfallClient = scryfallClient;
        this.skipHighRes = skipHighRes;
    }

    private String getCardsURL(String bulkDataType){
        return scryfallClient.getBulkData().getData().stream().filter(bulk->bulk.getType().equalsIgnoreCase(bulkDataType)).findFirst().map(BulkData::getDownloadUri).get();
    }

    public String getAllCardsURL(){
        log.info("Attempting to get ALL cards");
        return getCardsURL(Constants.ALL_CARDS);
    }
    public List<Card> getSetCards(String jsonUrl, String set) {
    log.info("attempting to download cards file from {}, for the set {}", jsonUrl, set);
        try {
            return getCardsFromJsonURL(jsonUrl, set);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Card> getCardsFromJsonURL(String jsonUrl, String set) throws IOException {
        log.info("Attempting to download cards json file");
        String jsonFileName = saveJSONFile(jsonUrl);
        ObjectMapper objectMapper = new ObjectMapper();
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

    public String saveJSONFile(String jsonUrl) throws IOException {
        File file = new File(JSON_FILE_NAME);
        if(!file.exists()){
            log.info("Creating new json file with name {}", JSON_FILE_NAME);
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(jsonUrl).openStream());
            try(FileOutputStream fileOutputStream = new FileOutputStream(JSON_FILE_NAME)){
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
            log.info("File downloaded");
        } else {
            log.info("File {} already exists! ", JSON_FILE_NAME);
        }
        return JSON_FILE_NAME;
    }

    private void deleteBaseJsonFile() {
        log.info("Attempting to erase existing base file");
        File file = new File(JSON_FILE_NAME);
        if (file.delete())
            log.info("Base file deleted!");
        else
            log.info("The base file has not been deleted");
    }

    private String getFileNameFromURL(String jsonUrl) {
        String[] urlParts = jsonUrl.split("/");
        return urlParts[urlParts.length-1];
    }

    public Map<String, String> getOracleCardsImages(List<Card> cardList) throws Exception {
        Map<String, String> downloadedCardsNames = new HashMap<>();
        new File(Constants.ORACLE_CARDS_FOLDER).mkdir();
            for (Card card : cardList) {
                if (card.getImageUris() != null) {
                    try {
                        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(card.getImageUris().get(Constants.PNG)).openStream());
                        String fileName = createCardName(card);
                        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
                            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                        }
                        downloadedCardsNames.put(card.getCollectorNumber(), fileName);
                    } catch (IOException ioException) {
                        log.error("IoException while downloading card image", ioException);
                    }
                } else {
                    log.info("The card {} might be double-faced card, downloading only one face, skipping");
                }
            }
        return downloadedCardsNames;
    }

    /**
     * Will return the card name with the following format
     * oracleCards/nameWithoutSlashes-setName-collectorNumber-languages.png
     * @param card
     * @return
     */
    private String createCardName(Card card) {
        return Constants.ORACLE_CARDS_FOLDER +
                card.getName().replace(Constants.FORWARD_SLASH, Constants.HYPHEN) +
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
