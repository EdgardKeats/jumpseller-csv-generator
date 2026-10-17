package cl.rivendel.csv.ui;

import cl.rivendel.csv.helper.*;
import cl.rivendel.csv.model.LogicDTO;
import cl.rivendel.csv.model.scryfall.Set;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleUIController {
    private final HostServices hostServices;
    private final ScryfallHelper scryfallHelper;
    private final LogicHelper logicHelper;

    private static final Logger log = LoggerFactory.getLogger(SimpleUIController.class);


    SimpleUIController(HostServices hostServices, ScryfallHelper scryfallHelper, LogicHelper logicHelper) {
        this.hostServices = hostServices;
        this.scryfallHelper = scryfallHelper;
        this.logicHelper = logicHelper;
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
                  log.info("No se ha ingresado una API Key para iniciar la ejecución, no se subirán imágenes");
              }
              if(checkIfTxtDolarIsValid()){
                  log.info("No se agrego valor dolar del día, o no es correcto");
              }

              disableControls();
              //prepare DTO
              LogicDTO dto = LogicDTO
                      .builder()
                      .addPrices(this.chkAddPrices.isSelected())
                      .setValue(this.cboSet.getValue())
                      .setType(this.setMap.get(cboSetType.getValue()).stream().filter(m -> m.getName().equalsIgnoreCase(this.cboSet.getValue()))
                              .findFirst().get().getCode())
                      .imbBBKey(this.txtImgBBKey.getText())
                      .uploadImages(this.chkUploadImages.isSelected())
                      .dolarValue(this.txtValorDolar.getText())
                      .build();


              Task<String> backgroundTask = new Task<String>() {
                  @Override
                  protected String call() throws Exception {
                      logicHelper.generateCSV(dto);
                      return "";
                  }
              };

              backgroundTask.setOnSucceeded( event -> {
                  enableControls();

             }); //do nothing special
              backgroundTask.setOnFailed( event-> {
                  showMessage("RIP", "Something happened", "Something exploded while trying to generate the file");
                  enableControls();
              });

              new Thread(backgroundTask).start();
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
                logicHelper.downloadCSV();
            } catch (Exception e){
                log.error("Error generating CSV...");
                log.error(e.getMessage());
            } finally {
                enableControls();
            }
        });
        newThread.start();

    }

    private void clearForm(ActionEvent actionEvent) {
        this.cboSet.setItems(FXCollections.observableList(new ArrayList<>()));
        this.cboSetType.getSelectionModel().clearSelection();
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
