package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private String printedTextLine;
    private String toughness;
    @JsonProperty("type_line")
    private String typeLine;
    private String watermark;

    @JsonProperty("artist_id")
    private String artistId;
    @JsonProperty("flavor_name")
    private String flavorName;
    @JsonProperty("defense")
    private String defense;
    @JsonProperty("printed_type_line")
    private String printedTypeLine;
}
