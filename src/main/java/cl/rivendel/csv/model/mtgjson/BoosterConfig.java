package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoosterConfig {
    private Map<String, List<BoosterPack>> boosters;
    private Float boostersTotalWeight;
    private Map<String, BoosterSheet> sheets;
}
