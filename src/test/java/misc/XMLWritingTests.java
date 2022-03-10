package misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seg.g33.Entitites.Obstacle;
import seg.g33.Helpers.XMLReading;
import seg.g33.Helpers.XMLWriting;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Suite for testing the XMLWriting functionality
 */
public class XMLWritingTests {

    /**
     * XML Writer
     */
    private XMLWriting xmlWriting;

    /**
     * XML Reader
     */
    private XMLReading xmlReading;

    /**
     * Runs before each test case is executed. Configure the XML parsing classes.
     */
    @BeforeEach
    public void setup() {
        xmlReading = new XMLReading();
        xmlWriting = new XMLWriting();
    }

    /**
     * Runs after each test case is executed. Releases XML parsing classes from memory.
     */
    @AfterEach
    public void tearDown() {
        xmlWriting = null;
        xmlReading = null;
    }

    @DisplayName("Test Writing an Obstacle instance and then Reading it.")
    @Test
    public void testSaveReadObstacle() {
        var obstacle = new Obstacle("Obstacle1", 123.1, 10.0, 5.0, 6.0);
        xmlWriting.createObstacleXMLFile(obstacle, "TestObstacle1");
        var readObstacle = xmlReading.configureObstacleFromXMLFile("TestObstacle1");

        assertEquals(obstacle.getName(), readObstacle.getName());
        assertEquals(obstacle.getCenterDistance(), readObstacle.getCenterDistance());
        assertEquals(obstacle.getLeftDistance(), readObstacle.getLeftDistance());
        assertEquals(obstacle.getRightDistance(), readObstacle.getRightDistance());
    }

    @DisplayName("Test passing a null Obstacle throws Exception")
    @Test
    public void testWritingNullObstacle() {
        assertThrows(IllegalArgumentException.class, () -> xmlWriting.createObstacleXMLFile(null, "NullObstacle"));
    }

    @DisplayName("Test passing a null Airport throws Exception")
    @Test
    public void testWritingNullAirport() {
        assertThrows(IllegalArgumentException.class, () -> xmlWriting.createAirportXMLFile(null, "NullAirport"));
    }

}
