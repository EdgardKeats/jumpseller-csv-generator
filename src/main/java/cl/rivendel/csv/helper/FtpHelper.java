package cl.rivendel.csv.helper;

import cl.rivendel.csv.model.imgbb.ImgBBRequest;
import cl.rivendel.csv.model.imgbb.ImgBBResponse;
import cl.rivendel.csv.service.client.ImgBBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FtpHelper {

    @Value("${imgbb.apikey}")
    private String imgBBApiKey;

    @Autowired
    private ImgBBClient imgBBClient;

    public void uploadImages(Map<String, String> setImages){
        for (Map.Entry<String, String> entry: setImages.entrySet()) {
            ImgBBRequest request = new ImgBBRequest(imgBBApiKey, getBase64Image(entry.getValue()), entry.getValue(), 3600 );
            ImgBBResponse response = imgBBClient.uploadImage(request);
            if(response.getStatus() == 200)
                entry.setValue(response.getData().getImage().getUrl());
            else
                entry.setValue("");
        }
    }

    private String getBase64Image(String imgPath) {
        //TODO: get file from path and create base64 String from it
        return "";
    }

}
