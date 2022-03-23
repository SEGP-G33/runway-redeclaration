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
import seg.g33.Entitites.Airport;
import seg.g33.DataHolders.AirportPresets;
import seg.g33.DataHolders.Environment;
import seg.g33.XMLParsing.XMLReading;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class SelectAirportController {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(SelectAirportController.class);

    @FXML
    private ComboBox<String> airportSelectionBox;

    @FXML
    private TextField airportNameField;

    @FXML
    private TextField airportCodeField;

    @FXML
    private TextField numberOfRunwaysField;

    private AirportPresets airportPresets = new AirportPresets();
    private List<Airport> airports;
    private ObservableList<String> airportCodesObservableList;
    private Airport selectedAirport;

    @FXML
    protected void initialize() {
        airports = airportPresets.getAllAirportPresets();
        setAirportListsAndComboBox();

        airportSelectionBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                setupUIForSelectedAirport(t1);
            }
        });
    }

    private void setAirportListsAndComboBox() {
        var codes = (airports.stream().map((airport -> airport.getShortcode()))).collect(Collectors.toList());
        airportCodesObservableList = FXCollections.observableList(codes);
        airportSelectionBox.setItems(airportCodesObservableList);
    }

    private void setupUIForSelectedAirport(String airportCode) {
        for (Airport airport : airports) {
            if (airport.getShortcode().equals(airportCode)) {
                selectedAirport = airport;
            }
        }

        airportNameField.textProperty().set(selectedAirport.getName());
        airportCodeField.textProperty().set(selectedAirport.getShortcode());
        numberOfRunwaysField.textProperty().set(String.valueOf(selectedAirport.getAirportRunways().size()));
    }

    @FXML
    void handleAddAirportClicked(ActionEvent event) throws Exception {
        logger.info("Adding Airport Manually");
        App.setRoot("configure-airport");
    }

    @FXML
    void handleBackButtonClicked(ActionEvent event) throws Exception {
        logger.info("Going back from SelectAirportController");
        App.setRoot("launch");
    }

    @FXML
    void handleChooseFileClicked(ActionEvent event) {
        logger.info("Handling File Selection");

        FileChooser.ExtensionFilter xmlFileFilter = new FileChooser.ExtensionFilter("XML Files", "*EX2.xml");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an XML File");
        fileChooser.setInitialDirectory(new File(App.getAppDirectory()));
        fileChooser.getExtensionFilters().addAll(xmlFileFilter);
        File selectedFile = fileChooser.showOpenDialog(App.getPrimaryStage());

        configureSelectedFile(selectedFile);
    }

    private void configureSelectedFile(File selectedFile) {
        var xmlReading = new XMLReading();
        var newAirport = xmlReading.configureAirportFromXMLFile(selectedFile.getAbsolutePath());

        if (airportAlreadyExists(newAirport)) {
            var alert = new Alert(Alert.AlertType.ERROR, "You have already added this Airport", ButtonType.CANCEL);
            alert.showAndWait();
            return;
        }

        airports.add(newAirport);
        setAirportListsAndComboBox();
        setupUIForSelectedAirport(newAirport.getShortcode());
    }

    private boolean airportAlreadyExists(Airport airport) {
        for (Airport airp : airports) {
            if (airp.getShortcode().equals(airport.getShortcode())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void handleConfirmButtonClicked(ActionEvent event) throws Exception {
        logger.info("Confirming Airport Selection");
        if (selectedAirport != null){
            var env = Environment.getInstance();
            env.setSelectedAirport(selectedAirport);
            App.setRoot("calculator");
        } else {
            var alert = new Alert(Alert.AlertType.WARNING, "Please select an airport.", ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

}

