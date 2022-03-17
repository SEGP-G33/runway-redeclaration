package seg.g33.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.App;

public class ConfigureAirportController {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(ConfigureAirportController.class);

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

