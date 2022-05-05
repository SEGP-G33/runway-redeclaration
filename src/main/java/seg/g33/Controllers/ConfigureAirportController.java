package seg.g33.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.App;
import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Runway;
import seg.g33.Entitites.RunwayParameters;
import seg.g33.Entitites.RunwaySection;
import seg.g33.Helpers.Constants;
import seg.g33.Helpers.Validator;
import seg.g33.XMLParsing.XMLWriting;

import java.io.File;


public class ConfigureAirportController {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(ConfigureAirportController.class);

    /**
     * The airport instance to be built manually from the inputs
     */
    private Airport airport;

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
                    // Need to enable R2 before enabling R3.
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
            App.setRoot(Constants.getSelectAirportFXML());
        }
        catch (Exception e) {
            logger.error("Error Going to select-airport Scene from ConfigureAirportController");
        }
    }

    /**
     * Saves the airport details to an XML File.
     */
    @FXML void handleSaveAirportButtonClicked(ActionEvent event) {
        airport = configureAirport();

        // Some fields weren't entered so alert was shown and airport returned null.
        if (airport == null) {
            return;
        }

        // All fields are entered okay, continue with saving
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Save");
        directoryChooser.setInitialDirectory(new File(App.getAppDirectory()));
        File directory = directoryChooser.showDialog(App.getPrimaryStage());

        var xmlWriter = new XMLWriting();
        var filename = directory.getAbsolutePath().concat("/" + airport.getShortcode() + ".xml");
        System.out.println("Saving Airport " + airport + " at location " + filename);
        xmlWriter.createAirportXMLFile(airport, filename);

        var alert = new Alert(Alert.AlertType.INFORMATION, "File " + airport.getShortcode() + ".xml written.", ButtonType.CANCEL);
        alert.showAndWait();
    }

    /**
     * Builds a fully configured Airport instance based on the inputs from the UI and the user.
     * This airport instance will be then saved to an XML file.
     * @return An instance of the Airport class
     */
    public Airport configureAirport() {
        if (!Validator.areAllFieldsValid(new TextField[] { airportNameField, airportCodeField })) {
            showInputsAlert("the name and the code");
            return null;
        }

        airport = new Airport(airportNameField.getText(), airportCodeField.getText());

        // Runway 1 will always be there.
        if (Validator.textFieldHasBlankText(runway1NameField)) {
            showInputsAlert("the runway 1 name");
            return null;
        }
        Runway runway1 = new Runway(runway1NameField.getText());
        var r1s1 = buildR1S1(runway1);
        var r1s2 = buildR1S2(runway1);

        // if these are null there was an error on the input fields, so show alert and return null.
        if (r1s1 == null || r1s2 == null) {
            showInputsAlert("the runway 1 properties");
            return null;
        }

        runway1.addRunwaySection(buildR1S1(runway1));
        runway1.addRunwaySection(buildR1S2(runway1));
        airport.addRunway(runway1);

        // Runway 2 and 3 could not be there.
        if (enableR2Check.isSelected()) {
            if (Validator.textFieldHasBlankText(runway2NameField)) {
                showInputsAlert("the runway 2 name");
                return null;
            }
            Runway runway2 = new Runway(runway2NameField.getText());
            var r2s1 = buildR2S1(runway2);
            var r2s2 = buildR2S2(runway2);

            // if these are null there was an error on the input fields, so show alert and return null.
            if (r2s1 == null || r2s2 == null) {
                showInputsAlert("the runway 2 properties");
                return null;
            }

            runway2.addRunwaySection(r2s1);
            runway2.addRunwaySection(r2s2);
            airport.addRunway(runway2);
        }
        if (enableR3Check.isSelected()) {
            if (Validator.textFieldHasBlankText(runway3NameField)) {
                showInputsAlert("the runway 3 name");
                return null;
            }
            Runway runway3 = new Runway(runway3NameField.getText());

            var r3s1 = buildR3S1(runway3);
            var r3s2 = buildR3S2(runway3);

            // if these are null there was an error on the input fields, so show alert and return null.
            if (r3s1 == null || r3s2 == null) {
                showInputsAlert("the runway 3 properties");
                return null;
            }

            runway3.addRunwaySection(r3s1);
            runway3.addRunwaySection(r3s2);
            airport.addRunway(runway3);
        }

        return airport;
    }

    /**
     * Builds the first section of the first runway
     * @param runway1 the first runway of the Airport
     * @return The RunwaySection class instance for r1s1
     */
    private RunwaySection buildR1S1(Runway runway1) {
        RunwaySection r1s1;
        try {
            var r1s1TORA = number(r1s1TORAField.getText());
            var r1s1TODA = number(r1s1TODAField.getText());
            var r1s1ASDA = number(r1s1ASDAField.getText());
            var r1s1LDA = number(r1s1LDAField.getText());
            var r1s1Angle = Integer.parseInt(r1s1AngleField.getText());
            var r1s1Direction = (r1s1DirectionField.getText()).charAt(0);
            RunwayParameters r1s1Params = new RunwayParameters(r1s1TORA, r1s1ASDA, r1s1TODA, r1s1LDA);
            r1s1 = new RunwaySection(runway1, r1s1Angle, r1s1Direction, r1s1Params);
            if (Validator.runwayParametersAreValid(r1s1)){
                return r1s1;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Builds the second section of the first runway
     * @param runway1 the first runway of the Airport
     * @return The RunwaySection class instance for r1s2
     */
    private RunwaySection buildR1S2(Runway runway1) {
        RunwaySection r1s2;
        try {
            var r1s2TORA = number(r1s2TORAField.getText());
            var r1s2TODA = number(r1s2TODAField.getText());
            var r1s2ASDA = number(r1s2ASDAField.getText());
            var r1s2LDA = number(r1s2LDAField.getText());
            var r1s2Angle = Integer.parseInt(r1s2AngleField.getText());
            var r1s2Direction = (r1s2DirectionField.getText()).charAt(0);

            RunwayParameters r1s2Params = new RunwayParameters(r1s2TORA, r1s2ASDA, r1s2TODA, r1s2LDA);
            r1s2 = new RunwaySection(runway1, r1s2Angle, r1s2Direction, r1s2Params);
            if (Validator.runwayParametersAreValid(r1s2)){
                return r1s2;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Builds the first section of the second runway
     * @param runway2 the second runway of the Airport
     * @return The RunwaySection class instance for r2s1
     */
    private RunwaySection buildR2S1(Runway runway2) {
        RunwaySection r2s1;
        try {
            var tora = number(r2s1TORAField.getText());
            var toda = number(r2s1TODAField.getText());
            var asda = number(r2s1ASDAField.getText());
            var lda = number(r2s1LDAField.getText());
            var angle = Integer.parseInt(r2s1AngleField.getText());
            var direction = (r2s1DirectionField.getText()).charAt(0);

            RunwayParameters r1s2Params = new RunwayParameters(tora, asda, toda, lda);
            r2s1 = new RunwaySection(runway2, angle, direction, r1s2Params);
            if (Validator.runwayParametersAreValid(r2s1)){
                return r2s1;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Builds the second section of the second runway
     * @param runway2 the second runway of the Airport
     * @return The RunwaySection class instance for r2s2
     */
    private RunwaySection buildR2S2(Runway runway2) {
        RunwaySection r2s2;
        try {
            var tora = number(r2s2TORAField.getText());
            var toda = number(r2s2TODAField.getText());
            var asda = number(r2s2ASDAField.getText());
            var lda = number(r2s2LDAField.getText());
            var angle = Integer.parseInt(r2s2AngleField.getText());
            var direction = (r2s2DirectionField.getText()).charAt(0);

            RunwayParameters r1s2Params = new RunwayParameters(tora, asda, toda, lda);
            r2s2 = new RunwaySection(runway2, angle, direction, r1s2Params);
            if (Validator.runwayParametersAreValid(r2s2)){
                return r2s2;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Builds the first section of the third runway
     * @param runway3 the third runway of the Airport
     * @return The RunwaySection class instance for r3s1
     */
    private RunwaySection buildR3S1(Runway runway3) {
        RunwaySection r3s1;
        try {
            var tora = number(r3s1TORAField.getText());
            var toda = number(r3s1TODAField.getText());
            var asda = number(r3s1ASDAField.getText());
            var lda = number(r3s1LDAField.getText());
            var angle = Integer.parseInt(r3s1AngleField.getText());
            var direction = (r3s1DirectionField.getText()).charAt(0);

            RunwayParameters r1s2Params = new RunwayParameters(tora, asda, toda, lda);
            r3s1 = new RunwaySection(runway3, angle, direction, r1s2Params);
            if (Validator.runwayParametersAreValid(r3s1)){
                return r3s1;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Builds the second section of the third runway
     * @param runway3 the third runway of the Airport
     * @return The RunwaySection class instance for r3s2
     */
    private RunwaySection buildR3S2(Runway runway3) {
        RunwaySection r3s2;
        try {
            var tora = number(r3s2TORAField.getText());
            var toda = number(r3s2TODAField.getText());
            var asda = number(r3s2ASDAField.getText());
            var lda = number(r3s2LDAField.getText());
            var angle = Integer.parseInt(r3s2AngleField.getText());
            var direction = (r3s2DirectionField.getText()).charAt(0);

            RunwayParameters r1s2Params = new RunwayParameters(tora, asda, toda, lda);
            r3s2 = new RunwaySection(runway3, angle, direction, r1s2Params);
            if (Validator.runwayParametersAreValid(r3s2)){
                return r3s2;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    private void showInputsAlert(String forInput) {
        var alert = new Alert(Alert.AlertType.ERROR, "Please make sure all fields for " + forInput + " are valid. ", ButtonType.CANCEL);
        alert.showAndWait();
    }

    /**
     * Turns a string input into a Double
     * @param input the string
     * @return a Double representing the value of the string
     */
    private Double number(String input) throws NumberFormatException {
        return Double.parseDouble(input);
    }

    @FXML
    void handleMenuBarQuit(ActionEvent event) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

    /**
     * UI Elements
     */
    @FXML private TextField airportNameField;
    @FXML private TextField airportCodeField;
    @FXML private CheckBox enableR1Check;
    @FXML private TextField runway1NameField;
    @FXML private TextField r1s1AngleField;
    @FXML private TextField r1s1DirectionField;
    @FXML private TextField r1s1TORAField;
    @FXML private TextField r1s1TODAField;
    @FXML private TextField r1s1ASDAField;
    @FXML private TextField r1s1LDAField;
    @FXML private TextField r1s2AngleField;
    @FXML private TextField r1s2DirectionField;
    @FXML private TextField r1s2TORAField;
    @FXML private TextField r1s2TODAField;
    @FXML private TextField r1s2ASDAField;
    @FXML private TextField r1s2LDAField;
    @FXML private CheckBox enableR2Check;
    @FXML private TextField runway2NameField;
    @FXML private HBox runway2HboxControls;
    @FXML private TextField r2s1AngleField;
    @FXML private TextField r2s1DirectionField;
    @FXML private TextField r2s1TORAField;
    @FXML private TextField r2s1TODAField;
    @FXML private TextField r2s1ASDAField;
    @FXML private TextField r2s1LDAField;
    @FXML private TextField r2s2AngleField;
    @FXML private TextField r2s2DirectionField;
    @FXML private TextField r2s2TORAField;
    @FXML private TextField r2s2TODAField;
    @FXML private TextField r2s2ASDAField;
    @FXML private TextField r2s2LDAField;
    @FXML private CheckBox enableR3Check;
    @FXML private TextField runway3NameField;
    @FXML private HBox runway3HboxControls;
    @FXML private TextField r3s1AngleField;
    @FXML private TextField r3s1DirectionField;
    @FXML private TextField r3s1TORAField;
    @FXML private TextField r3s1TODAField;
    @FXML private TextField r3s1ASDAField;
    @FXML private TextField r3s1LDAField;
    @FXML private TextField r3s2AngleField;
    @FXML private TextField r3s2DirectionField;
    @FXML private TextField r3s2TORAField;
    @FXML private TextField r3s2TODAField;
    @FXML private TextField r3s2ASDAField;
    @FXML private TextField r3s2LDAField;

}



