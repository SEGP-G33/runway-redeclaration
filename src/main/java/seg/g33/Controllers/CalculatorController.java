package seg.g33.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import seg.g33.Entitites.*;
import seg.g33.Helpers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CalculatorController {

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

    private Airport selectedAirport;


    /**
     * JavaFX Initializer
     * Called as soon as the FXML file is loaded from the FXMLLoader.
     */
    @FXML
    protected void initialize() {
        setAirportProperties();

        // TODO: Testing Canvas. Remove later
        testCanvas();

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

    private void testCanvas() {
        Runway runway = new Runway("myRunway");
        RunwayParameters param09R = new RunwayParameters(3660d, 3660d, 3660d, 3353d);
        RunwayParameters param27L = new RunwayParameters(3660d, 3660d, 3660d, 3660d);

        RunwaySection section09R = new RunwaySection(runway, 9, 'R', param09R, 307d, 0d, 0d, 240d, 60d);
        RunwaySection section27L = new RunwaySection(runway, 27, 'L', param27L, 0D, 0d, 0d, 240d, 60d);
        runway.addRunwaySection(section09R);
        runway.addRunwaySection(section27L);

        Plane plane = new Plane("myPlane", 300d, 50d);

        Obstacle obstacle = new Obstacle("myObstacle", 25d, 20d, 2853d, 500d);

        Calculator calculator = new Calculator("myCalculator", plane, obstacle, runway);
        ArrayList<RunwayParameters> results = calculator.calculate();

        RunwayParameters params1 = results.get(0);
        RunwayParameters params2 = results.get(1);

        Drawer.drawTopDown(canvas, 10*section09R.getAngle(), runway, obstacle, params1, params2);
    }

    /**
     * Sets all UI properties for the currently selected airport
     */
    private void setAirportProperties() {
        var env = Environment.getInstance();
        selectedAirport = env.getSelectedAirport();

        airportNameField.setText(selectedAirport.getName());
        airportCodeField.setText(selectedAirport.getShortcode());
        numberOfRunwaysField.setText(String.valueOf(selectedAirport.getAirportRunways().size()));
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
        obstacleHeightField.textProperty().set(height);
        obstacleCenterField.textProperty().set(center);
        obstacleLeftField.textProperty().set(left);
        obstacleRightField.textProperty().set(right);
    }

    @FXML private Canvas canvas;

    @FXML
    private ScrollPane root_scroll;

    @FXML
    private VBox root_vbox;

    @FXML
    private Button button_back;

    @FXML
    private Button button_import_obstacle;

    @FXML
    private Button button_export_obstacle;

    @FXML
    private TextField airportNameField;

    @FXML
    private TextField airportCodeField;

    @FXML
    private TextField numberOfRunwaysField;

    @FXML
    private ComboBox<String> selectRunwayComboBox;

    @FXML
    private ComboBox<String> selectObstacleComboBox;

    @FXML
    private CheckBox useObstaclePresetCheckbox;

    @FXML
    private TextField obstacleNameField;

    @FXML
    private TextField obstacleCenterField;

    @FXML
    private TextField obstacleHeightField;

    @FXML
    private TextField obstacleRightField;

    @FXML
    private TextField obstacleLeftField;

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
    private Pane pane_flash_8;

    @FXML
    private TextField field_original_tora2;

    @FXML
    private Pane pane_flash_10;

    @FXML
    private Pane pane_flash_13;

    @FXML
    private Pane pane_flash_14;

    @FXML
    private Pane pane_flash_9;

    @FXML
    private Pane pane_flash_12;

    @FXML
    private Pane pane_flash_15;

    @FXML
    private Pane pane_flash_3;

    @FXML
    private BorderPane pane_flash_4;

    @FXML
    private BorderPane pane_flash_5;

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
    private TextField field_original_lda1;

    @FXML
    private TextField field_original_asda1;

    @FXML
    private TextField field_original_toda1;

    @FXML
    private TextField field_original_tora1;

    @FXML
    private Pane pane_flash_71;

    @FXML
    private Pane pane_flash_81;

    @FXML
    private TextField field_original_tora21;

    @FXML
    private Pane pane_flash_101;

    @FXML
    private TextField field_original_asda11;

    @FXML
    private Pane pane_flash_131;

    @FXML
    private Pane pane_flash_141;

    @FXML
    private TextField field_original_lda11;

    @FXML
    private Pane pane_flash_91;

    @FXML
    private Pane pane_flash_121;

    @FXML
    private Pane pane_flash_151;

    @FXML
    private Pane pane_flash_31;

    @FXML
    private BorderPane pane_flash_41;

    @FXML
    private BorderPane pane_flash_51;

    @FXML
    private TextField field_original_tora11;

    @FXML
    private BorderPane pane_flash_21;

    @FXML
    private Pane pane_flash_61;

    @FXML
    private Label label_msg_results;

    @FXML
    private TextArea textarea_results;

    @FXML
    void handleButtonBack(ActionEvent event) {

    }

    @FXML
    void handleExportObstacle(ActionEvent event) {

    }

}
