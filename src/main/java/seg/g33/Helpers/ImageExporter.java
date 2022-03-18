package seg.g33.Helpers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class ImageExporter {
    private FileChooser fileChooser = new FileChooser();
    private Stage stageToExport;

    /**
     * Image exporter constructor
     * @param stageToExport The stage that contains the current scene to export
     */
    public ImageExporter (Stage stageToExport) {
        this.stageToExport = stageToExport;
    }

    /**
     * Exports whatever the current scene is showing, at whatever size it is on
     * Opens up file explorer to let the user select where they want the file to be saved
     */
    public void exportImage () {
        File exportFile = setExtFilters(fileChooser).showSaveDialog(stageToExport);

        if (exportFile != null) {
            try {
                WritableImage writableImage = new WritableImage((int)stageToExport.getWidth(), (int) stageToExport.getHeight());
                stageToExport.getScene().snapshot(writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", exportFile);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Image export error!");
            }
        }
    }

    /**
     * Adds default file extensions to the file explorer window that pops up
     * @param chooser The chooser which the extensions are being added to
     * @return The modified chooser with the new file extensions
     */
    private FileChooser setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")

        );
        return chooser;
    }
}
