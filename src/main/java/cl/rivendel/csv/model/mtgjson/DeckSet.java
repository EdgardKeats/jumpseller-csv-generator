package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeckSet {
    private String code;
    private List<CardSetDeck> commander;
    private List<CardSetDeck> mainBoard;
    private String name;
    private String releaseDate;
    private String sealedProductUuids;
    private List<CardSetDeck> sideBoard;
    private String type;
}
