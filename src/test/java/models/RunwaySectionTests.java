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
    private final Airport airport = new Airport("airport");
    private final Runway runway1 = new Runway("runway1");
    private final Runway runway2 = new Runway("runway2");
    private final RunwayParameters runwayParameters1 = new RunwayParameters(1.0,1.0,1.0,1.0);
    private final RunwayParameters runwayParameters2 = new RunwayParameters(2.0,2.0,2.0,2.0);
    private final RunwaySection runwaySection1 = new RunwaySection(runway1,10,'L',1.0,runwayParameters1);
    private final RunwaySection runwaySection2 = new RunwaySection(runway1, 100, 'R', 1.0, runwayParameters2);

    /**
     *
     * Makes sure the runway and runwaySection are correctly linked and that the class was constructed correctly
     */
    @Test
    public void CheckRunwaySectionProperties() {
        var isRunway = runwaySection1.getRunway();
        assertEquals(runway1,isRunway);

        runway1.addRunwaySection(runwaySection1);
        var isRunwaySection = runway1.getRunwaySections().contains(runwaySection1);
        assertTrue(isRunwaySection);

        assertEquals(runway1,runwaySection1.getRunway());
        assertEquals(1,runwaySection1.getAngle());
        assertEquals(1.0,runwaySection1.getDisplacedThreshold());
        assertEquals(runwayParameters1,runwaySection1.getDefaultParameters());
    }

    /**
     * Makes sure all setters and getters work
     */
    @Test
    public void CheckSettersAndGetters() {
        runwaySection1.setRunway(runway2);
        runwaySection1.setAngle(2);
        runwaySection1.setDisplaced(2.0);
        runwaySection1.setDefaultParameters(runwayParameters2);

        assertNotEquals(runway1,runwaySection1.getRunway());
        assertEquals(runway2,runwaySection1.getRunway());
        assertEquals(2,runwaySection1.getAngle());
        assertEquals(2.0,runwaySection1.getDisplacedThreshold());
        assertEquals(runwayParameters2,runwaySection1.getDefaultParameters());
    }
}
