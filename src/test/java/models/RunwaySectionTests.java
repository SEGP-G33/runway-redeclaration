package models;

import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Runway;
import seg.g33.Entitites.RunwayParameters;
import seg.g33.Entitites.RunwaySection;

import static org.junit.jupiter.api.Assertions.*;

public class RunwaySectionTests {
    private static final Logger logger = LogManager.getLogger(RunwaySectionTests.class);

    /**
     * Initialises all necessary classes
     */
    private final Airport airport = new Airport("airport", "AIR");
    private final Runway runway1 = new Runway("runway1");
    private final Runway runway2 = new Runway("runway2");
    private final RunwayParameters runwayParameters1 = new RunwayParameters(1.0,1.0,1.0,1.0);
    private final RunwayParameters runwayParameters2 = new RunwayParameters(2.0,2.0,2.0,2.0);
    private final RunwaySection runwaySection = new RunwaySection(runway1, 12, 'L', runwayParameters1);

    /**
     *
     * Makes sure the runway and runwaySection are correctly linked and that the class was constructed correctly
     */
    @Test
    public void testRunwaySectionProperties() {
        var isRunway = runwaySection.getRunway();
        assertEquals(runway1, isRunway);

        runway1.addRunwaySection(runwaySection);
        var isRunwaySection = runway1.getRunwaySections().contains(runwaySection);
        assertTrue(isRunwaySection);

        assertEquals(runway1,runwaySection.getRunway());
        assertEquals(12,runwaySection.getAngle());
        assertEquals(0d,runwaySection.getDisplacedThreshold());
        assertEquals(runwayParameters1,runwaySection.getDefaultParameters());
    }

    /**
     * Makes sure all setters and getters work
     */
    @Test
    public void testRunwaySectionGettersSetters() {
        runwaySection.setRunway(runway2);
        runwaySection.setAngle(2);
        runwaySection.setDisplaced(2.0);
        runwaySection.setDefaultParameters(runwayParameters2);

        assertNotEquals(runway1,runwaySection.getRunway());
        assertEquals(runway2,runwaySection.getRunway());
        assertEquals(2,runwaySection.getAngle());
        assertEquals(2.0,runwaySection.getDisplacedThreshold());
        assertEquals(runwayParameters2,runwaySection.getDefaultParameters());
    }
}
