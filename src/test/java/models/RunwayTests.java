package models;

import org.junit.jupiter.api.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class RunwayTests {

    private static final Logger logger = LogManager.getLogger(RunwayTests.class);

    private Runway runway;

    @BeforeEach
    public void setupTesting() {
        runway = new Runway();
    }

    @AfterEach
    public void tearDown() {
        runway = null;
    }

    @DisplayName("Testing Runway Angle")
    @Test
    public void testRunwayAngle() {
        var angle = runway.getAngle();
        assertEquals(12.0, angle, "Runway Angle should be 12.0");
    }

    @DisplayName("Testing Runway Direction")
    @Test
    public void testRunwayDirection() {
        var direction = runway.getDirection();
        assertEquals("L", direction, "Runway Direction should be L");
    }

    @DisplayName("Testing Runway Lengths")
    @Test
    public void testRunwayLengths() {
        var clearway = runway.getClearwayLength();
        assertEquals(30.0, clearway, "Runway Clearway should be 30.0");

        var stopway = runway.getStopway();
        assertEquals(40.0, stopway, "Runway Stopway should be 40.0");

        var runwayLength = runway.getLength();
        assertEquals(1500.0, runwayLength, "Runway Length should be 1500.0");

        var resa = runway.getRESALength();
        assertEquals(500.0, resa, "RESA Length should be 500.0");

        var stripEnd = runway.getStripEndLength();
        assertEquals(50.0, stripEnd, "StripEnd Length should be 50.0");
    }

    @DisplayName("Testing Runway Displaced Threshold")
    @Test
    public void testRunwayDisplacedThreshold() {
        var displacedThreshold = runway.getDisplacedThreshold();
        assertEquals(50.0, displacedThreshold, "Displaced Threshold should be 50.0");
    }

}
