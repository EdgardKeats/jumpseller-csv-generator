package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForeignData {
    private String faceName;
    private String flavorText;
    private String language;
    private Float multiverseId;
    private String name;
    private String text;
    private String type;
}
