package seg.g33.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.App;
import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Obstacle;
import seg.g33.Helpers.AirportPresets;
import seg.g33.Helpers.ObstaclePresets;

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

        var codes = (airports.stream().map((airport -> airport.getShortcode()))).collect(Collectors.toList());

        airportCodesObservableList = FXCollections.observableList(codes);
        airportSelectionBox.setItems(airportCodesObservableList);

        airportSelectionBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                setupUIForSelectedAirport(t1);
            }
        });
    }

    private void setupUIForSelectedAirport(String airportCode) {
        for (Airport airport : airports) {
            if (airport.getShortcode().equals(airportCode)) {
                selectedAirport = airport;
            }
        }

        System.out.println("Selected Airport: " + selectedAirport);

        airportNameField.textProperty().set(selectedAirport.getName());
        airportCodeField.textProperty().set(selectedAirport.getShortcode());
        numberOfRunwaysField.textProperty().set(String.valueOf(selectedAirport.getAirportRunways().size()));
    }

    @FXML
    void handleAddAirportClicked(ActionEvent event) {
        logger.info("Adding Airport Manually");
    }

    @FXML
    void handleBackButtonClicked(ActionEvent event) throws Exception {
        logger.info("Going back from SelectAirportController");
        App.setRoot("launch");
    }

    @FXML
    void handleChooseFileClicked(ActionEvent event) {
        logger.info("Handling File Selection");
    }

    @FXML
    void handleConfirmButtonClicked(ActionEvent event) {
        logger.info("Confirming Airport Selection");
    }

}

