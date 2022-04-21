package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.UUID;


public class CardFace {
    private String artist;
    private float cmc;
    @JsonProperty("color_indicator")
    private char[] colorIndicator;
    private char[] colors;
    @JsonProperty("flavor_text")
    private String flavorText;
    @JsonProperty("illustration_id")
    private UUID illustrationId;
    @JsonProperty("image_uris")
    private Map<String, String> imageUris;
    private String layout;
    private String loyalty;
    @JsonProperty("mana_cost")
    private String manaCost;
    private String name;
    private String object;
    @JsonProperty("oracle_id")
    private UUID oracleId;
    @JsonProperty("oracle_text")
    private String oracleText;
    private String power;
    @JsonProperty("printed_name")
    private String printedName;
    @JsonProperty("printed_text")
    private String printedText;
    @JsonProperty("printed_text_line")
    private String printedTypeLine;
    private String toughness;
    @JsonProperty("type_line")
    private String typeLine;
    private String watermark;

    @JsonProperty("artist_id")
    private String artistId;
    @JsonProperty("flavor_name")
    private String flavorName;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public float getCmc() {
        return cmc;
    }

    public void setCmc(float cmc) {
        this.cmc = cmc;
    }

    public char[] getColorIndicator() {
        return colorIndicator;
    }

    public void setColorIndicator(char[] colorIndicator) {
        this.colorIndicator = colorIndicator;
    }

    public char[] getColors() {
        return colors;
    }

    public void setColors(char[] colors) {
        this.colors = colors;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public UUID getIllustrationId() {
        return illustrationId;
    }

    public void setIllustrationId(UUID illustrationId) {
        this.illustrationId = illustrationId;
    }

    public Map<String, String> getImageUris() {
        return imageUris;
    }

    public void setImageUris(Map<String, String> imageUris) {
        this.imageUris = imageUris;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public UUID getOracleId() {
        return oracleId;
    }

    public void setOracleId(UUID oracleId) {
        this.oracleId = oracleId;
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

    public String getPrintedName() {
        return printedName;
    }

    public void setPrintedName(String printedName) {
        this.printedName = printedName;
    }

    public String getPrintedText() {
        return printedText;
    }

    public void setPrintedText(String printedText) {
        this.printedText = printedText;
    }

    public String getPrintedTypeLine() {
        return printedTypeLine;
    }

    public void setPrintedTypeLine(String printedTypeLine) {
        this.printedTypeLine = printedTypeLine;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }
}
