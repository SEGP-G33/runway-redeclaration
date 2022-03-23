package seg.g33.DataHolders;

import seg.g33.Entitites.Airport;

/**
 * Environment.java
 *
 * This class will hold all the shared state that needs to be passed around in the application.
 * For example: selected airport
 *
 * It is a singleton class meaning it instances of it can't be created, thus assuring that only one version exists
 * for the duration of the app's lifecycle.
 *
 * This class in necessary since working with JavaFX FXML Controllers makes it considerable hard to pass data between
 * controllers. This will guarantee all views have access to the global state they require.
 */
public final class Environment {

    /**
     * Shared Instance
     */
    private final static Environment shared = new Environment();

    /**
     * Shared airport instance.
     * Will be passed along each screen that needs it using this singleton class.
     */
    private Airport selectedAirport;

    /**
     * Constructor is private so no new instances can be created.
     */
    private Environment() { }

    /**
     * The shared instance of the class.
     */
    public static Environment getInstance() {
        return shared;
    }

    public void setSelectedAirport(Airport selectedAirport) {
        this.selectedAirport = selectedAirport;
    }

    public Airport getSelectedAirport() {
        return selectedAirport;
    }
}
