package seg.g33.Entitites;

import java.util.ArrayList;

public final class Airport {

    /**
     * Properties
     */
    private String name;
    private String shortcode;
    private final ArrayList<Runway> airportRunways;

    /**
     * Basic Constructor
     * @param name is the name of the airport
     */
    public Airport(String name, String shortcode) {
        this.name = name;
        this.shortcode = shortcode;
        this.airportRunways = new ArrayList<Runway>();
    }

    /**
     * Adds a new runway to our list of runways
     * @param runway is the new runway added
     */
    public void addRunway(Runway runway){
        this.airportRunways.add(runway);
    }

    /**
     * Removes a new runway to our list of runways
     * @param runway is the new runway removed
     */
    public void removeRunway(Runway runway) {
        this.airportRunways.remove(runway);
    }

    /**
     * Retrieves a specific runway
     * @param runwayName the name of the runway to be retrieved
     * @return that retrieved runway
     */
    public Runway retrieveRunway(String runwayName) {
        for (Runway runway : getAirportRunways()) {
            if (runway.getName().equals(runwayName)) {
                return runway;
            }
        }
        return null;
    }

    /**
     * Getter method to get the list of runways
     * @return all added or updated runways in the airport
     */
    public ArrayList<Runway> getAirportRunways() {
        return this.airportRunways;
    }

    /**
     * Getter method for the airport name
     * @return the name of the airport
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method to set the name of the airport
     * @param name is the name of the airport to be set.
     */
    public void setName(String name) {
        this.name = name;
    }


    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
}
