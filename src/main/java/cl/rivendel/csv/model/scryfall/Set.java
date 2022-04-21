package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Set {
    private UUID id;
    private String code;
    @JsonProperty("mtgo_code")
    private String mtgoCode;
    @JsonProperty("tcgplayer_id")
    private Integer tcgplayerId;
    private String name;
    @JsonProperty("set_type")
    private String setType;
    @JsonProperty("released_at")
    private String releasedAt;
    @JsonProperty("block_code")
    private String blockCode;
    private String block;
    @JsonProperty("parent_set_code")
    private String parentSetCode;
    @JsonProperty("card_count")
    private int cardCount;
    @JsonProperty("printed_size")
    private Integer printedSize;
    private boolean digital;
    @JsonProperty("foil_only")
    private boolean foilOnly;
    @JsonProperty("nonfoil_only")
    private boolean noFoilOnly;
    @JsonProperty("scryfall_uri")
    private String scryfallUri;
    private String uri;
    @JsonProperty("icon_svg_uri")
    private String iconSvgUri;
    @JsonProperty("search_uri")
    private String searchUri;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMtgoCode() {
        return mtgoCode;
    }

    public void setMtgoCode(String mtgoCode) {
        this.mtgoCode = mtgoCode;
    }

    public Integer getTcgplayerId() {
        return tcgplayerId;
    }

    public void setTcgplayerId(Integer tcgplayerId) {
        this.tcgplayerId = tcgplayerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public String getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(String releasedAt) {
        this.releasedAt = releasedAt;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getParentSetCode() {
        return parentSetCode;
    }

    public void setParentSetCode(String parentSetCode) {
        this.parentSetCode = parentSetCode;
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public Integer getPrintedSize() {
        return printedSize;
    }

    public void setPrintedSize(Integer printedSize) {
        this.printedSize = printedSize;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public boolean isFoilOnly() {
        return foilOnly;
    }

    public void setFoilOnly(boolean foilOnly) {
        this.foilOnly = foilOnly;
    }

    public boolean isNoFoilOnly() {
        return noFoilOnly;
    }

    public void setNoFoilOnly(boolean noFoilOnly) {
        this.noFoilOnly = noFoilOnly;
    }

    public String getScryfallUri() {
        return scryfallUri;
    }

    public void setScryfallUri(String scryfallUri) {
        this.scryfallUri = scryfallUri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIconSvgUri() {
        return iconSvgUri;
    }

    public void setIconSvgUri(String iconSvgUri) {
        this.iconSvgUri = iconSvgUri;
    }

    public String getSearchUri() {
        return searchUri;
    }

    public void setSearchUri(String searchUri) {
        this.searchUri = searchUri;
    }
}
