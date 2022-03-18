package seg.g33.Controllers;

import java.io.IOException;
import java.lang.String;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import seg.g33.App;
import seg.g33.Entitites.Obstacle;
import seg.g33.Helpers.ObstaclePresets;
import seg.g33.Helpers.XMLWriting;

public class MainController {

    /**
     * XMLWriter used to write a new obstacle
     */
    private XMLWriting xmlWriting = new XMLWriting();

    /**
     * Handles all Obstacle presets in the app.
     */
    private ObstaclePresets obstaclePresets = new ObstaclePresets();

    /**
     * Properties used for the JavaFX ComboBox to work properly.
     */
    private List<Obstacle> obstacles;
    private ObservableList<String> obstacleNamesObservableList;

    /**
     * The obstacle that is currently added in the the application.
     */
    private Obstacle selectedObstacle;

    /**
     * JavaFX Initializer
     * Called as soon as the FXML file is loaded from the FXMLLoader.
     */
    @FXML
    protected void initialize() {
        obstacles = obstaclePresets.getAllObstaclePresets();

        var names = (obstacles.stream().map((obstacle -> obstacle.getName()))).collect(Collectors.toList());

        obstacleNamesObservableList = FXCollections.observableList(names);
        selectObstacleComboBox.setItems(obstacleNamesObservableList);

        selectObstacleComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                setElementsForSelectedObstacle(t1);
            }
        });
    }

    /**
     * Updated the UI for the selected obstacle element.
     * @param obstacleName The name of the obstacle that it currently selected.
     */
    private void setElementsForSelectedObstacle(String obstacleName) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getName().equals(obstacleName)) {
                selectedObstacle = obstacle;
            }
        }

        var name = selectedObstacle.getName();
        var height = String.valueOf(selectedObstacle.getHeight());
        var center = String.valueOf(selectedObstacle.getCenterDistance());
        var left = String.valueOf(selectedObstacle.getLeftDistance());
        var right = String.valueOf(selectedObstacle.getRightDistance());

        obstacleNameField.textProperty().set(name);
        field_obsheight.textProperty().set(height);
        field_centerline.textProperty().set(center);
        field_threshold_1.textProperty().set(left);
        field_threshold_2.textProperty().set(right);
    }

    @FXML
    private ScrollPane root_scroll;

    @FXML
    private VBox root_vbox;

    @FXML
    private Button button_back;

    @FXML
    private Button button_import_runway;

    @FXML
    private Button button_import_obstacle;

    @FXML
    private Button button_export_runway;

    @FXML
    private Button button_export_obstacle;

    @FXML
    private Button button_export_text;

    @FXML
    private Label label_threshold_designator_1;

    @FXML
    private Slider slider_threshold;

    @FXML
    private Label label_threshold_designator_2;

    @FXML
    private TextField obstacleNameField;

    @FXML
    private TextField field_obsheight;

    @FXML
    private TextField field_centerline;

    @FXML
    private ComboBox<?> cbox_centreline;

    @FXML
    private String cbox_direction_1;

    @FXML
    private String cbox_direction_2;

    @FXML
    private String cbox_direction_3;

    @FXML
    private String cbox_direction_4;

    @FXML
    private Label label_distance_designator_1;

    @FXML
    private TextField field_threshold_1;

    @FXML
    private Label label_distance_designator_2;

    @FXML
    private TextField field_threshold_2;

    @FXML
    private TextField field_original_lda;

    @FXML
    private TextField field_original_asda;

    @FXML
    private TextField field_original_toda;

    @FXML
    private TextField field_original_tora;

    @FXML
    private Pane pane_flash_7;

    @FXML
    private TextField field_results_tora_;

    @FXML
    private Pane pane_flash_8;

    @FXML
    private Pane pane_flash_10;

    @FXML
    private TextField pane_flash_11;

    @FXML
    private Pane pane_flash_13;

    @FXML
    private Pane pane_flash_14;

    @FXML
    private Pane pane_flash_16;

    @FXML
    private Pane pane_flash_17;

    @FXML
    private Pane pane_flash_9;

    @FXML
    private Pane pane_flash_12;

    @FXML
    private Pane pane_flash_15;

    @FXML
    private Label pane_flash_18;

    @FXML
    private Pane pane_flash_3;

    @FXML
    private BorderPane pane_flash_4;

    @FXML
    private Label label_results_designator_1;

    @FXML
    private BorderPane pane_flash_5;

    @FXML
    private Label label_results_designator_2;

    @FXML
    private BorderPane pane_flash_2;

    @FXML
    private Pane pane_flash_1;

    @FXML
    private Pane pane_flash_6;

    @FXML
    private Button button_recalculate;

    @FXML
    private Button button_breakdown;

    @FXML
    private Label label_msg_results;

    @FXML
    private TextArea textarea_results;


    @FXML
    private ComboBox selectObstacleComboBox;

    /**
     * Invoked then the back button is pressed.
     * Shows main welcome screen back to the user
     */
    @FXML
    void handleButtonBack(ActionEvent event) throws IOException {
        App.setRoot("launch");
    }


    @FXML
    void handleImportXML(ActionEvent event) {

    }

    @FXML
    void handleExportObstacle(ActionEvent event) {
        var name = obstacleNameField.getText();
        var height = Double.parseDouble(field_obsheight.getText());
        var center = Double.parseDouble(field_centerline.getText());
        var left = Double.parseDouble(field_threshold_1.getText());
        var right = Double.parseDouble(field_threshold_2.getText());

        var obstacle = new Obstacle(name, height, center, left, right);
        xmlWriting.createObstacleXMLFile(obstacle, name);
    }

}
