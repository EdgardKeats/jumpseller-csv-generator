package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Card {
    private String object;
    private UUID id;
    @JsonProperty("oracle_id")
    private  String oracleId;
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

    public Card() {
    }

    public Card(String object, UUID id, String oracleId, int[] multiverseIds, int mtgoId, int mtgoFoilId, int tcgPlayerId, int tcgplayerEtchedId, int cardmarketId, String name, String lang, String releasedAt, String uri, String scryfallUri, String layout, boolean highresImage, String imageStatus, Map<String, String> imageUris, String manaCost, float cmc, String typeLine, String oracleText, String power, String toughness, char[] colors, char[] colorIdentity, String[] keywords, Map<String, String> legalities, String[] games, boolean reserved, boolean foil, boolean nonfoil, String[] finishes, boolean oversized, boolean promo, boolean reprint, boolean variation, UUID setId, String set, String setName, String setType, String setUri, String setSearchUri, String scryfallSetUri, String rulingsUri, String printsSearchUri, String collectorNumber, boolean digital, String rarity, String cardBackId, String artist, String[] artistIds, String illustrationId, String borderColor, String frame, List<String> frameEffects, String securityStamp, boolean fullArt, boolean textless, boolean booster, boolean storySpotlight, int edhrecRank, Map<String, String> preview, Map<String, String> prices, Map<String, String> relatedUris, String flavorName, String flavorText, int arenaId, String watermark, char[] producedMana, List<RelatedCards> allParts, List<CardFace> cardFaces, List<String> promoTypes, String loyalty, String lifeModifier, String handModifier, char[] colorIndicator, String contentWarning, String pennyRank) {
        this.object = object;
        this.id = id;
        this.oracleId = oracleId;
        this.multiverseIds = multiverseIds;
        this.mtgoId = mtgoId;
        this.mtgoFoilId = mtgoFoilId;
        this.tcgPlayerId = tcgPlayerId;
        this.tcgplayerEtchedId = tcgplayerEtchedId;
        this.cardmarketId = cardmarketId;
        this.name = name;
        this.lang = lang;
        this.releasedAt = releasedAt;
        this.uri = uri;
        this.scryfallUri = scryfallUri;
        this.layout = layout;
        this.highresImage = highresImage;
        this.imageStatus = imageStatus;
        this.imageUris = imageUris;
        this.manaCost = manaCost;
        this.cmc = cmc;
        this.typeLine = typeLine;
        this.oracleText = oracleText;
        this.power = power;
        this.toughness = toughness;
        this.colors = colors;
        this.colorIdentity = colorIdentity;
        this.keywords = keywords;
        this.legalities = legalities;
        this.games = games;
        this.reserved = reserved;
        this.foil = foil;
        this.nonfoil = nonfoil;
        this.finishes = finishes;
        this.oversized = oversized;
        this.promo = promo;
        this.reprint = reprint;
        this.variation = variation;
        this.setId = setId;
        this.set = set;
        this.setName = setName;
        this.setType = setType;
        this.setUri = setUri;
        this.setSearchUri = setSearchUri;
        this.scryfallSetUri = scryfallSetUri;
        this.rulingsUri = rulingsUri;
        this.printsSearchUri = printsSearchUri;
        this.collectorNumber = collectorNumber;
        this.digital = digital;
        this.rarity = rarity;
        this.cardBackId = cardBackId;
        this.artist = artist;
        this.artistIds = artistIds;
        this.illustrationId = illustrationId;
        this.borderColor = borderColor;
        this.frame = frame;
        this.frameEffects = frameEffects;
        this.securityStamp = securityStamp;
        this.fullArt = fullArt;
        this.textless = textless;
        this.booster = booster;
        this.storySpotlight = storySpotlight;
        this.edhrecRank = edhrecRank;
        this.preview = preview;
        this.prices = prices;
        this.relatedUris = relatedUris;
        this.flavorName = flavorName;
        this.flavorText = flavorText;
        this.arenaId = arenaId;
        this.watermark = watermark;
        this.producedMana = producedMana;
        this.allParts = allParts;
        this.cardFaces = cardFaces;
        this.promoTypes = promoTypes;
        this.loyalty = loyalty;
        this.lifeModifier = lifeModifier;
        this.handModifier = handModifier;
        this.colorIndicator = colorIndicator;
        this.contentWarning = contentWarning;
        this.pennyRank = pennyRank;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOracleId() {
        return oracleId;
    }

    public void setOracleId(String oracleId) {
        this.oracleId = oracleId;
    }

    public int[] getMultiverseIds() {
        return multiverseIds;
    }

    public void setMultiverseIds(int[] multiverseIds) {
        this.multiverseIds = multiverseIds;
    }

    public int getMtgoId() {
        return mtgoId;
    }

    public void setMtgoId(int mtgoId) {
        this.mtgoId = mtgoId;
    }

    public int getMtgoFoilId() {
        return mtgoFoilId;
    }

    public void setMtgoFoilId(int mtgoFoilId) {
        this.mtgoFoilId = mtgoFoilId;
    }

    public int getTcgPlayerId() {
        return tcgPlayerId;
    }

    public void setTcgPlayerId(int tcgPlayerId) {
        this.tcgPlayerId = tcgPlayerId;
    }

    public int getTcgplayerEtchedId() {
        return tcgplayerEtchedId;
    }

    public void setTcgplayerEtchedId(int tcgplayerEtchedId) {
        this.tcgplayerEtchedId = tcgplayerEtchedId;
    }

    public int getCardmarketId() {
        return cardmarketId;
    }

    public void setCardmarketId(int cardmarketId) {
        this.cardmarketId = cardmarketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(String releasedAt) {
        this.releasedAt = releasedAt;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getScryfallUri() {
        return scryfallUri;
    }

    public void setScryfallUri(String scryfallUri) {
        this.scryfallUri = scryfallUri;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public boolean isHighresImage() {
        return highresImage;
    }

    public void setHighresImage(boolean highresImage) {
        this.highresImage = highresImage;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public Map<String, String> getImageUris() {
        return imageUris;
    }

    public void setImageUris(Map<String, String> imageUris) {
        this.imageUris = imageUris;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public float getCmc() {
        return cmc;
    }

    public void setCmc(float cmc) {
        this.cmc = cmc;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }

    public String getOracleText() {
        return oracleText;
    }

    public void setOracleText(String oracleText) {
        this.oracleText = oracleText;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public char[] getColors() {
        return colors;
    }

    public void setColors(char[] colors) {
        this.colors = colors;
    }

    public char[] getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(char[] colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public Map<String, String> getLegalities() {
        return legalities;
    }

    public void setLegalities(Map<String, String> legalities) {
        this.legalities = legalities;
    }

    public String[] getGames() {
        return games;
    }

    public void setGames(String[] games) {
        this.games = games;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isFoil() {
        return foil;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public boolean isNonfoil() {
        return nonfoil;
    }

    public void setNonfoil(boolean nonfoil) {
        this.nonfoil = nonfoil;
    }

    public String[] getFinishes() {
        return finishes;
    }

    public void setFinishes(String[] finishes) {
        this.finishes = finishes;
    }

    public boolean isOversized() {
        return oversized;
    }

    public void setOversized(boolean oversized) {
        this.oversized = oversized;
    }

    public boolean isPromo() {
        return promo;
    }

    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    public boolean isReprint() {
        return reprint;
    }

    public void setReprint(boolean reprint) {
        this.reprint = reprint;
    }

    public boolean isVariation() {
        return variation;
    }

    public void setVariation(boolean variation) {
        this.variation = variation;
    }

    public UUID getSetId() {
        return setId;
    }

    public void setSetId(UUID setId) {
        this.setId = setId;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public String getSetUri() {
        return setUri;
    }

    public void setSetUri(String setUri) {
        this.setUri = setUri;
    }

    public String getSetSearchUri() {
        return setSearchUri;
    }

    public void setSetSearchUri(String setSearchUri) {
        this.setSearchUri = setSearchUri;
    }

    public String getScryfallSetUri() {
        return scryfallSetUri;
    }

    public void setScryfallSetUri(String scryfallSetUri) {
        this.scryfallSetUri = scryfallSetUri;
    }

    public String getRulingsUri() {
        return rulingsUri;
    }

    public void setRulingsUri(String rulingsUri) {
        this.rulingsUri = rulingsUri;
    }

    public String getPrintsSearchUri() {
        return printsSearchUri;
    }

    public void setPrintsSearchUri(String printsSearchUri) {
        this.printsSearchUri = printsSearchUri;
    }

    public String getCollectorNumber() {
        return collectorNumber;
    }

    public void setCollectorNumber(String collectorNumber) {
        this.collectorNumber = collectorNumber;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getCardBackId() {
        return cardBackId;
    }

    public void setCardBackId(String cardBackId) {
        this.cardBackId = cardBackId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String[] getArtistIds() {
        return artistIds;
    }

    public void setArtistIds(String[] artistIds) {
        this.artistIds = artistIds;
    }

    public String getIllustrationId() {
        return illustrationId;
    }

    public void setIllustrationId(String illustrationId) {
        this.illustrationId = illustrationId;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public List<String> getFrameEffects() {
        return frameEffects;
    }

    public void setFrameEffects(List<String> frameEffects) {
        this.frameEffects = frameEffects;
    }

    public String getSecurityStamp() {
        return securityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        this.securityStamp = securityStamp;
    }

    public boolean isFullArt() {
        return fullArt;
    }

    public void setFullArt(boolean fullArt) {
        this.fullArt = fullArt;
    }

    public boolean isTextless() {
        return textless;
    }

    public void setTextless(boolean textless) {
        this.textless = textless;
    }

    public boolean isBooster() {
        return booster;
    }

    public void setBooster(boolean booster) {
        this.booster = booster;
    }

    public boolean isStorySpotlight() {
        return storySpotlight;
    }

    public void setStorySpotlight(boolean storySpotlight) {
        this.storySpotlight = storySpotlight;
    }

    public int getEdhrecRank() {
        return edhrecRank;
    }

    public void setEdhrecRank(int edhrecRank) {
        this.edhrecRank = edhrecRank;
    }

    public Map<String, String> getPreview() {
        return preview;
    }

    public void setPreview(Map<String, String> preview) {
        this.preview = preview;
    }

    public Map<String, String> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, String> prices) {
        this.prices = prices;
    }

    public Map<String, String> getRelatedUris() {
        return relatedUris;
    }

    public void setRelatedUris(Map<String, String> relatedUris) {
        this.relatedUris = relatedUris;
    }

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public int getArenaId() {
        return arenaId;
    }

    public void setArenaId(int arenaId) {
        this.arenaId = arenaId;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public char[] getProducedMana() {
        return producedMana;
    }

    public void setProducedMana(char[] producedMana) {
        this.producedMana = producedMana;
    }

    public List<RelatedCards> getAllParts() {
        return allParts;
    }

    public void setAllParts(List<RelatedCards> allParts) {
        this.allParts = allParts;
    }

    public List<CardFace> getCardFaces() {
        return cardFaces;
    }

    public void setCardFaces(List<CardFace> cardFaces) {
        this.cardFaces = cardFaces;
    }

    public List<String> getPromoTypes() {
        return promoTypes;
    }

    public void setPromoTypes(List<String> promoTypes) {
        this.promoTypes = promoTypes;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public String getLifeModifier() {
        return lifeModifier;
    }

    public void setLifeModifier(String lifeModifier) {
        this.lifeModifier = lifeModifier;
    }

    public String getHandModifier() {
        return handModifier;
    }

    public void setHandModifier(String handModifier) {
        this.handModifier = handModifier;
    }

    public char[] getColorIndicator() {
        return colorIndicator;
    }

    public void setColorIndicator(char[] colorIndicator) {
        this.colorIndicator = colorIndicator;
    }

    public String getContentWarning() {
        return contentWarning;
    }

    public void setContentWarning(String contentWarning) {
        this.contentWarning = contentWarning;
    }

    public String getPennyRank(){
        return this.pennyRank;
    }

    public void setPennyRank(String pennyRank){
        this.pennyRank = pennyRank;
    }
}
