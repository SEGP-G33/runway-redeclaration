package seg.g33.Controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.App;

public class ConfigureAirportController {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(ConfigureAirportController.class);

    @FXML private TextField airportNameField;
    @FXML private TextField airportCodeField;
    @FXML private CheckBox enableR1Check;
    @FXML private HBox runway1HboxControls;
    @FXML private CheckBox enableR2Check;
    @FXML private HBox runway2HboxControls;
    @FXML private CheckBox enableR3Check;
    @FXML private HBox runway3HboxControls;
    @FXML private TextField runway3NameField;
    @FXML private TextField runway2NameField;
    @FXML private TextField runway1NameField;

    @FXML
    protected void initialize() {
        setDisabledViews();
        setupListeners();
    }

    private void setupListeners() {
        enableR2Check.setOnAction((actionEvent) -> {
            if (enableR2Check.isSelected()) {
                runway2HboxControls.setDisable(false);
                runway2NameField.setDisable(false);
            }
            else {
                runway2HboxControls.setDisable(true);
                runway2NameField.setDisable(true);
            }
        });

        enableR3Check.setOnAction((actionEvent) -> {
            if (enableR3Check.isSelected()) {
                runway3HboxControls.setDisable(false);
                runway3NameField.setDisable(false);
            }
            else {
                runway3HboxControls.setDisable(true);
                runway3NameField.setDisable(true);
            }
        });
    }

    private void setDisabledViews() {
        enableR1Check.setSelected(true);
        enableR1Check.setDisable(true);
        runway2HboxControls.setDisable(true);
        runway3HboxControls.setDisable(true);
        runway2NameField.setDisable(true);
        runway3NameField.setDisable(true);
    }

    @FXML
    void handleBackButtonClicked(ActionEvent event)  {
        try {
            App.setRoot("select-airport");
        }
        catch (Exception e) {
            logger.error("Error Going to select-airport Scene from ConfigureAirportController");
        }
    }

    @FXML
    void handleConfirmButtonClicked(ActionEvent event) {

    }

}



