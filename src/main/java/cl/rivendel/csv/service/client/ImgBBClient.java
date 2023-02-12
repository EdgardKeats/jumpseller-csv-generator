package cl.rivendel.csv.service.client;

import cl.rivendel.csv.helper.FtpHelper;
import cl.rivendel.csv.model.imgbb.ImgBBResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImgBBClient implements FtpClient {

    private static final Logger log = LoggerFactory.getLogger(FtpClient.class);

    @Value("${imgbb.apikey}")
    private String imgBBApiKey;

    public String uploadImage(String base64Image, String imgName) throws IOException {
        log.trace("uploading base64Image={}, imgName={}", base64Image, imgName);
        String returnValue = "";
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload?key="+imgBBApiKey);
            List<NameValuePair> postParameters = new ArrayList<>();
            postParameters.add(new BasicNameValuePair("image", base64Image));
            postParameters.add(new BasicNameValuePair("name", imgName));
            httpPost.setEntity(new UrlEncodedFormEntity(postParameters));

            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            log.trace(response2.getCode() + " " + response2.getReasonPhrase());

            if(response2.getCode()==200) {
                HttpEntity entity2 = response2.getEntity();

                String stringJson = EntityUtils.toString(entity2);
                ObjectMapper mapper = new ObjectMapper();

                ImgBBResponse responsePojo = mapper.readValue(stringJson, ImgBBResponse.class);

                returnValue = responsePojo.getData().getImage().getUrl();
                log.trace("new value! {}", returnValue);

            } else {
                log.error("Non 200 response from ftp service: {} {}", response2.getCode(), response2.getReasonPhrase());
            }

        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
        } finally {
            if(httpclient!=null){
                httpclient.close();
                log.trace("http client succesfully closed");
            }
        }
        return returnValue;
    }
}
