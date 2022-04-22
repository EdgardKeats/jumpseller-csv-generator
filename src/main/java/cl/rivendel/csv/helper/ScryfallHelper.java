package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.scryfall.BulkData;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import cl.rivendel.csv.model.scryfall.SetListObject;
import cl.rivendel.csv.service.client.ScryfallClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ScryfallHelper {

    private static final Logger log = LoggerFactory.getLogger(ScryfallHelper.class);

    private static final String ORACLE_CARDS_FOLDER = "C:\\SimpleSolution\\oracleCards\\";

    @Autowired
    private ScryfallClient scryfallClient;

    public String getOracleCardsURL() {
        return scryfallClient.getBulkData()
                .getData()
                .stream()
                .filter(bulkData1 -> bulkData1.getType().equalsIgnoreCase("oracle_cards"))
                .findFirst().map(BulkData::getDownloadUri).get();
    }

    public List<Card> getSetCards(String jsonUrl, String set) throws IOException {
        return getOracleCards(jsonUrl).stream().filter(p -> p.getSet().trim().equalsIgnoreCase(set)).collect(Collectors.toList());
    }

    public List<Card> getOracleCards(String jsonUrl) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(jsonUrl).openStream())) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(in, new TypeReference<List<Card>>() {
            });
        }
    }

    public List<String> getOracleCardsImages(List<Card> cardList) {
        List<String> downloadedCardsNames = new ArrayList<>();
        new File(ORACLE_CARDS_FOLDER).mkdir();
        boolean skipHighRes = true;
        for (Card card : cardList) {
            if (card.getImageUris() != null) {
                if (skipHighRes || card.getImageStatus().equalsIgnoreCase("highres_scan")) {
                    try {
                        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(card.getImageUris().get("png")).openStream());
                        String fileName = new StringBuffer().append(ORACLE_CARDS_FOLDER).append(card.getName().replace("/", "-")).append("-").append(card.getSet()).append(".png").toString();
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        fileOutputStream.getChannel()
                                .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                        downloadedCardsNames.add(fileName);
                    } catch (IOException ioException) {
                        log.error("IoException while downloading card image", ioException);
                    }
                }
            }
        }
        return downloadedCardsNames;
    }

    public Map<String, String> getSets() {
        SetListObject sets = scryfallClient.getAllSets();
        return sets.getData().stream().collect(Collectors.toMap(Set::getCode, Set::getName));
    }
}
