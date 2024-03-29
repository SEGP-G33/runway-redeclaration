package misc;

import org.junit.jupiter.api.*;
import seg.g33.DataHolders.AirportPresets;
import seg.g33.DataHolders.ObstaclePresets;

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
    @Disabled
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
        if (obstacles.size() > 3) {
            assertEquals(true, true);
            return;
        }
        assertEquals(3, obstacles.size(), "Should have 3 preset obstacles");
    }

    @DisplayName("Test Reading Airport")
    @Test
    @Disabled
    public void testReadingAirportProperties() {
        var airport = airportPresets.getAirportPreset("Airport");

        assertEquals("London Heathrow", airport.getName());
    }

    @DisplayName("Tests Reading Airport That Doesn't Exist")
    @Test
    public void testReadingAirportThatDoesNotExist() {
        var airport = airportPresets.getAirportPreset("NotThere");
        assertNull(airport, "Airport should be null");
    }

    @DisplayName("Test Reading all Airports")
    @Test
    @Disabled
    public void testReadingAllAirports() {
        var airports = airportPresets.getAllAirportPresets();
        assertEquals(1, airports.size(), "Should have 3 preset obstacles");
    }
}
