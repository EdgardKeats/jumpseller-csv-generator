package cl.rivendel.csv.configuration;

import cl.rivendel.csv.service.ScryfallClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class AppConfig {
    
    @Bean
    public ScryfallClient scryfallClient(){
        //https://api.scryfall.com/
        return Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(ScryfallClient.class, "https://api.scryfall.com/");
    }
}
