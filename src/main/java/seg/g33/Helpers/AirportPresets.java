package seg.g33.Helpers;

import seg.g33.App;
import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Obstacle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AirportPresets {

    /**
     * Directory of Airport presets
     */
    private String airportDirectory;

    /**
     * XML reader to parse the Airport presets
     */
    private XMLReading xmlReading = new XMLReading();

    /**
     * Constructor with default directory to presets
     */
    public AirportPresets() {
        this.airportDirectory = App.getResourceDirectory().concat("/Airports");
    }

    /**
     * Constructor with custom path to Airport presets
     * @param airportDirectory The custom directory for obstacle presets
     */
    public AirportPresets(String airportDirectory) {
        this.airportDirectory = airportDirectory;
    }

    /**
     * Preset getter, using preset name without the file extension
     * @param airportName Preset name without file extension
     * @return the preset obstacle, or null if it doesn't exist
     */
    public Airport getAirportPreset(String airportName) {
        String fullDirectory = airportDirectory.concat("/"+airportName+".xml");
        return xmlReading.configureAirportFromXMLFile(fullDirectory);
    }

    /**
     * Preset getter, using preset name with the file extension
     * @param airportNameWithExtension Preset name with the file extension
     * @return the preset airport, or null if it doesn't exist
     */
    public Airport getAirportPresetWithExtension(String airportNameWithExtension) {
        String fullDirectory = airportDirectory.concat("/"+airportNameWithExtension);
        return xmlReading.configureAirportFromXMLFile(fullDirectory);
    }

    /**
     * Gets all presets in the previously specified directory
     * @return Returns a list of Airports presets, or null if there were none
     */
    public List<Airport> getAllAirportPresets() {
        List<Airport> airports = new ArrayList<>();
        File airportsFile = new File(airportDirectory);
        File[] airportNames = airportsFile.listFiles();
        if (airportNames != null) {
            for (File airport : airportNames) {
                airports.add(getAirportPresetWithExtension(airport.getName()));
            }
        }
        return airports;
    }

}
