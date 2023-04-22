package cl.rivendel.csv.mapper;

import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class CSVMapper {


    @Mappings({
            @Mapping(expression = "java(generateCardName(card))", target = "name"),
            @Mapping(expression = "java(generateCardDescription(card))", target = "description"),
            @Mapping(expression = "java(generateCardName(card))", target = "metaTitle"),
            @Mapping(expression = "java(generateCardName(card))", target = "metaDescription"),
            @Mapping(constant = "10.0", target = "width"),
            @Mapping(constant = "2.0", target = "length"),
            @Mapping(constant = "10.0", target = "height"),
            @Mapping(expression = "java(generateCategory(card))", target = "categories"),
            @Mapping(constant = "NO", target = "digital"),
            @Mapping(constant = "NO", target = "featured"),
            @Mapping(constant = "available", target = "status"),
            @Mapping(constant = "0.1", target = "weight"),
            @Mapping(constant = "0", target = "stock"),
            @Mapping(constant = "NO", target = "stockUnlimited"),
            @Mapping(constant = "0.0", target = "price"),
            @Mapping(constant = "Idioma", target = "variantOneOptionName"),
            @Mapping(constant = "option", target = "variantOneOptionType"),
            @Mapping(constant = "Ingles", target = "variantOneOptionValue"),
            @Mapping(constant = "Color", target = "customFieldOneLabel"),
            @Mapping(expression = "java(processColorIdentity(card.getColorIdentity()))", target = "customFieldOneValue"),
            @Mapping(constant = "selection", target = "customFieldOneType"),
            @Mapping(constant = "Edicion", target = "customFieldTwoLabel"),
            @Mapping(source = "card.setName", target = "customFieldTwoValue"),
            @Mapping(constant = "selection", target = "customFieldTwoType"),
            @Mapping(constant = "Rareza", target = "customFieldFourLabel"),
            @Mapping(source = "card.rarity", target = "customFieldFourValue"),
            @Mapping(constant = "selection", target = "customFieldFourType"),
            @Mapping(constant = "Tipo de carta", target = "customFieldThreeLabel"),
            @Mapping(source = "card.typeLine", target = "customFieldThreeValue"),
            @Mapping(constant = "selection", target = "customFieldThreeType"),
            @Mapping(constant = "Wizards", target = "brand"),
            @Mapping(expression = "java(setCardImage(card))", target = "images")
    })
    public abstract CSVModel toCsvModel(Card card);

    public String setCardImage(Card card) {
        if (card.getImageUris() != null && card.getImageUris().get("imgBB") != null)
            return card.getImageUris().get("imgBB");
        return "";
    }

    public String processColorIdentity(char[] colorIdentity) {
        String identity = new String(colorIdentity);
        if (identity.length() == 0)
            return "Incoloro";
        if (identity.length() > 1)
            return "Multicolor";
        else
            switch (identity.toLowerCase()) {
                case "u":
                    return "Azul";
                case "w":
                    return "Blanco";
                case "r":
                    return "Rojo";
                case "b":
                    return "Negro";
                case "g":
                    return "Verde";
            }

        return new String(colorIdentity);


    }

    public String generateCategory(Card card) {
        return "Magic Singles,Magic Singles /" + card.getSetName();
    }

    public String generateCardName(Card card) {
        return card.getName() +
                " #" +
                String.format("%03d", Integer.parseInt(card.getCollectorNumber()));
    }

    public String generateCardDescription(Card card) {
        StringBuilder sb = new StringBuilder()
                .append("<h1>")
                .append(card.getName())
                .append(card.getManaCost())
                .append("</h1>")
                .append("\n")

                .append(Constants.P_OPENING)
                .append(card.getTypeLine())
                .append(Constants.P_CLOSING)
                .append("\n")

                .append(Constants.P_OPENING)
                .append(card.getOracleText() != null ? card.getOracleText().replace("\n", "</br>").replace("\r", "") : "")
                .append(Constants.P_CLOSING)
                .append("\n");
        if (card.getTypeLine().toUpperCase().contains(Constants.CREATURE.toUpperCase())) {
            sb.append(Constants.P_OPENING)
                    .append(card.getPower())
                    .append("/")
                    .append(card.getToughness())
                    .append(Constants.P_CLOSING)
                    .append("\n");
        }

        if (card.getTypeLine().toUpperCase().contains("PLANESWALKER")) {
            sb.append(Constants.P_OPENING)
                    .append("Loyalty: ")
                    .append(card.getLoyalty())
                    .append(Constants.P_CLOSING).append("\n");
        }

        sb.append(Constants.P_OPENING)
                .append("Illustrated by ")
                .append("<a href='https://scryfall.com/search?q=a%3A%E2%80%9C")
                .append(card.getArtist().replace(" ", "+"))
                .append("%E2%80%9D&unique=art'")
                .append(">")
                .append(card.getArtist())
                .append("</a>")
                .append(Constants.P_CLOSING)
                .append("\n")
                .toString();

        return sb.toString();

    }
}
