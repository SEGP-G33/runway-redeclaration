package seg.g33.Entitites;

import java.util.ArrayList;

public class Runway {

    /**
     * Properties
     */
    private final String name;
    private final ArrayList<RunwaySection> runwaySections;

    /**
     * Constructor for the Runway
     * @param name the name of the runway, imported from XML
     */
    public Runway(String name) {

        this.name = name;
        this.runwaySections = new ArrayList<>();
    }

    /**
     * -=-=-=-=-=-=-=-
     * Getter Methods
     * -=-=-=-=-=-=-=-
     */
    public String getName() {
        return name;
    }

    public ArrayList<RunwaySection> getRunwaySections() {
        return runwaySections;
    }

    /**
     * -=-=-=-=-=-=-=-
     * Setter Methods
     * -=-=-=-=-=-=-=-
     */
    public void addRunwaySection(RunwaySection section) throws IllegalArgumentException{
        if (this.runwaySections.contains(section)){
            throw new IllegalArgumentException("Cannot add a runway section which has already been added");
        } else if (this.runwaySections.size() == 2){
            throw new IllegalArgumentException("This runway already has 2 logical runways added");
        }
    }

}
