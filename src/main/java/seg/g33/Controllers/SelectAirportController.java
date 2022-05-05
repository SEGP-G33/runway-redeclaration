package seg.g33.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.App;
import seg.g33.DataHolders.Notify;
import seg.g33.Entitites.Airport;
import seg.g33.DataHolders.AirportPresets;
import seg.g33.DataHolders.Environment;
import seg.g33.Helpers.Constants;
import seg.g33.Helpers.Validator;
import seg.g33.XMLParsing.XMLReading;

import seg.g33.Controllers.CalculatorController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SelectAirportController {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(SelectAirportController.class);

    /**
     * FXML UI elements
     */
    @FXML private ComboBox<String> airportSelectionBox;
    @FXML private TextField airportNameField;
    @FXML private TextField airportCodeField;
    @FXML private TextField numberOfRunwaysField;

    /**
     * Holds all airports visible to the application.
     * Required properties for the ComboBox and selection process to work properly.
     */
    private AirportPresets airportPresets = new AirportPresets();
    private List<Airport> airports;
    private ObservableList<String> airportCodesObservableList;

    /**
     * The airport currently selected by the user.
     */
    private Airport selectedAirport;

    /**
     * JavaFX Initializer
     * Called as soon as the FXML file is loaded from the FXMLLoader.
     */
    @FXML
    protected void initialize() {
        airports = airportPresets.getAllAirportPresets();
        setAirportListsAndComboBox();

        // Change UI when a new element is selected from the dropdown box.
        airportSelectionBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                setupUIForSelectedAirport(t1);
            }
        });
    }

    /**
     * Updates the airport property lists and the ComboBox.
     */
    private void setAirportListsAndComboBox() {
        var codes = (airports.stream().map((airport -> airport.getShortcode()))).collect(Collectors.toList());
        airportCodesObservableList = FXCollections.observableList(codes);
        airportSelectionBox.setItems(airportCodesObservableList);
    }

    /**
     * Populates the UI elements for the currently selected airport.
     * @param airportCode the shortcode of the selected airport.
     *                    This will be used to find the Airport instance currently selected and update the UI.
     */
    private void setupUIForSelectedAirport(String airportCode) {
        // search through airports to find the selected instance
        for (Airport airport : airports) {
            if (airport.getShortcode().equals(airportCode)) {
                selectedAirport = airport;
            }
        }

        // update all UI elements based on the selected airport instance.
        airportNameField.textProperty().set(selectedAirport.getName());
        airportCodeField.textProperty().set(selectedAirport.getShortcode());
        numberOfRunwaysField.textProperty().set(String.valueOf(selectedAirport.getAirportRunways().size()));
    }

    /**
     * Called when the "Configure Airport" button is pressed.
     * Presents the ConfigureAirport Scene
     * @param event the button click ActionEvent
     */
    @FXML
    void handleAddAirportClicked(ActionEvent event)  {
        logger.info("Adding Airport Manually");
        try {
            App.setRoot(Constants.getConfigureAirportFXML());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the "Go Back" button is pressed.
     * Presents the Launch screen.
     * @param event the button click ActionEvent
     */
    @FXML
    void handleBackButtonClicked(ActionEvent event)  {
        logger.info("Going back from SelectAirportController");
        try {
            App.setRoot(Constants.getLauncherFXML());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the "Import File" button is pressed.
     * Presents a FileChooser component to select a file to be imported.
     * @param event the button click ActionEvent.
     */
    @FXML
    void handleChooseFileClicked(ActionEvent event) {
        logger.info("Handling File Selection");

        // Only allow .xml files.
        FileChooser.ExtensionFilter xmlFileFilter = new FileChooser.ExtensionFilter("XML Files", "*.xml");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an XML File");
        fileChooser.setInitialDirectory(new File(App.getAppDirectory()));
        fileChooser.getExtensionFilters().addAll(xmlFileFilter);

        // The file selected from the FileChooser.
        File selectedFile = fileChooser.showOpenDialog(App.getPrimaryStage());
        configureSelectedFile(selectedFile);
    }

    /**
     * Works with the file selected by the user.
     * Checks if it's an XML file and if so it parses it.
     * Checks if the airport is already added in the list.
     * Updates the Airport properties, lists and UI to match the imported airport.
     * @param selectedFile the file selected by the user.
     */
    private void configureSelectedFile(File selectedFile) {
        // Is the file a valid XML File?
        if (!Validator.isXMLFile(selectedFile.getName())) {
            var alert = new Alert(Alert.AlertType.ERROR, "You need to import a .XML File", ButtonType.CANCEL);
            alert.showAndWait();
            return;
        }

        var xmlReading = new XMLReading();
        var newAirport = xmlReading.configureAirportFromXMLFile(selectedFile.getAbsolutePath());

        // Is the airport already in the list.
        if (airportAlreadyExists(newAirport)) {
            var alert = new Alert(Alert.AlertType.ERROR, "You have already added this Airport", ButtonType.CANCEL);
            alert.showAndWait();
            return;
        }

        // Updates properties and UI
        airports.add(newAirport);
        setAirportListsAndComboBox();
        setupUIForSelectedAirport(newAirport.getShortcode());
    }

    /**
     * Checks if a given airport is already present in the list
     *      Check is performed using the shortcode of the airport.
     *      It assumes no 2 airports have the same code.
     * @param airport the airport to be checked
     * @return true if it already exists, false otherwise.
     */
    private boolean airportAlreadyExists(Airport airport) {
        for (Airport airp : airports) {
            if (airp.getShortcode().equals(airport.getShortcode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Called when the "Confirm Selection" button is pressed.
     *      Presents the main Calculator scene.
     * @param event the button click ActionEvent.
     * @throws Exception In case the FXML file isn't there. Should never happen.
     */
    @FXML
    void handleConfirmButtonClicked(ActionEvent event) throws Exception {
        logger.info("Confirming Airport Selection");
        if (selectedAirport != null){
            // Save airport to Environment
            Environment.getInstance().setSelectedAirport(selectedAirport);

            // Present Calculator scene
            App.setRoot(Constants.getCalculatorFXML());
        } else {
            var alert = new Alert(Alert.AlertType.WARNING, "Please select an airport.", ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

}

