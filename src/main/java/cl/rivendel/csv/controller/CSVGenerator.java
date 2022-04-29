package cl.rivendel.csv.controller;

import cl.rivendel.csv.helper.CSVHelper;
import cl.rivendel.csv.helper.CardImageHelper;
import cl.rivendel.csv.helper.FtpHelper;
import cl.rivendel.csv.helper.ScryfallHelper;
import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CSVGenerator {

    private static final Logger log = LoggerFactory.getLogger(CSVGenerator.class);

    @Autowired
    private CardImageHelper cardImageHelper;
    @Autowired
    private ScryfallHelper scryfallHelper;
    @Autowired
    private CSVHelper csvHelper;
    @Autowired
    private FtpHelper ftpHelper;

    Map<String, String> cardNames;

    @Value("${csvGenProperty.createImage:false}")
    private boolean createImages;

    /**
     * Preliminary CSV generation for jumpseller
     */
    public void generateCSV() throws IOException {
        log.info("getting sets");
        Map<String, String> setMap = scryfallHelper.getSets();

        log.info("getting base json");
        String baseJsonUrl = scryfallHelper.getOracleCardsURL();
        String firstFoundSet = "snc";

        log.info("getting cards from set: {}", setMap.get(firstFoundSet));
        List<Card> cardsList = scryfallHelper.getSetCards(baseJsonUrl, firstFoundSet);

        if(createImages) {
            log.info("getting {} cards images", cardsList.size());
            cardNames = scryfallHelper.getOracleCardsImages(cardsList);

            log.info("processing card images");
            cardImageHelper.createJumpsellerImages(cardNames);

            log.info("uploading jumpseller images to image server");
            ftpHelper.uploadImages(cardNames);

            log.info("updating csv file with card images urls");
            csvHelper.updateImagesUris(cardsList, cardNames);
        }

        log.info("creating csv models");
        List<CSVModel> listCSVModel = csvHelper.cardListToCsvModelList(cardsList);

        log.info("creating jumpseller csv file");
        csvHelper.generateJumpSellerCSV(listCSVModel);


    }
}
