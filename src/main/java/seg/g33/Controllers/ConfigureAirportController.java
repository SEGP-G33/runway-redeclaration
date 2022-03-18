package seg.g33.Controllers;

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
import seg.g33.Helpers.XMLWriting;

import java.io.File;

//
// TODO: All sorts of error handling needs to happen here.
//  Currently breaks if something is null or empty
//  Currently breaks if something expects a String but get a number or
//  expects a Double but gets a String that can't be parsed into a Double.
//
//
// TODO: Connect components for Runway2 and Runway3
//
public class ConfigureAirportController {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(ConfigureAirportController.class);

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
        airport = configureAirport();

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
        airport = new Airport(airportNameField.getText(), airportCodeField.getText());

        // Runway 1 will always be there.
        Runway runway1 = new Runway(runway1NameField.getText());
        runway1.addRunwaySection(buildR1S1(runway1));
        runway1.addRunwaySection(buildR1S2(runway1));
        airport.addRunway(runway1);

        // Runway 2 and 3 could not be there.
        if (enableR2Check.isSelected()) {
            Runway runway2 = new Runway(runway1NameField.getText());
            airport.addRunway(runway2);
        }
        if (enableR3Check.isSelected()) {
            Runway runway3 = new Runway(runway1NameField.getText());
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
        var r1s1TORA = number(r1s1TORAField.getText());
        var r1s1TODA = number(r1s1TODAField.getText());
        var r1s1ASDA = number(r1s1ASDAField.getText());
        var r1s1LDA = number(r1s1LDAField.getText());
        var r1s1Angle = Integer.parseInt(r1s1AngleField.getText());
        var r1s1Direction = (r1s1DirectionField.getText()).charAt(0);

        RunwayParameters r1s1Params = new RunwayParameters(r1s1TORA, r1s1ASDA, r1s1TODA, r1s1LDA);
        RunwaySection r1s1 = new RunwaySection(runway1, r1s1Angle, r1s1Direction, r1s1Params);
        return r1s1;
    }

    /**
     * Builds the second section of the first runway
     * @param runway1 the first runway of the Airport
     * @return The RunwaySection class instance for r1s2
     */
    private RunwaySection buildR1S2(Runway runway1) {
        var r1s2TORA = number(r1s2TORAField.getText());
        var r1s2TODA = number(r1s2TODAField.getText());
        var r1s2ASDA = number(r1s2ASDAField.getText());
        var r1s2LDA = number(r1s2LDAField.getText());
        var r1s2Angle = Integer.parseInt(r1s2AngleField.getText());
        var r1s2Direction = (r1s2DirectionField.getText()).charAt(0);

        RunwayParameters r1s2Params = new RunwayParameters(r1s2TORA, r1s2ASDA, r1s2TODA, r1s2LDA);
        RunwaySection r1s2 = new RunwaySection(runway1, r1s2Angle, r1s2Direction, r1s2Params);
        return r1s2;
    }

    /**
     * Turns a string input into a Double
     * @param input the string
     * @return a Double representing the value of the string
     */
    private Double number(String input) {
        return Double.parseDouble(input);
    }

    /**
     * UI Elements
     */
    @FXML
    private TextField airportNameField;

    @FXML
    private TextField airportCodeField;

    @FXML
    private CheckBox enableR1Check;

    @FXML
    private TextField runway1NameField;

    @FXML
    private HBox runway1HboxControls;

    @FXML
    private TextField r1s1AngleField;

    @FXML
    private TextField r1s1DirectionField;

    @FXML
    private TextField r1s1LengthField;

    @FXML
    private TextField r1s1ClearwayField;

    @FXML
    private TextField r1s1StopwayField;

    @FXML
    private TextField r1s1RESAField;

    @FXML
    private TextField r1s1TORAField;

    @FXML
    private TextField r1s1TODAField;

    @FXML
    private TextField r1s1ASDAField;

    @FXML
    private TextField r1s1LDAField;

    @FXML
    private TextField r1s1StripendField;

    @FXML
    private TextField r1s1DisplacedField;

    @FXML
    private TextField r1s2AngleField;

    @FXML
    private TextField r1s2DirectionField;

    @FXML
    private TextField r1s2LengthField;

    @FXML
    private TextField r1s2ClearwayField;

    @FXML
    private TextField r1s2StopwayField;

    @FXML
    private TextField r1s2RESAField;

    @FXML
    private TextField r1s2TORAField;

    @FXML
    private TextField r1s2TODAField;

    @FXML
    private TextField r1s2ASDAField;

    @FXML
    private TextField r1s2LDAField;

    @FXML
    private TextField r1s2StripendField;

    @FXML
    private TextField r1s2DisplacedField;

    @FXML
    private CheckBox enableR2Check;

    @FXML
    private TextField runway2NameField;

    @FXML
    private HBox runway2HboxControls;

    @FXML
    private CheckBox enableR3Check;

    @FXML
    private TextField runway3NameField;

    @FXML
    private HBox runway3HboxControls;

}



