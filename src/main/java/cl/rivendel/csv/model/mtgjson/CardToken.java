package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardToken {
    private String artist;
    private List<String> artistIds;
    private String asciiName;
    private List<String> availability;
    private List<String> boosterTypes;
    private String borderColor;
    private List<String> cardParts;
    private List<String> colorIdentity;
    private List<String> colorIndicator;
    private List<String> colors;
    private String faceName;
    private String faceFlavorName;
    private List<String> finishes;
    private String flavorText;
    private List<String> frameEffects;
    private String frameVersion;
    private boolean hasFoil;
    private boolean hasNonFoil;
    private Map<String, String> identifiers;
    private Boolean isFullArt;
    private Boolean isFunny;
    private Boolean isOnlineOnly;
    private Boolean isPromo;
    private Boolean isReprint;
    private Boolean isTextless;
    private List<String> keywords;
    private String language;
    private String layout;
    private String loyalty;
    private String name;
    private String number;
    private String orientation;
    private String originalText;
    private String originalType;
    private List<String> otherFaceIds;
    private String power;
    private List<String> promoTypes;
    private RelatedCards relatedCards;
    private List<String> reverseRelated;
    private String securityStamp;
    private String setCode;
    private String side;
    private String signature;
    private String sourceProducts;
    private String subsets;
    private List<String> subtypes;
    private List<String> supertypes;
    private String text;
    private String toughness;
    private String type;
    private List<String> types;
    private String uuid;
    private String watermark;
    private Long edhrecSaltiness;
    private String manaCost;
}
