package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricePoints {
    private Map<String, Float> foil;
    private Map<String, Float> normal;
    private Map<String, Float> etched;
}
