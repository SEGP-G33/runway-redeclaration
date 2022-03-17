package seg.g33.Controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    /**
     * UI Elements
     */
    @FXML private TextField airportNameField;
    @FXML private TextField airportCodeField;
    @FXML private CheckBox enableR1Check;
    @FXML private TextField runway1NameField;
    @FXML private HBox runway1HboxControls;
    @FXML private TextField r1s1DirectionField;
    @FXML private TextField r1s1LengthField;
    @FXML private TextField r1s1ClearwayField;
    @FXML private TextField r1s1StopwayField;
    @FXML private TextField r1s1RESAField;
    @FXML private TextField r1s1TORAField;
    @FXML private TextField r1s1TODAField;
    @FXML private TextField r1s1ASDAField;
    @FXML private TextField r1s1LDAField;
    @FXML private TextField r1s1StripendField;
    @FXML private TextField r1s1DisplacedField;
    @FXML private TextField r1s2DirectionField;
    @FXML private TextField r1s2LengthField;
    @FXML private TextField r1s2ClearwayField;
    @FXML private TextField r1s2StopwayField;
    @FXML private TextField r1s2RESAField;
    @FXML private TextField r1s2TORAField;
    @FXML private TextField r1s2TODAField;
    @FXML private TextField r1s2ASDAField;
    @FXML private TextField r1s2LDAField;
    @FXML private TextField r1s2StripendField;
    @FXML private TextField r1s2DisplacedField;
    @FXML private CheckBox enableR2Check;
    @FXML private TextField runway2NameField;
    @FXML private HBox runway2HboxControls;
    @FXML private CheckBox enableR3Check;
    @FXML private TextField runway3NameField;
    @FXML private HBox runway3HboxControls;

    /**
     * JavaFX Initializer
     * Called as soon as the FXML file is loaded from the FXMLLoader.
     */
    @FXML protected void initialize() {
        setDisabledViews();
        setupListeners();
    }

    /**
     * Sets listeners for buttons, checkboxes.
     * Used to enable/disable controls for runways as well as alert logic.
     */
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
                if (!enableR2Check.isSelected()) {
                    enableR3Check.setSelected(false);
                    var alert = new Alert(Alert.AlertType.WARNING, "Enable R2 first before enabling R3.", ButtonType.CANCEL);
                    alert.showAndWait();
                } else {
                    runway3HboxControls.setDisable(false);
                    runway3NameField.setDisable(false);
                }
            }
            else {
                runway3HboxControls.setDisable(true);
                runway3NameField.setDisable(true);
            }
        });
    }

    /**
     * Disables the views that need to be disabled at init.
     */
    private void setDisabledViews() {
        enableR1Check.setSelected(true);
        enableR1Check.setDisable(true);
        runway2HboxControls.setDisable(true);
        runway3HboxControls.setDisable(true);
        runway2NameField.setDisable(true);
        runway3NameField.setDisable(true);
    }

    /**
     * Goes back to the SelectAirport Scene
     */
    @FXML void handleBackButtonClicked(ActionEvent event)  {
        try {
            App.setRoot("select-airport");
        }
        catch (Exception e) {
            logger.error("Error Going to select-airport Scene from ConfigureAirportController");
        }
    }

    /**
     * Saves the airport details to an XML File.
     */
    @FXML void handleSaveAirportButtonClicked(ActionEvent event) {

    }

}



