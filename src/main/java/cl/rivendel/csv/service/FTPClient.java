package cl.rivendel.csv.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface FTPClient {

    void uploadImages(Map<String, String> setImages);

}
