package cl.rivendel.csv.service.client;

import cl.rivendel.csv.model.imgbb.ImgBBRequest;
import cl.rivendel.csv.model.imgbb.ImgBBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "imgBBClient", url = "https://api.imgbb.com/")
public interface ImgBBClient {
    @RequestMapping(method = RequestMethod.POST, value = "/1/upload?key={key}")
    ImgBBResponse uploadImage(String key, ImgBBRequest requests);
}
