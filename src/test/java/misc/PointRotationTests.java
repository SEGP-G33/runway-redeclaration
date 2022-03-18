package misc;

import org.junit.jupiter.api.*;
import seg.g33.Helpers.Drawer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Suite fot testing the XMLReading functionality
 */
public class PointRotationTests {


    @DisplayName("Test rotating points")
    @Test
    public void testReadingObstacleProperties() {
        Double[] point = {0d, 1d};
        Integer angle = 90;
        Double x1 = 0d;
        Double y1 = 0d;
        Double x2 = 4d;
        Double y2 = 1d;

        Double[] expected1 = {-1d, 0d};
        Double[] expected2 = {4d, -3d};
        Double[] actual1 = Drawer.rotateAroundPoint(angle, x1, y1, point);
        Double[] actual2 = Drawer.rotateAroundPoint(angle, x2, y2, point);
        assertEquals(expected1[0], actual1[0]);
        assertEquals(expected1[1], actual1[1]);
        assertEquals(expected2[0], actual2[0]);
        assertEquals(expected2[1], actual2[1]);


    }
}