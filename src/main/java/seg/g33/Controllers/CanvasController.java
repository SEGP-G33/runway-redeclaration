package seg.g33.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import seg.g33.Entitites.*;
import seg.g33.Helpers.Drawer;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CanvasController {

    /**
     * JavaFX Initializer
     * Called as soon as the FXML file is loaded from the FXMLLoader.
     */
    @FXML
    protected void initialize() {
        Runway runway = new Runway("myRunway");
        RunwayParameters param09R = new RunwayParameters(3660d, 3660d, 3660d, 3353d);
        RunwayParameters param27L = new RunwayParameters(3660d, 3660d, 3660d, 3660d);

        RunwaySection section09R = new RunwaySection(runway, 9, 'R', param09R, 307d, 90d, 60d, 240d, 60d);
        RunwaySection section27L = new RunwaySection(runway, 27, 'L', param27L, 0D, 90d, 60d, 240d, 60d);
        runway.addRunwaySection(section09R);
        runway.addRunwaySection(section27L);

        Plane plane = new Plane("myPlane", 300d, 50d);

        Obstacle obstacle = new Obstacle("myObstacle", 25d, 20d, 2853d, 500d);

        Calculator calculator = new Calculator("myCalculator", plane, obstacle, runway);
        ArrayList<RunwayParameters> results = calculator.calculate();

        RunwayParameters params1 = results.get(0);
        RunwayParameters params2 = results.get(1);

//        Drawer.drawTopDown(canvas, 10*section09R.getAngle(), runway, obstacle, params1, params2);
        Drawer.drawSideOn(canvas, runway, obstacle, plane, params1, params2);
    }

    @FXML
    private Canvas canvas;

}
