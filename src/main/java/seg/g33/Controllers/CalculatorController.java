package seg.g33.Controllers;

import java.lang.String;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;

public class CalculatorController {

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
    private ComboBox<?> selectObstacleComboBox;

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
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void handleButtonBack(ActionEvent event) {

    }

    @FXML
    void handleExportObstacle(ActionEvent event) {

    }

    @FXML
    void handleImportXML(ActionEvent event) {

    }

}
