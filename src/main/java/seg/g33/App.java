package seg.g33;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    /**
     * Main scene
     */
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Runway Redeclaration Calculator");
        scene = new Scene(loadFXML("launch"), 650, 800);
        stage.setScene(scene);
        stage.setTitle("Runway Redeclaration");
        stage.show();
    }

    /**
     * Sets the Root of the scene to an FXML file
     * @param fxml the name of the fxml file
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads an FXML file using an FXMLLoader
     * @param fxml the name of the FXML file
     * @throws IOException exception if the file isn't there
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main Application Entry
     */
    public static void main(String[] args) {
        launch();
    }

}