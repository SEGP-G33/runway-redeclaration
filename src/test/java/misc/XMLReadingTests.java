package misc;

import org.junit.jupiter.api.*;
import seg.g33.Entitites.Obstacle;
import seg.g33.Helpers.AirportPresets;
import seg.g33.Helpers.ObstaclePresets;
import seg.g33.Helpers.XMLReading;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Suite fot testing the XMLReading functionality
 */
public class XMLReadingTests {

    /**
     * ObstaclePresents instance to be tested
     */
    private ObstaclePresets obstaclePresets;

    /**
     * AirportPresets instance to be tested
     */
    private AirportPresets airportPresets;

    /**
     * Runs before each test case is executed. Configure the preset classes.
     */
    @BeforeEach
    public void setup() {
        obstaclePresets = new ObstaclePresets();
        airportPresets = new AirportPresets();
    }

    /**
     * Runs after each test case is executed. Releases preset classes from memory.
     */
    @AfterEach
    public void tearDown() {
        obstaclePresets = null;
        airportPresets = null;
    }

    /**
     * MARK: TESTS
     */

    @DisplayName("Test Reading Obstacle")
    @Test
    public void testReadingObstacleProperties() {
        var obstacle = obstaclePresets.getObstaclePreset("Obstacle");

        assertEquals("Pigeon", obstacle.getName());
        assertEquals(34.0, obstacle.getHeight());
        assertEquals(10.0, obstacle.getCenterDistance());
        assertEquals(20.0, obstacle.getLeftDistance());
        assertEquals(30.0, obstacle.getRightDistance());
    }

    @DisplayName("Test Reading Obstacle That Doesn't Exist")
    @Test
    public void testReadingObstacleDoesNotExist() {
        var obstacle = obstaclePresets.getObstaclePreset("NotThere");
        assertNull(obstacle, "Obstacle Should be null");
    }

    @DisplayName("Test Reading all obstacles")
    @Test
    public void testReadingAllObstacles() {
        var obstacles = obstaclePresets.getAllObstaclePresets();
        assertEquals(3, obstacles.size(), "Should have 3 preset obstacles");
    }

    @DisplayName("Test Reading Airport")
    @Test
    public void testReadingAirportProperties() {
        var airport = airportPresets.getAirportPreset("Airport");

        assertEquals("JFK International Airport", airport.getName());
    }

    @DisplayName("Tests Reading Airport That Doesn't Exist")
    @Test
    public void testReadingAirportThatDoesNotExist() {
        var airport = airportPresets.getAirportPreset("NotThere");
        assertNull(airport, "Airport should be null");
    }

    @DisplayName("Test Reading all Airports")
    @Test
    public void testReadingAllAirports() {
        var airports = airportPresets.getAllAirportPresets();
        assertEquals(1, airports.size(), "Should have 3 preset obstacles");
    }
}
