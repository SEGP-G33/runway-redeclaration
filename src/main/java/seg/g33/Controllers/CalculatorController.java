package seg.g33.Controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import seg.g33.App;
import seg.g33.DataHolders.Environment;
import seg.g33.DataHolders.ObstaclePresets;
import seg.g33.Entitites.*;
import seg.g33.Helpers.*;
import seg.g33.XMLParsing.XMLReading;
import seg.g33.XMLParsing.XMLWriting;
import seg.g33.ui.FieldTooltip;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
     * Properties currently selected in the application.
     */
    private Obstacle selectedObstacle;
    private Airport selectedAirport;
    private Runway selectedRunway;

    /**
     * Properties used for the JavaFX ComboBox to work properly.
     */
    private List<Runway> airportRunways;
    private ObservableList<String> airportRunwayNamesObservableList;

    /**
     * JavaFX Initializer
     * Called as soon as the FXML file is loaded from the FXMLLoader.
     */
    @FXML
    protected void initialize() {
        addTooltipsToFields();
        setAirportProperties();
        setupObstacleBoxUI();

        obstacles = obstaclePresets.getAllObstaclePresets();
        setObstacleListsAndComboBox();

        selectObstacleComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                setElementsForSelectedObstacle(t1);
            }
        });

        observeParameters();
    }

    /**
     * Listens to the observables required to handle recalaulation of parameters
     */
    private void observeParameters(){
        final ComboBoxBase[] recalculateParamsComboBoxObservables = {
                selectRunwayComboBox,
                selectObstacleComboBox
        };

        final TextField[] recalculateParamsTextFieldObservables = {
                obstacleNameField,
                obstacleHeightField,
                obstacleCenterField,
                obstacleRightField,
                obstacleLeftField,

                s1TORAField,
                s1TODAField,
                s1ASDAField,
                s1LDAField,

                s2TODAField,
                s2TODAField,
                s2ASDAField,
                s2LDAField
        };

        //when one of the observables change, recalculate parameters again
        for (ComboBoxBase<?> observable : recalculateParamsComboBoxObservables){
            observable.valueProperty().addListener((ChangeListener) (observableValue, o, t1) -> handleRecalculateParams());
        }

        for (TextField observable : recalculateParamsTextFieldObservables){
            observable.textProperty().addListener((observableValue, s, t1) -> handleRecalculateParams());
        }
    }

    /**
     * Adds tooltips for user convenience in all UI elements that need them.
     */
    private void addTooltipsToFields() {
        // Tooltips for Obstacles
        obstacleNameField.setTooltip(new FieldTooltip("Obstacle Name"));
        obstacleLeftField.setTooltip(new FieldTooltip("Distance from L (m)"));
        obstacleHeightField.setTooltip(new FieldTooltip("Obstacle Height (m)"));
        obstacleCenterField.setTooltip(new FieldTooltip("Distance from center line (m)"));
        obstacleRightField.setTooltip(new FieldTooltip("Distance from R (m)"));

        // Tooltips for Airport & Runway
        airportNameField.setTooltip(new FieldTooltip("Airport Name"));
        airportCodeField.setTooltip(new FieldTooltip("Airport Code"));
        numberOfRunwaysField.setTooltip(new FieldTooltip("Number of runways in the airport"));
    }

    /**
     * Sets & updates the UI for the obstacle boxes, based on whether the usePreset check is ticked
     */
    private void setupObstacleBoxUI() {
        if (useObstaclePresetCheckbox.isSelected()) {
           setEditableFields(false);
        }

        useObstaclePresetCheckbox.setOnAction(action -> {
            setEditableFields(!useObstaclePresetCheckbox.isSelected());
            clearObstacleFields();
        });
    }

    /**
     * Removes all text values from the obstacle fields.
     */
    private void clearObstacleFields() {
        obstacleLeftField.setText(null);
        obstacleCenterField.setText(null);
        obstacleHeightField.setText(null);
        obstacleNameField.setText(null);
        obstacleRightField.setText(null);
    }

    /**
     * Disables or enables the obstacle input fields.
     * @param editable true or false
     */
    private void setEditableFields(Boolean editable) {
        selectObstacleComboBox.setDisable(editable);
        obstacleRightField.setEditable(editable);
        obstacleLeftField.setEditable(editable);
        obstacleHeightField.setEditable(editable);
        obstacleNameField.setEditable(editable);
        obstacleCenterField.setEditable(editable);

        // if not editable, change the style of the fields to look uneditable
        if (!editable){
            obstacleNameField.setStyle("-fx-background-color : #E0E0E0");
            obstacleHeightField.setStyle("-fx-background-color : #E0E0E0");
            obstacleCenterField.setStyle("-fx-background-color : #E0E0E0");
            obstacleLeftField.setStyle("-fx-background-color : #E0E0E0");
            obstacleRightField.setStyle("-fx-background-color : #E0E0E0");
        }
        else{  // else, change it back to default
            obstacleNameField.setStyle(null);
            obstacleHeightField.setStyle(null);
            obstacleCenterField.setStyle(null);
            obstacleLeftField.setStyle(null);
            obstacleRightField.setStyle(null);
        }
    }

    /**
     * Sets all UI properties for the currently selected airport
     */
    private void setAirportProperties() {
        var env = Environment.getInstance();
        selectedAirport = env.getSelectedAirport();

        airportRunways = selectedAirport.getAirportRunways();
        var names = (airportRunways.stream().map((runway -> runway.getName()))).collect(Collectors.toList());

        airportRunwayNamesObservableList = FXCollections.observableList(names);
        selectRunwayComboBox.setItems(airportRunwayNamesObservableList);

        selectRunwayComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                setSelectedRunway(t1);
            }
        });

        airportNameField.setText(selectedAirport.getName());
        airportCodeField.setText(selectedAirport.getShortcode());
        numberOfRunwaysField.setText(String.valueOf(selectedAirport.getAirportRunways().size()));
    }

    /**
     * Sets the selectedRunway field based on the Runway name that was selected from the drop-down.
     * @param runwayName
     */
    private void setSelectedRunway(String runwayName) {
        for (Runway run : airportRunways) {
            if (run.getName().equals(runwayName)) {
                selectedRunway = run;
                setElementsForSelectedRunway();
                return;
            }
        }
    }

    /**
     * Sets the UI elements for the currently selected runway.
     */
    private void setElementsForSelectedRunway() {
        var sections = selectedRunway.getRunwaySections();
        var section1 = sections.get(0);
        var section2 = sections.get(1);
        var section1Params = section1.getDefaultParameters();
        var section2Params = section2.getDefaultParameters();

        // Section 1 UI Elements
        s1TODAField.setText(section1Params.getTODA().toString());
        s1TORAField.setText(section1Params.getTORA().toString());
        s1ASDAField.setText(section1Params.getASDA().toString());
        s1LDAField.setText(section1Params.getLDA().toString());

        // Section 2 UI Elements
        s2TODAField.setText(section2Params.getTODA().toString());
        s2TORAField.setText(section2Params.getTORA().toString());
        s2ASDAField.setText(section2Params.getASDA().toString());
        s2LDAField.setText(section2Params.getLDA().toString());
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

    /**
     * Called when the recalculate button is pressed.
     * If currently selected obstacle or runway is null, shows an alert and returns
     */
    @FXML
    void handleRecalculateParams() {
        var plane = Plane.DEFAULT_PLANE;

        if (!useObstaclePresetCheckbox.isSelected()) {
            var name = obstacleNameField.getText();
            var height = Double.parseDouble(obstacleHeightField.getText());
            var center = Double.parseDouble(obstacleCenterField.getText());
            var left = Double.parseDouble(obstacleLeftField.getText());
            var right = Double.parseDouble(obstacleRightField.getText());
            selectedObstacle = new Obstacle(name, height, center, left, right);
        }

        // lower threshold is on the left side of the view
        Collections.sort(selectedRunway.getRunwaySections(), (o1, o2) -> o1.getAngle().compareTo(o2.getAngle()));

        Calculator calculator = new Calculator("Calculator", plane, selectedObstacle, selectedRunway);
        ArrayList<RunwayParameters> results = calculator.calculate();
        breakdownTextArea.setText(calculator.calcAsString());

        System.out.println("Obstacle: " + selectedObstacle);

        setRecalculateParamsUI(results);

        var angle = selectedRunway.getRunwaySections().get(0).getAngle();
        Drawer.drawTopDown(canvas, 10*angle, selectedRunway, selectedObstacle, results.get(0), results.get(1));
        Drawer.drawSideOn(sideCanvas, selectedRunway, selectedObstacle, plane, results.get(0), results.get(1));
    }

    /**
     * Sets UI components for recalculated distances.
     */
    private void setRecalculateParamsUI(ArrayList<RunwayParameters> results) {
        var section1Results = results.get(0);
        var section2Results = results.get(1);

        recalcS1TORA.setText(section1Results.getTORA().toString());
        recalcS1TODA.setText(section1Results.getTODA().toString());
        recalcS1LDA.setText(section1Results.getLDA().toString());
        recalcS1ASDA.setText(section1Results.getASDA().toString());

        recalcS2TORA.setText(section2Results.getTORA().toString());
        recalcS2TODA.setText(section2Results.getTODA().toString());
        recalcS2LDA.setText(section2Results.getLDA().toString());
        recalcS2ASDA.setText(section2Results.getASDA().toString());
    }

    /**
     * Called when the Import Obstacle XML button is pressed.
     */
    @FXML
    void handleImportObstacleXML(ActionEvent event) {
        FileChooser.ExtensionFilter xmlFileFilter = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an XML File");
        fileChooser.setInitialDirectory(new File(App.getAppDirectory()));
        fileChooser.getExtensionFilters().addAll(xmlFileFilter);
        File selectedFile = fileChooser.showOpenDialog(App.getPrimaryStage());

        configureSelectedFile(selectedFile);
    }

    /**
     * Handles reading an Obstacle XML file and configuring the UI with that.
     * @param selectedFile the file selected from the file chooser.
     */
    private void configureSelectedFile(File selectedFile) {
        var xmlReading = new XMLReading();
        var newObstacle = xmlReading.configureObstacleFromXMLFile(selectedFile.getAbsolutePath());

        if (obstacleAlreadyExists(newObstacle)) {
            var alert = new Alert(Alert.AlertType.ERROR, "You have already added this Obstacle", ButtonType.CANCEL);
            alert.showAndWait();
            return;
        }

        obstacles.add(newObstacle);
        setObstacleListsAndComboBox();
        setElementsForSelectedObstacle(newObstacle.getName());
    }

    /**
     * Sets properties required for the ComboBox to work properly.
     */
    private void setObstacleListsAndComboBox() {
        var names = (obstacles.stream().map((obstacle -> obstacle.getName()))).collect(Collectors.toList());
        obstacleNamesObservableList = FXCollections.observableList(names);
        selectObstacleComboBox.setItems(obstacleNamesObservableList);
    }

    /**
     * Checks if an obstacle already exists in the obstacle list.
     * @param obstacle the obstacle to be added and to be checked against.
     * @return true if the obstacle already exists, false otherwise
     */
    private boolean obstacleAlreadyExists(Obstacle obstacle) {
        for (Obstacle obs : obstacles) {
            if (obs.getName().equals(obstacle.getName()) && obs.getHeight() == obstacle.getHeight()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Called then the back button is pressed.
     * Returns to SelectAirport scene, after confirming navigation with an alert.
     * @throws Exception In case the FXML file isn't there or can't be read correctly. This should never happen.
     */
    @FXML
    void handleButtonBack(ActionEvent event) throws Exception {
        var alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to go back?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            App.setRoot(Constants.getSelectAirportFXML());
        }
    }

    /**
     * Export the obstacle information to an XML file.
     */
    @FXML
    void handleExportObstacle(ActionEvent event) {
        if (useObstaclePresetCheckbox.isSelected()) {
            var alert = new Alert(Alert.AlertType.CONFIRMATION, "You seem to be using an obstacle preset. Are you sure you want to export it again? ", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                exportObstacleToXML();
            }
            return;
        }

        exportObstacleToXML();
    }

    /**
     * Exports the currently selected obstacle to an XML file.
     */
    private void exportObstacleToXML() {
        if (!validateObstacleFieldsForExport()) {
            var alert = new Alert(Alert.AlertType.ERROR, "Please complete all fields with valid values.", ButtonType.CANCEL);
            alert.showAndWait();
            return;
        }

        var name = obstacleNameField.getText();
        var height = Double.parseDouble(obstacleHeightField.getText());
        var center = Double.parseDouble(obstacleCenterField.getText());
        var left = Double.parseDouble(obstacleLeftField.getText());
        var right = Double.parseDouble(obstacleRightField.getText());

        var obstacle = new Obstacle(name, height, center, left, right);

        System.out.println("Exporting Obstacle: " + obstacle.toString());

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Save");
        directoryChooser.setInitialDirectory(new File(App.getAppDirectory()));
        File directory = directoryChooser.showDialog(App.getPrimaryStage());

        var xmlWriter = new XMLWriting();
        var filename = directory.getAbsolutePath().concat("/" + obstacle.getName() + ".xml");
        System.out.println("Saving Airport " + obstacle + " at location " + filename);
        xmlWriter.createObstacleXMLFile(obstacle, filename);

        var alert = new Alert(Alert.AlertType.INFORMATION, "File " + obstacle.getName() + ".xml written.", ButtonType.CANCEL);
        alert.showAndWait();
    }


    /**
     * Validates the input text fields of the obstacle properties.
     * Guarantees they're not empty and contain legal values.
     * @return true if all fields are valid. False otherwise.
     */
    private boolean validateObstacleFieldsForExport() {
        // Don't want to allow blank text values
        var obstacleFields = new TextField[] { obstacleNameField, obstacleHeightField, obstacleCenterField, obstacleLeftField, obstacleRightField };
        if (!Validator.areAllFieldsValid(obstacleFields)) {
            return false;
        }

        // Values for these fields need to be numbers
        obstacleFields = new TextField[] { obstacleHeightField, obstacleCenterField, obstacleLeftField, obstacleRightField };
        var areObstacleFieldsValid = Validator.checkObstacleTextFieldsValid(obstacleFields);
        if (!areObstacleFieldsValid) {
            return false;
        }

        return true;
    }

    /**
     * Called when the "Export Canvas" button is pressed.
     *      Saves the canvas as an image, and shows an alert on the state.
     * @param event the button click ActionEvent
     */
    @FXML
    void handleExportCanvas(ActionEvent event) {
        var selectedIndex  = mainTabPane.getSelectionModel().getSelectedIndex();

        ImageExporter exporter = new ImageExporter(selectedIndex == 0 ? canvas : sideCanvas);

        try {
            exporter.exportImage();
            var alert = new Alert(Alert.AlertType.CONFIRMATION, "Image Exported!", ButtonType.CANCEL);
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            var alert = new Alert(Alert.AlertType.ERROR, "Image Export Failed! ", ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    @FXML
    void handleMenuBarQuit(ActionEvent event) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

    @FXML
    private TabPane mainTabPane;

    @FXML
    private TextArea breakdownTextArea;

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
    private TextField s1LDAField;

    @FXML
    private TextField s1ASDAField;

    @FXML
    private TextField s1TODAField;

    @FXML
    private TextField s1TORAField;

    @FXML
    private TextField s2TODAField;

    @FXML
    private TextField s2ASDAField;

    @FXML
    private TextField s2LDAField;

    @FXML
    private TextField s2TORAField;

    @FXML
    private TextField recalcS1LDA;

    @FXML
    private TextField recalcS1ASDA;

    @FXML
    private TextField recalcS1TODA;

    @FXML
    private TextField recalcS1TORA;

    @FXML
    private TextField recalcS2TODA;

    @FXML
    private TextField recalcS2ASDA;

    @FXML
    private TextField recalcS2LDA;

    @FXML
    private TextField recalcS2TORA;

    @FXML
    private Canvas canvas;

    @FXML
    private Canvas sideCanvas;
}
