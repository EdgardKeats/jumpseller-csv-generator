package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetData {
    private int baseSetSize;
    private String block;
    private Map<String, BoosterConfig> booster;
    private List<Card> cards;
    private Float cardsphereSetId;
    private String code;
    private String codeV3;
    private List<DeckSet> decks;
    private Boolean isForeignOnly;
    private Boolean isFoilOnly;
    private Boolean isNonFoilOnly;
    private Boolean isOnlineOnly;
    private Boolean isPaperOnly;
    private Boolean isPartialPreview;
    private String keyruneCode;
    private List<String> languages;
    private Float mcmId;
    private Float mcmIdExtras;
    private String mcmName;
    private String mtgoCode;
    private String name;
    private String parentCode;
    private String releaseDate;
    private List<SealedProduct> sealedProduct;
    private Float tcgplayerGroupId;
    private List<CardToken> tokens;
    private String tokenSetCode;
    private float totalSetSize;
    private Map<String, String> translations;
    private String type;

}
