package cl.rivendel.csv.model.mtgjson;

import java.util.List;
import java.util.Map;

public class SealedProductContents {
    private List<SealedProductCard> card;
    private List<SealedProductDeck> deck;
    private List<SealedProductOther> other;
    private List<SealedProductPack> pack;
    private List<SealedProductSealed> sealed;
    private Map<String, SealedProductContents[]>[] variable;
}
