package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.scryfall.BulkData;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import cl.rivendel.csv.service.client.ScryfallClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private static final String ORACLE_CARDS_FOLDER = ".\\oracleCards\\";
    private static final String ORACLE_CARDS = "oracle_cards";
    private static final String HIGH_RES_SCAN = "highres_scan";
    private static final String PNG = "png";
    private static final String PNG_EXTENSION = ".png";
    private static final String HYPHEN = "-";
    private static final String FORWARD_SLASH = "/";

    @Autowired
    private ScryfallClient scryfallClient;

    @Value("${csvGenProperty.skipHighResImage:true}")
    private boolean skipHighRes;


    public String getOracleCardsURL() {
        return scryfallClient.getBulkData()
                .getData()
                .stream()
                .filter(bulkData1 -> bulkData1.getType().equalsIgnoreCase(ORACLE_CARDS))
                .findFirst().map(BulkData::getDownloadUri).get();
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

    public Map<String, String> getOracleCardsImages(List<Card> cardList) {
        Map<String, String> downloadedCardsNames = new HashMap<>();
        new File(ORACLE_CARDS_FOLDER).mkdir();
        for (Card card : cardList) {
            if (card.getImageUris() != null && (skipHighRes || card.getImageStatus().equalsIgnoreCase(HIGH_RES_SCAN))) {
                try {
                    ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(card.getImageUris().get(PNG)).openStream());
                    String fileName = ORACLE_CARDS_FOLDER + card.getName().replace(FORWARD_SLASH, HYPHEN) + HYPHEN + card.getSet() + PNG_EXTENSION;
                    FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                    fileOutputStream.getChannel()
                            .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                    downloadedCardsNames.put(card.getCollectorNumber(), fileName);
                } catch (IOException ioException) {
                    log.error("IoException while downloading card image", ioException);
                }
            }
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
