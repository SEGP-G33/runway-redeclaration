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
     * Main App Directory.
     * Used for XMLReading, XMLWriting and Saving of files...
     */
    private static String resourceDirectory = "src/main/resources";
    private static String appDirectory = System.getProperty("user.dir");

    /**
     * Main scene
     */
    private static Scene scene;

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        stage.setTitle("Runway Redeclaration Calculator");
        scene = new Scene(loadFXML("launch"), 900, 600);
        stage.setScene(scene);
        //TODO Fixed scene size on the start page, and adaptive window size
//        stage.setResizable(false);
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
     * Returns the Primary stage for the Application.
     * Used to pass to FileChooser and other components later on.
     * @return Application's primary stage.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the main directory of the JavaFX application.
     */
    public static String getResourceDirectory() {
        return resourceDirectory;
    }

    public static String getAppDirectory() {
        return appDirectory;
    }

    /**
     * Main Application Entry
     */
    public static void main(String[] args) {
        launch();
    }


}