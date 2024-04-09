package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SealedProduct {
    private Float cardCount;
    private String category;
    private SealedProductContents contents;
    private Map<String, String> identifiers;
    private String name;
    private Float productSize;
    private Map<String, String> purchaseUrls;
    private String releaseDate;
    private String subtype;
    private String uuid;
}
