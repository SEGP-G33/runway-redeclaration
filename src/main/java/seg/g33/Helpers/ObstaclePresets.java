package seg.g33.Helpers;

import seg.g33.Entitites.Obstacle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ObstaclePresets {

    /**
     * Directory of obstacle presets
     */
    private String obstaclesDirectory;

    /**
     * XML reader to parse the obstacle presets
     */
    private XMLReading xmlReading = new XMLReading();

    /**
     * Constructor with default directory to presets
     */
    public ObstaclePresets() {
        this.obstaclesDirectory = "src/main/resources/Obstacles";
    }

    /**
     * Constructor with custom path to obstacle presets
     * @param obstaclesDirectory The custom directory for obstacle presets
     */
    public ObstaclePresets(String obstaclesDirectory) {
        this.obstaclesDirectory = obstaclesDirectory;
    }

    /**
     * Preset getter, using preset name without the file extension
     * @param obstacleName Preset name without file extension
     * @return the preset obstacle, or null if it doesn't exist
     */
    public Obstacle getObstaclePreset(String obstacleName) {
        String fullDirectory = obstaclesDirectory.concat("/"+obstacleName+".xml");
        return xmlReading.configureObstacleFromXMLFile(fullDirectory);
    }

    /**
     * Preset getter, using preset name with the file extension
     * @param obstacleNameWithExtension Preset name with the file extension
     * @return the preset obstacle, or null if it doesn't exist
     */
    public Obstacle getObstaclePresetWithExtension(String obstacleNameWithExtension) {
        String fullDirectory = obstaclesDirectory.concat("/"+obstacleNameWithExtension);
        return xmlReading.configureObstacleFromXMLFile(fullDirectory);
    }

    /**
     * Gets all presets in the previously specified directory
     * @return Returns a list of obstacle presets, or null if there were none
     */
    public List<Obstacle> getAllObstaclePresets() {
        List<Obstacle> obstacles = new ArrayList<>();
        File obstacleFile = new File(obstaclesDirectory);
        File[] obstacleNames = obstacleFile.listFiles();
        if (obstacleNames != null) {
            for (File obstacle : obstacleNames) {
                obstacles.add(getObstaclePresetWithExtension(obstacle.getName()));
            }
        }
        return obstacles;
    }
}
