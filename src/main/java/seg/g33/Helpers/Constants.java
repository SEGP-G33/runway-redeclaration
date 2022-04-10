package seg.g33.Helpers;

/**
 * Constants class to hold common filenames and various bits used throughout the application.
 *
 * Used to avoid spelling mistakes or having to change references in a number of places when a filename updates.
 */
public final class Constants {

    /**
     * Names for FXML files.
     */
    static String CalculatorFXML = "calculator";
    static String LauncherFXML = "launch";
    static String ConfigureAirportFXML = "configure-airport";
    static String SelectAirportFXML = "select-airport";



    public static String getCalculatorFXML() {
        return CalculatorFXML;
    }

    public static String getLauncherFXML() {
        return LauncherFXML;
    }

    public static String getConfigureAirportFXML() {
        return ConfigureAirportFXML;
    }

    public static String getSelectAirportFXML() {
        return SelectAirportFXML;
    }
}
