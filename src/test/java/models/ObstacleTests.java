package models;

import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seg.g33.Entitites.Obstacle;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTests {

    private static final Logger logger = LogManager.getLogger(ObstacleTests.class);

    private final Obstacle obstacle = new Obstacle("Obstacle1", 123.1, 10.0, 5.0, 6.0);

    @DisplayName("Testing Obstacle Properties")
    @Test
    public void testObstacleProperties() {
        var name = obstacle.getName();
        var height = obstacle.getHeight();
        var center = obstacle.getCenterDistance();
        var left = obstacle.getLeftDistance();
        var right = obstacle.getRightDistance();

        assertEquals("Obstacle1", name, "Obstacle Name should be \"Obstacle 1\".");
        assertEquals(123.1, height, "Obstacle Height should be \"123.1\".");
        assertEquals(10.0, center, "Obstacle Center Distance should be \"10.0\".");
        assertEquals(5.0, left, "Obstacle Left Distance should be \"5.0\".");
        assertEquals(6.0, right, "Obstacle Right Distance should be \"6.0\".");
    }

    @DisplayName("Testing Obstacle Setters")
    @Test
    public void testObstacleTesters() {
        obstacle.setName("New Name");
        obstacle.setHeight(200.0);
        obstacle.setCenterDistance(20.0);
        obstacle.setLeftDistance(10.0);
        obstacle.setRightDistance(15.0);

        var name = obstacle.getName();
        var height = obstacle.getHeight();
        var center = obstacle.getCenterDistance();
        var left = obstacle.getLeftDistance();
        var right = obstacle.getRightDistance();

        assertEquals("New Name", name, "Obstacle Name should be \"New Name\".");
        assertEquals(200.0, height, "Obstacle Height should be \"200.0\".");
        assertEquals(20.0, center, "Obstacle Center Distance should be \"20.0\".");
        assertEquals(10.0, left, "Obstacle Left Distance should be \"10.0\".");
        assertEquals(15.0, right, "Obstacle Right Distance should be \"15.0\".");
    }
}
