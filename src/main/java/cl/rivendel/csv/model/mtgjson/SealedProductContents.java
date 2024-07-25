package cl.rivendel.csv.model.mtgjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SealedProductContents {
    private List<SealedProductCard> card;
    private List<SealedProductDeck> deck;
    private List<SealedProductOther> other;
    private List<SealedProductPack> pack;
    private List<SealedProductSealed> sealed;
    private Map<String, SealedProductContents[]>[] variable;
}
