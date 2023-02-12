package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Card {
    private String object;
    private UUID id;
    @JsonProperty("oracle_id")
    private String oracleId;
    @JsonProperty("multiverse_ids")
    private int[] multiverseIds;
    @JsonProperty("mtgo_id")
    private int mtgoId;
    @JsonProperty("mtgo_foil_id")
    private int mtgoFoilId;
    @JsonProperty("tcgplayer_id")
    private int tcgPlayerId;
    @JsonProperty("tcgplayer_etched_id")
    private int tcgplayerEtchedId;
    @JsonProperty("cardmarket_id")
    private int cardmarketId;
    private String name;
    private String lang;
    @JsonProperty("released_at")
    private String releasedAt;
    private String uri;
    @JsonProperty("scryfall_uri")
    private String scryfallUri;
    private String layout; //could be Enum
    @JsonProperty("highres_image")
    private boolean highresImage;
    @JsonProperty("image_status")
    private String imageStatus; //could be Enum
    @JsonProperty("image_uris")
    private Map<String, String> imageUris;
    @JsonProperty("mana_cost")
    private String manaCost;
    private float cmc;
    @JsonProperty("type_line")
    private String typeLine;
    @JsonProperty("oracle_text")
    private String oracleText;
    private String power;
    private String toughness;
    private char[] colors;
    @JsonProperty("color_identity")
    private char[] colorIdentity;
    private String[] keywords;
    private Map<String, String> legalities;
    private String[] games;
    private boolean reserved;
    private boolean foil;
    private boolean nonfoil;
    private String[] finishes;
    private boolean oversized;
    private boolean promo;
    private boolean reprint;
    private boolean variation;
    @JsonProperty("set_id")
    private UUID setId;
    private String set;
    @JsonProperty("set_name")
    private String setName;
    @JsonProperty("set_type")
    private String setType;
    @JsonProperty("set_uri")
    private String setUri;
    @JsonProperty("set_search_uri")
    private String setSearchUri;
    @JsonProperty("scryfall_set_uri")
    private String scryfallSetUri;
    @JsonProperty("rulings_uri")
    private String rulingsUri;
    @JsonProperty("prints_search_uri")
    private String printsSearchUri;
    @JsonProperty("collector_number")
    private String collectorNumber;
    private boolean digital;
    private String rarity; //could be Enum
    @JsonProperty("card_back_id")
    private String cardBackId;
    private String artist;
    @JsonProperty("artist_ids")
    private String[] artistIds;
    @JsonProperty("illustration_id")
    private String illustrationId;
    @JsonProperty("border_color")
    private String borderColor;
    private String frame;
    @JsonProperty("frame_effects")
    private List<String> frameEffects;
    @JsonProperty("security_stamp")
    private String securityStamp; //could be Enum
    @JsonProperty("full_art")
    private boolean fullArt;
    private boolean textless;
    private boolean booster;
    @JsonProperty("story_spotlight")
    private boolean storySpotlight;
    @JsonProperty("edhrec_rank")
    private int edhrecRank;
    private Map<String, String> preview;
    private Map<String, String> prices;
    @JsonProperty("related_uris")
    private Map<String, String> relatedUris;
    @JsonProperty("flavor_name")
    private String flavorName;
    @JsonProperty("flavor_text")
    private String flavorText;
    @JsonProperty("arena_id")
    private int arenaId;
    private String watermark;
    @JsonProperty("produced_mana")
    private char[] producedMana;
    @JsonProperty("all_parts")
    private List<RelatedCards> allParts;
    @JsonProperty("card_faces")
    private List<CardFace> cardFaces;
    @JsonProperty("promo_types")
    private List<String> promoTypes;
    private String loyalty;
    @JsonProperty("life_modifier")
    private String lifeModifier;
    @JsonProperty("hand_modifier")
    private String handModifier;
    @JsonProperty("color_indicator")
    private char[] colorIndicator;
    @JsonProperty("content_warning")
    private String contentWarning;
    @JsonProperty("penny_rank")
    private String pennyRank;
    @JsonProperty("attraction_lights")
    private int[] attractionLights;


}
