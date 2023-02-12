package cl.rivendel.csv.service.client;

import java.io.IOException;

public interface FtpClient {
    String uploadImage(String base64Image, String imageName) throws IOException;
}
