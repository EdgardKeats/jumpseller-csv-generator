package cl.rivendel.csv.helper;

import cl.rivendel.csv.service.client.FtpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@Component
public class FtpHelper {

    private static final Logger log = LoggerFactory.getLogger(FtpHelper.class);
    @Autowired
    private FtpClient ftpClient;

    public void uploadImages(Map<String, String> setImages) {
        for (Map.Entry<String, String> entry : setImages.entrySet()) {
            try {
                String base64Image = getBase64Image(entry.getValue());
                entry.setValue(ftpClient.uploadImage(base64Image, entry.getKey()));
            } catch (IOException ioException) {
                log.error("IOException while uploading image...");
            }
        }
    }

    private String getBase64Image(String imgPath) {
        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(
                    Paths.get(imgPath)));
        } catch (IOException ex) {
            return "";
        }
    }


}
