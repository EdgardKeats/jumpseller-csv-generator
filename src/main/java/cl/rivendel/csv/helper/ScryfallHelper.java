package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.scryfall.BulkData;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import cl.rivendel.csv.service.client.ScryfallClient;
import cl.rivendel.csv.utils.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ScryfallHelper {

    private static final Logger log = LoggerFactory.getLogger(ScryfallHelper.class);
    private ScryfallClient scryfallClient;
    private boolean skipHighRes;

    public ScryfallHelper(@Autowired ScryfallClient scryfallClient, @Value("${csvGenProperty.skipHighResImage:true}") boolean skipHighRes){
        this.scryfallClient = scryfallClient;
        this.skipHighRes = skipHighRes;
    }

    public String getOracleCardsURL() {
        return scryfallClient.getBulkData().getData().stream().filter(bulkData1 -> bulkData1.getType().equalsIgnoreCase(Constants.ORACLE_CARDS)).findFirst().map(BulkData::getDownloadUri).get();
    }

    public List<Card> getSetCards(String jsonUrl, String set) {

        try {
            return getOracleCards(jsonUrl).stream().filter(p -> p.getSet().trim().equalsIgnoreCase(set)).collect(Collectors.toList());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Card> testGetSetCards(String jsonUrl, String set) {
        try {
            return Collections.singletonList(getOracleCards(jsonUrl).stream().filter(p -> p.getSet().trim().equalsIgnoreCase(set)).findFirst().get());

        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Card> getOracleCards(String jsonUrl) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(jsonUrl).openStream())) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(in, new TypeReference<List<Card>>() {
            });
        }
    }

    public Map<String, String> getOracleCardsImages(List<Card> cardList) throws Exception {
        Map<String, String> downloadedCardsNames = new HashMap<>();
        if (new File(Constants.ORACLE_CARDS_FOLDER).mkdir()) {
            for (Card card : cardList) {
                if (card.getImageUris() != null && (skipHighRes || card.getImageStatus().equalsIgnoreCase(Constants.HIGH_RES_SCAN))) {
                    try {
                        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(card.getImageUris().get(Constants.PNG)).openStream());
                        String fileName = Constants.ORACLE_CARDS_FOLDER + card.getName().replace(Constants.FORWARD_SLASH, Constants.HYPHEN) + Constants.HYPHEN + card.getSet() + Constants.PNG_EXTENSION;
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                        downloadedCardsNames.put(card.getCollectorNumber(), fileName);
                    } catch (IOException ioException) {
                        log.error("IoException while downloading card image", ioException);
                    }
                }
            }
        } else {
            throw new Exception("The target folder was not created");
        }
        return downloadedCardsNames;
    }

    public Map<String, String> getSets() {
        return scryfallClient.getAllSets().getData().stream().collect(Collectors.toMap(Set::getCode, Set::getName));
    }

    public Map<String, List<Set>> getSetsDividedByType() {
        return scryfallClient.getAllSets().getData().stream().collect(Collectors.groupingBy(Set::getSetType));
    }
}
