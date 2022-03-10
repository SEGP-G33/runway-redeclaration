package models;

import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Runway;


import static org.junit.jupiter.api.Assertions.*;

public class AirportTests {

    private static final Logger logger = LogManager.getLogger(PlaneTests.class);

    /**
     * Basic airports with a name and a couple of runways
     * Runway parameters are set to random values because this test is to only test if runways are added to the airport.
     */
    private final Airport airport1 = new Airport("Airport1");
    private final Airport airport2 = new Airport("Airport2");
    private final Runway runway1 = new Runway("Runway1",airport1,0,'R',1.0,1.0,1.0,1.0);
    private final Runway runway2 = new Runway("Runway2",airport1,0,'L',1.0,1.0,1.0,1.0);
    private final Runway runway3 = new Runway("Runway3",airport2,0,'L',1.0,1.0,1.0,1.0);

    /**
     *
     * This test checks if airport names and runways in them are correct.
     */
    @Test
    public void checkAirportProperties(){
        airport1.addRunway(runway1);
        airport1.addRunway(runway2);
        airport2.addRunway(runway3);
        var name1 = airport1.getName();
        var name2 = airport2.getName();
        var isRunway1 = airport1.getAirportRunways().contains(runway1);
        var isRunway2 = airport1.getAirportRunways().contains(runway2);
        var isRunway3 = airport1.getAirportRunways().contains(runway3);    // should assert to false
        var isRunway4 = airport2.getAirportRunways().contains(runway3);

        assertEquals("Airport1", name1, "Airport Name should be \"Airport1\".");
        assertEquals("Airport2", name2, "Airport Name should be \"Airport2\".");
        assertEquals(true, isRunway1, "Airport1 should have \"Runway1\".");
        assertEquals(true, isRunway2, "Airport1 should have \"Runway2\".");
        assertEquals(false, isRunway3, "Airport1 should not have \"Runway3\".");
        assertEquals(true, isRunway4, "Airport2 should have \"Runway3\".");


    }
    /**
     * Runway2 is removed from Airport1 here to check if removeRunway works.
     * Runway2 is then added to Airport2 to check if addRunway works.
     */
    @Test
    public void checkAddRemove(){
        airport1.removeRunway(runway2);
        var isRunway5 = airport1.getAirportRunways().contains(runway2);  // should assert to false
        assertEquals(false, isRunway5, "Airport1 should not have\"Runway2\" anymore since it was removed.");

        airport2.addRunway(runway2);
        var isRunway6 = airport2.getAirportRunways().contains(runway2);
        assertEquals(true, isRunway6, "Airport2 should now have \"Runway2\" since now it was added.");

    }

}
