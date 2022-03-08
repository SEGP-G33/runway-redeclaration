package seg.g33.Controllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LaunchController {

    @FXML
    void handleLaunchClicked(ActionEvent event) {
        System.out.println("Launch Clicked...");
    }

    @FXML
    void handleQuitApplication(ActionEvent event) {
        Platform.exit();;
    }

}