package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SealedProductCard {
    private Boolean foil;
    private String name;
    private String number;
    private String set;
    private String uuid;
}
