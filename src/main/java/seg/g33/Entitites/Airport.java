package seg.g33.Entitites;

import java.util.ArrayList;
import java.util.UUID;

public class Airport {
    private String name;
    private int airportId;
    private ArrayList<Runway> airportRunways;

    public Airport() {
        this.airportRunways = new ArrayList<Runway>();
        this.airportId = UUID.randomUUID().hashCode();
    }

    public Airport(String name){
        this();
        this.name = name;
    }

    public void addRunway(Runway r){
        this.airportRunways.add(r);
    }

    //removeRunway goes to here

    //getter for runways

    public int getAirportId(){
        return this.airportId;
    }

    public String getName() {
        return this.name;
    }
}
