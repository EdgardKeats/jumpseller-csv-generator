package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatedCards {
    private List<String> reverseRelated;
    private List<String> spellbook;
}
