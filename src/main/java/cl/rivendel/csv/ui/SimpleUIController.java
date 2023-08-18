package cl.rivendel.csv.ui;

import cl.rivendel.csv.helper.CSVHelper;
import cl.rivendel.csv.helper.CardImageHelper;
import cl.rivendel.csv.helper.ScryfallHelper;
import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import cl.rivendel.csv.model.scryfall.Set;
import cl.rivendel.csv.service.FTPClient;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleUIController {
    private HostServices hostServices;
    private ScryfallHelper scryfallHelper;
    private CardImageHelper cardImageHelper;
    private FTPClient ftpHelper;
    private CSVHelper csvHelper;


    SimpleUIController(HostServices hostServices, ScryfallHelper scryfallHelper, CardImageHelper cardImageHelper, FTPClient ftpHelper, CSVHelper csvHelper) {
        this.hostServices = hostServices;
        this.scryfallHelper = scryfallHelper;
        this.cardImageHelper = cardImageHelper;
        this.ftpHelper = ftpHelper;
        this.csvHelper = csvHelper;
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

    private Map<String, List<Set>> setMap;

    @FXML
    public void initialize(){
        this.setMap =  scryfallHelper.getSetsDividedByType();
        this.cboSetType.setItems(prepareSetTypeList());
        this.cboSetType.setOnAction(actionEvent -> this.cboSet.setItems(changeSetCombo()));
        this.btnGenerateCSV.setOnAction(this::generateCSV);
        this.btnClearForm.setOnAction(this::clearForm);
        this.btnUpdateDB.setOnAction(this::updateDB);
    }

    private void updateDB(ActionEvent actionEvent) {
        try{
            printToLogView("Updating base file");
            scryfallHelper.saveJSONFile(scryfallHelper.getAllCardsURL());
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

    private void generateCSV(ActionEvent actionEvent) {
        try {

            printToLogView("Starting... please wait a few minutes");
            String setName = this.cboSet.getValue();
            String set = this.setMap.get(cboSetType.getValue()).stream().filter(m -> m.getName().equalsIgnoreCase(setName)).findFirst().get().getCode();
            List<Card> cardsList = scryfallHelper.getSetCards(scryfallHelper.getAllCardsURL(), set);
            if (!cardsList.isEmpty()) {
                printToLogView("Generating images...");
                Map<String, String> cardNames;
                if(this.chkUploadImages.isSelected()){
                    printToLogView("getting " + cardsList.size() + " cards images");
                    cardNames = scryfallHelper.getOracleCardsImages(cardsList);
                    printToLogView("processing card images");
                    cardImageHelper.createJumpsellerImages(cardNames);
                    printToLogView("uploading jumpseller images to image server");
                    ftpHelper.uploadImages(cardNames);
                    printToLogView("updating csv file with card images urls");
                    csvHelper.updateImagesUris(cardsList, cardNames);
                }
                printToLogView("creating csv models");
                List<CSVModel> listCSVModel = csvHelper.cardListToCsvModelList(cardsList);
                printToLogView("creating jumpseller csv file");
                csvHelper.generateJumpSellerCSV(listCSVModel, ".\\" + cardsList.get(0).getSet() + "-list.csv");
                printToLogView("File name: "+cardsList.get(0).getSet() + "-list.csv");
            }
            printToLogView("CSV File generation finished! :D");

        } catch (Exception e){
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

    private void printToLogView(String log){
        StringBuilder sb = new StringBuilder();
        if(!txtLog.getText().isEmpty()) {
            sb.append(this.txtLog.getText());
            sb.append("\n");
        }
        sb.append(log);
        this.txtLog.setText(sb.toString());

    };

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
