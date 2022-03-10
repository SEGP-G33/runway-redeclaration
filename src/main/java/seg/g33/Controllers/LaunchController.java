package seg.g33.Controllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.App;

import java.io.IOException;

public class LaunchController {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(LaunchController.class);

    /**
     * Handles clicking the Launch Application Button
     * @param event the ActionEvent event
     * @throws IOException If the FXML isn't properly there
     */
    @FXML
    private void handleLaunchClicked(ActionEvent event) throws IOException {
        logger.info("Launching Main Menu Window...");
        App.setRoot("main-menu");
    }

    /**
     * Handles clicking the Quit Application Button
     * Quits the application
     * @param event the ActionEvent event
     */
    @FXML
    private void handleQuitApplication(ActionEvent event) {
        Platform.exit();;
    }

}