package seg.g33.Helpers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;


import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

/**
 * Optional Requirement 4: Export views as Images
 *
 * Handles exporting a Canvas to a PNG image.
 */
public class ImageExporter {


    private FileChooser fileChooser = new FileChooser();

    /**
     * Canvas element to be exported
     */
    private Canvas canvasToExport;

    /**
     * Image exporter constructor
     * @param canvasToExport The stage that contains the current scene to export
     */
    public ImageExporter (Canvas canvasToExport) {
        this.canvasToExport = canvasToExport;
    }

    /**
     * Exports whatever the given canvas is showing, at whatever size it is on
     * Opens up file explorer to let the user select where they want the file to be saved
     */
    public void exportImage() {
        File exportFile = setExtFilters(fileChooser).showSaveDialog(null);

        if (exportFile != null) {
            try {
                WritableImage writableImage = new WritableImage((int)canvasToExport.getWidth(), (int)canvasToExport.getHeight());
                canvasToExport.snapshot(null, writableImage);
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
     * TODO: Potentially add more file extension types
     */
    private FileChooser setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        return chooser;
    }
}
