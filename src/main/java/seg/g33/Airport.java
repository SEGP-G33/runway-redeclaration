package seg.g33;

import java.util.ArrayList;
import java.util.UUID;

public class Airport {
    private String name;
    private int airportId;
    //private ArrayList<Runway> = airportRunways;

    public Airport() {
        //this.airportRunways = ArrayList<Runway>
        this.airportId = UUID.randomUUID().hashCode();
    }

    public Airport(String name){
        this();
        this.name = name;
    }

    //addRunway goes to here

    //removeRunway goes to here

    //getter for runways

    public int getAirportId(){
        return this.airportId;
    }

    public String getName() {
        return this.name;
    }
}
