package seg.g33.Entitites;

import java.util.ArrayList;
import java.util.UUID;

public final class Airport {

    private String name;
    private ArrayList<Runway> airportRunways;

    public Airport() {
        this.airportRunways = new ArrayList<Runway>();
    }

    public Airport(String name) {
        this.name = name;
    }

    public void addRunway(Runway runway){
        this.airportRunways.add(runway);
    }

    public void removeRunway(Runway runway) {
        this.airportRunways.remove(runway);
    }

    public Runway retrieveRunway(String runwayName) {
        for (Runway runway : getAirportRunways()) {
            if (runway.getName().equals(runwayName)) {
                return runway;
            }
        }
        return null;
    }

    public ArrayList<Runway> getAirportRunways() {
        return this.airportRunways;
    }

    public String getName() {
        return this.name;
    }
}
