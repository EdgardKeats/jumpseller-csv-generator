package cl.rivendel.csv.mapper;

import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class CSVMapper {
    static final String P_OPENING = "<p>";
    static final String P_CLOSING = "</p>";
    static final String CREATURE = "creature";

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

            //@Mapping(constant = "", target ="permalink"),
            @Mapping(constant = "", target = "brand"),
            @Mapping(constant = "", target = "barcode"),
            @Mapping(constant = "", target = "images"),
            @Mapping(constant = "", target = "sku"),
            @Mapping(constant = "", target = "googleProductCategory")
    })
    public abstract CSVModel toCsvModel(Card card);

    public String processColorIdentity(char[] colorIdentity){
        String identity = new String(colorIdentity);
        if(identity.length()== 0)
            return "Incoloro";
        if(identity.length()>1)
            return "Multicolor";
        else
            switch (identity.toLowerCase()){
                case "u" : return "Azul";
                case "w" : return "Blanco";
                case "r" : return "Rojo";
                case "b" : return "Negro";
                case "g" : return "Verde";
            }

        return new String(colorIdentity);


    }

    public String generateCategory(Card card) {
        return "Magic Singles,Magic Singles /" + card.getSetName();
    }

    public String generateCardName(Card card) {

        StringBuffer sb = new StringBuffer();
        return sb.append(card.getName())
                .append(" #")
                .append(String.format("%03d", Integer.parseInt(card.getCollectorNumber()))).toString();
    }

    public String generateCardDescription(Card card) {
        StringBuffer sb = new StringBuffer()
                .append("<h1>")
                .append(card.getName())
                .append(card.getManaCost())
                .append("</h1>")
                .append(P_OPENING)
                .append(card.getTypeLine())
                .append(P_CLOSING)
                .append(P_OPENING)
                .append(card.getOracleText().replace("\n", "").replace("\r", ""))
                .append(P_CLOSING);
        if (card.getTypeLine().contains(CREATURE)) {
            sb.append(P_OPENING)
                    .append(card.getPower())
                    .append("/")
                    .append(card.getToughness())
                    .append(P_CLOSING);
        }
        return sb.append(P_OPENING)
                .append("Illustrated by ")
                .append("<a href='https://scryfall.com/search?q=a%3A%E2%80%9C")
                .append(card.getArtist().replace(" ", "+"))
                .append("%E2%80%9D&unique=art")
                .toString();

    }
}
