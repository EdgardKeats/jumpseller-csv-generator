package cl.rivendel.csv.utils;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

@Service
public class DownloadUtils {
    public String saveJSONFile(String targetJsonUrl) throws IOException {
        String jsonFileName = getJsonFilename(targetJsonUrl);
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new URL(targetJsonUrl).openStream();
            output = new FileOutputStream(jsonFileName);
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
            // Here you could append further stuff to `output` if necessary.
        } finally {
            if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
            if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
        }
        return jsonFileName;
    }

    private String getJsonFilename(String targetJsonUrl) {
        String[] splittedString = targetJsonUrl.split("/");
        return splittedString[splittedString.length-1];
    }
}
