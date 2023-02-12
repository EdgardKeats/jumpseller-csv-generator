package cl.rivendel.csv.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class CardImageHelper {
    private static final Logger log = LoggerFactory.getLogger(CardImageHelper.class);

    public void createJumpsellerImages(Map<String, String> oracleCardsPaths){
        oracleCardsPaths.values().forEach(cardName -> {
            try {
                CardImageHelper.this.redrawImage(cardName);
            } catch (IOException e) {
                log.error("Error while trying to redraw image {}: {}", cardName, e);
            }
        });
    }

    public boolean retornaBoolean(String palo){
        return palo.trim().equalsIgnoreCase("palo");
    }

    public void redrawImage(String cardPath) throws IOException {
        File file = new File(cardPath);
        BufferedImage bi = ImageIO.read(file);
        int desiredSize = bi.getHeight(); //set image height
        BufferedImage resizedImage = new BufferedImage(desiredSize, desiredSize, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = resizedImage.createGraphics();
        g.setPaint(Color.WHITE);
        g.fillRect(0, 0, desiredSize, desiredSize);

        int tempWidth;
        int tempHeight;
        int y = 0;
        int x = 0;

        if (bi.getHeight() < bi.getWidth()) {
            tempWidth = desiredSize;
            tempHeight = (int)(((double)bi.getHeight()*desiredSize)/bi.getWidth());
            y = -(tempHeight - tempWidth)/2;
        }
        else {
            tempHeight = desiredSize;
            tempWidth = (int)(((double)bi.getWidth()*desiredSize)/bi.getHeight());
            x = -(tempWidth - tempHeight)/2;
        }

        g.drawImage(bi.getScaledInstance(tempWidth, tempHeight, Image.SCALE_SMOOTH), x, y, null);

        g.dispose();

        ImageIO.write(resizedImage, "jpg", file);
        bi.flush();
    }

}
