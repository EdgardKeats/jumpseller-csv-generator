package cl.rivendel.csv.ui;

import cl.rivendel.csv.helper.CSVHelper;
import cl.rivendel.csv.helper.CardImageHelper;
import cl.rivendel.csv.helper.MTGJsonHelper;
import cl.rivendel.csv.helper.ScryfallHelper;
import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import cl.rivendel.csv.service.FTPClient;
import cl.rivendel.csv.utils.DownloadUtils;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleUIController {
    private final HostServices hostServices;
    private final ScryfallHelper scryfallHelper;
    private final CardImageHelper cardImageHelper;
    private final FTPClient ftpHelper;
    private final CSVHelper csvHelper;
    private final DownloadUtils downloadUtils;
    private final MTGJsonHelper mtgJsonHelper;

    private static final Logger log = LoggerFactory.getLogger(SimpleUIController.class);


    SimpleUIController(HostServices hostServices, ScryfallHelper scryfallHelper, CardImageHelper cardImageHelper, FTPClient ftpHelper, CSVHelper csvHelper, DownloadUtils downloadUtils, MTGJsonHelper mtgJsonHelper) {
        this.hostServices = hostServices;
        this.scryfallHelper = scryfallHelper;
        this.cardImageHelper = cardImageHelper;
        this.ftpHelper = ftpHelper;
        this.csvHelper = csvHelper;
        this.downloadUtils = downloadUtils;
        this.mtgJsonHelper = mtgJsonHelper;
    }

    @FXML
    public ComboBox<String> cboSetType;
    @FXML
    public ComboBox<String> cboSet;
    @FXML
    public CheckBox chkUploadImages;
    @FXML
    public Button btnUpdateDB;
    @FXML
    public Button btnClearForm;
    @FXML
    public Button btnGenerateCSV;
    @FXML
    public TextArea txtLog;

    @FXML
    public TextField txtImgBBKey;

    @FXML
    public TextField txtValorDolar;

    @FXML
    public CheckBox chkAddPrices;

    private Map<String, List<Set>> setMap;

    @FXML
    public void initialize(){
        this.setMap =  scryfallHelper.getSetsDividedByType();
        this.cboSetType.setItems(prepareSetTypeList());
        this.cboSetType.setOnAction(actionEvent -> this.cboSet.setItems(changeSetCombo()));
        this.btnGenerateCSV.setOnAction(this::startGeneration);
        this.btnClearForm.setOnAction(this::clearForm);
        this.btnUpdateDB.setOnAction(this::updateDB);
        this.chkAddPrices.setOnAction(this::activateTxtValorDolar);
        this.txtValorDolar.setDisable(true);
    }

    private void activateTxtValorDolar(ActionEvent actionEvent) {
        this.txtValorDolar.setDisable(!this.chkAddPrices.isSelected());

    }

    private void startGeneration(ActionEvent actionEvent) {
        if (this.txtImgBBKey.getText().isEmpty()){
            log.error("No se ha ingresado una API Key para iniciar la ejecución, no se subirán imágenes");
        }
        if(checkIfTxtDolarIsValid()){
            printToLogView("No se agrego valor dolar del día, o no es correcto");
        }
        Thread newThread = new Thread(() -> {
            try {
                disableControls();
                generateCSV();
            } catch (Exception e){
                   log.error("Error generating CSV...");
                   log.error(e.getMessage());
            } finally {
                enableControls();
            }
        });
        newThread.start();
    }

    private boolean checkIfTxtDolarIsValid() {
        if (this.chkAddPrices.isSelected()
                && this.txtValorDolar.getText().isEmpty()){
            try {
                Integer.parseInt(this.txtValorDolar.getText());
            } catch (Exception e) {
                return false;
            }
        }
        return false;


    }

    private void enableControls() {
        this.btnGenerateCSV.setDisable(false);
        this.btnUpdateDB.setDisable(false);
        this.btnClearForm.setDisable(false);
        this.cboSetType.setDisable(false);
        this.cboSet.setDisable(false);
        this.txtImgBBKey.setDisable(false);
    }

    private void disableControls() {
        this.btnGenerateCSV.setDisable(true);
        this.btnUpdateDB.setDisable(true);
        this.btnClearForm.setDisable(true);
        this.cboSetType.setDisable(true);
        this.cboSet.setDisable(true);
        this.txtImgBBKey.setDisable(true);
    }


    private void updateDB(ActionEvent actionEvent) {
        Thread newThread = new Thread(() -> {
            try {
                disableControls();
                downloadCSV();
            } catch (Exception e){
                log.error("Error generating CSV...");
                log.error(e.getMessage());
            } finally {
                enableControls();
            }
        });
        newThread.start();

    }

    private void downloadCSV(){
        try{
            printToLogView("Updating base file");
            downloadUtils.saveJSONFile(scryfallHelper.getAllCardsURL());
            printToLogView("File downloaded successfully");
        } catch (Exception ex){
            printToLogView("Something happened while downloading the new file");
            printToLogView(ex.getMessage());
        }
    }

    private void clearForm(ActionEvent actionEvent) {
        this.cboSet.setItems(FXCollections.observableList(new ArrayList<>()));
        this.cboSetType.getSelectionModel().clearSelection();
    }

    private void fillCsvWithPrices(){
        try{
            printToLogView("Starting filling process... please wait a few minutes");
            String filePath = "";
            List<CSVModel> cardList = csvHelper.csvModelToCardList(filePath);

        } catch (FileNotFoundException e) {
            showMessage("RIP", "Something happened", "The file could not be found");

            throw new RuntimeException(e);
        }


    }

    private void generateCSV() {
        try {
            printToLogView("Starting... please wait a few minutes");
            String setName = this.cboSet.getValue();
            String set = this.setMap.get(cboSetType.getValue()).stream().filter(m -> m.getName().equalsIgnoreCase(setName)).findFirst().get().getCode();
            List<Card> cardsList = scryfallHelper.getCardsFromJsonURL(scryfallHelper.getAllCardsURL(), set);
            if (!cardsList.isEmpty()) {
                Map<String, String> cardNames;
                if(this.chkUploadImages.isSelected()){
                    printToLogView("Generating images...");
                    printToLogView("getting " + cardsList.size() + " cards images");
                    cardNames = scryfallHelper.getOracleCardsImages(cardsList);
                    printToLogView("processing card images");
                    cardImageHelper.createJumpsellerImages(cardNames);
                    printToLogView("uploading jumpseller images to image server");
                    ftpHelper.uploadImages(cardNames, this.txtImgBBKey.getText());
                    printToLogView("updating csv file with card images urls");
                    csvHelper.updateImagesUris(cardsList, cardNames);
                }
                if(chkAddPrices.isSelected()){
                    List<cl.rivendel.csv.model.mtgjson.Card> cards = mtgJsonHelper.getCardsFromSetJSON(set);
                    mtgJsonHelper.replaceUUID(cardsList, cards);
                    Map<String, Float> cardPrices = mtgJsonHelper.getCardPrices();
                    mtgJsonHelper.mergePrices(cardsList, cardPrices, Integer.parseInt(this.txtValorDolar.getText()));
                    printToLogView("Cantidad de cartas con precio recuperadas: "+cards.size());
                }
                printToLogView("creating csv models");
                List<CSVModel> listCSVModel = csvHelper.cardListToCsvModelList(cardsList);
                printToLogView("creating jumpseller csv file");
                csvHelper.generateJumpSellerCSV(listCSVModel, ".\\" + cardsList.get(0).getSet() + "-list.csv");
                printToLogView("File name: "+cardsList.get(0).getSet() + "-list.csv");
            }
            printToLogView("CSV File generation finished! :D");

        } catch (Exception e){
            showMessage("RIP", "Something happened", "Something exploded while trying to generate the file");
            throw new RuntimeException(e);
        }
    }

    private ObservableList<String> prepareSetTypeList() {
        return FXCollections.observableList(new ArrayList<>(this.setMap.keySet()));
    }

    private ObservableList<String> changeSetCombo(){
        if(this.cboSetType.getValue()!= null)
            return FXCollections.observableList(new ArrayList<>(
                    this.setMap.get(this.cboSetType.getValue())
                            .stream()
                            .map(Set::getName)
                            .collect(Collectors.toList())
            ));
        else
            return FXCollections.observableList(new ArrayList<>());
    }

    private void printToLogView(String logString){

        log.info(logString);
        this.txtLog.appendText(logString+"\n");
    }

    private void showMessage(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

}
