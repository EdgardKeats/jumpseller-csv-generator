package cl.rivendel.csv.helper;

import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardImageHelper {
    public List<String> createJumpsellerImages(List<String> oracleCardsNames){
        ArrayList<String> newImagesNames = new ArrayList<>();
        for (String cardName: oracleCardsNames) {
            BufferedImage newImage = new BufferedImage(2048, 2048, BufferedImage.TYPE_INT_RGB);

            //TODO: create image from existing card image, adding white background onto it

        }
        return newImagesNames;
    }
}
