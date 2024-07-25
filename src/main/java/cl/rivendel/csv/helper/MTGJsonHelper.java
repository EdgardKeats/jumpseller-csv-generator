package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.mtgjson.AllPrices;
import cl.rivendel.csv.model.mtgjson.Card;
import cl.rivendel.csv.model.mtgjson.MtgJsonData;
import cl.rivendel.csv.model.mtgjson.PriceData;
import cl.rivendel.csv.utils.DownloadUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Component
public class MTGJsonHelper {

    private static final String MTG_JSON_BASE_URL = "https://mtgjson.com/api/v5/%s";
    private static final String STORE_CARD_KINGDOM = "cardkingdom";
    @Autowired
    DownloadUtils downloadUtils;

    public List<Card> getCardsFromSetJSON(String set){
        try {
            String filePath = downloadUtils.saveJSONFile(String.format(MTG_JSON_BASE_URL, set.toUpperCase(Locale.ROOT)+".json"));
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            MtgJsonData setData = objectMapper.readValue(jsonData, MtgJsonData.class);
            return setData.getData().getCards();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public Map<String, Float> getCardPrices() {
        try {
            String fileName = downloadUtils.saveJSONFile(String.format(MTG_JSON_BASE_URL, "AllPricesToday.json"));
            byte[] jsonData = Files.readAllBytes(Paths.get(fileName));
            ObjectMapper objectMapper = new ObjectMapper();
            AllPrices priceData = objectMapper.readValue(jsonData, AllPrices.class);
            Map<String, Float> cardPrices = new HashMap<>();
            for (Map.Entry<String, PriceData> entry: priceData.getData().entrySet()) {
                if(entry.getValue().getPaper() != null &&
                        entry.getValue().getPaper().get(STORE_CARD_KINGDOM)!=null) {
                    Map<String, Float> normalPriceEntry = entry.getValue().getPaper().get(STORE_CARD_KINGDOM).getRetail().getNormal();
                    if(normalPriceEntry!=null)
                        cardPrices.put(entry.getKey(), normalPriceEntry.entrySet().stream().findFirst().get().getValue());
                }
            }
            return cardPrices;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public void replaceUUID(List<cl.rivendel.csv.model.scryfall.Card> cardsList, List<Card> cards) {
        int replacedUUIDsCounter = 0;
        for (cl.rivendel.csv.model.scryfall.Card scryfallCard : cardsList) {
            Optional<Card> mtgJsonCard = cards.stream().filter((card) -> card.getName().equalsIgnoreCase(scryfallCard.getName())).findFirst();
            if(mtgJsonCard.isPresent()){
                scryfallCard.setId(mtgJsonCard.get().getUuid());
                replacedUUIDsCounter++;
            }
        }
        System.out.println("Replaced IDs: "+replacedUUIDsCounter);
    }

    public void mergePrices(List<cl.rivendel.csv.model.scryfall.Card> cardsList, Map<String, Float> cardPrices, int valorDolar) {
        cardsList.forEach(card -> {
            if(cardPrices.get(card.getId()) != null){
                float valorTotal = Math.round(cardPrices.get(card.getId())*valorDolar/10.0) * 10 ;
                card.getPrices().put("custom-cardkingdom", Float.toString(valorTotal));
            } else {
                card.getPrices().put("custom-cardkingdom", "0.0");
            }
        });
    }
}
