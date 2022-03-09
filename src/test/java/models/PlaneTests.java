package models;

import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.Entitites.Plane;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneTests {

    private static final Logger logger = LogManager.getLogger(PlaneTests.class);

    /**
     * A default plane with only name given
     */
    private final Plane defaultPlane = new Plane("Plane1");

    /**
     * A test to test if default values for blast protection and slope are correct given the plane only has a name
     */
    @Test
    public void testDefaultPlaneProperties(){
        var name = defaultPlane.getName();
        var blastProtection = defaultPlane.getBlastProtection();
        var slope = defaultPlane.getSlope();

        assertEquals("Plane1", name, "Plane Name should be \"Plane1\".");
        assertEquals(300.0, blastProtection, "Plane Blast Protection Value Should Be \"300.0\".");
        assertEquals(50.0, slope,"Plane Slope Value Should be \"50.0\".");
    }

    /**
     * A plane with blast protection and slope value given
     */
    private final Plane plane = new Plane("Plane2", 350.0, 45.0);

    /**
     * A test to test if the values given for blast protection and slope for a plane are correct
     */
    @Test
    public void testPlaneProperties(){
        var name = plane.getName();
        var blastProtection = plane.getBlastProtection();
        var slope = plane.getSlope();

        assertEquals("Plane2", name, "Plane Name should be \"Plane2\".");
        assertEquals(350.0, blastProtection, "Plane Blast Protection Value Should Be \"350.0\".");
        assertEquals(45.0, slope,"Plane Slope Value Should be \"45.0\".");
    }
}
