package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ruling {
    private String date;
    private String text;
}
