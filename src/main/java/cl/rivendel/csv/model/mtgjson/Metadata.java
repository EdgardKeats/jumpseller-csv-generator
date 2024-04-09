package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Metadata {
    private String date;
    private String version;
}
