package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoosterSheet {
    private Boolean allowDuplicates;
    private Boolean balanceColors;
    private Map<String, Float> cards;
    private boolean foil;
    private Boolean fixed;
    private Float totalWeight;
}
