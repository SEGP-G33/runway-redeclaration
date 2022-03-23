package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import seg.g33.Entitites.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {

    private static final Logger logger = LogManager.getLogger(CalculatorTests.class);
    private static final RunwayParameters param09R = new RunwayParameters(3660d, 3660d, 3660d, 3353d);
    private static final RunwayParameters param27L = new RunwayParameters(3660d, 3660d, 3660d, 3660d);
    private static final RunwayParameters param09L = new RunwayParameters(3902d, 3902d, 3902d, 3595d);
    private static final RunwayParameters param27R = new RunwayParameters(3884d, 3962d, 3884d, 3884d);

    @DisplayName("Tests Setting up the Airports and Runways")
    @Test
    public void testSetup(){
        Airport airport = new Airport("myAirport", "MAIR");
        Runway runway = new Runway("myRunway");
        Obstacle obstacle = new Obstacle("myObstacle", 12d, 0d, -50d, 3646d);
        Plane plane = new Plane("myPlane", 300d, 50d);

        RunwayParameters param09L = new RunwayParameters(3902d, 3902d, 3902d, 3595d);
        RunwayParameters param27R = new RunwayParameters(3884d, 3962d, 3884d, 3884d);

        RunwaySection section09L = new RunwaySection(runway, 9, 'L', param09L);
        RunwaySection section27R = new RunwaySection(runway, 27, 'R', param27R);
        runway.addRunwaySection(section09L);
        runway.addRunwaySection(section27R);

        Calculator calculator = new Calculator("myCalculator", plane, obstacle, runway);
        assertEquals(plane, calculator.getPlane(), "Plane set incorrectly");
        assertEquals(obstacle, calculator.getObstacle(), "Obstacle set incorrectly");
        assertEquals(runway, calculator.getRunway(), "Runway set incorrectly");
    }

    @DisplayName("Tests Calculation")
    @Test
    public void testCalculation(){
        Airport airport = new Airport("MyAirport", "MAIR");
        Runway runway = new Runway("myRunway");
        Obstacle obstacle = new Obstacle("myObstacle", 12d, 0d, -50d, 3646d);
        Plane plane = new Plane("myPlane", 300d, 50d);

        RunwaySection section09L = new RunwaySection(runway, 9, 'L', param09L,306d, 0d, 0d, 240d, 60d);
        RunwaySection section27R = new RunwaySection(runway, 27, 'R', param27R);
        runway.addRunwaySection(section09L);
        runway.addRunwaySection(section27R);

        Calculator calculator = new Calculator("myCalculator", plane, obstacle, runway);
        ArrayList<RunwayParameters> results = calculator.calculate();

        RunwayParameters params1 = results.get(0);
        RunwayParameters params2 = results.get(1);
        //assertEquals("", calculator.calcAsString(), "Wrong");
        assertEquals(3346d, params1.getTORA(), "TORA incorrectly calculated for 09L");
        assertEquals(3346d, params1.getASDA(), "ASDA incorrectly calculated for 09L");
        assertEquals(3346d, params1.getTODA(), "TODA incorrectly calculated for 09L");
        assertEquals(2985d, params1.getLDA(), "LDA incorrectly calculated for 09L");

        assertEquals(2986d, params2.getTORA(), "TORA incorrectly calculated for 27R");
        assertEquals(2986d, params2.getASDA(), "ASDA incorrectly calculated for 27R");
        assertEquals(2986d, params2.getTODA(), "TODA incorrectly calculated for 27R");
        assertEquals(3346d, params2.getLDA(), "LDA incorrectly calculated for 27R");
    }

    @DisplayName("Tests Calculation 2")
    @Test
    public void testCalculation2(){
        Airport airport = new Airport("MyAirport", "MAIR");
        Runway runway = new Runway("myRunway");
        Obstacle obstacle = new Obstacle("myObstacle", 25d, 20d, 2853d, 500d);
        Plane plane = new Plane("myPlane", 300d, 50d);

        RunwaySection section09R = new RunwaySection(runway, 9, 'L', param09R,307d, 0d, 0d, 240d, 60d);
        RunwaySection section27L = new RunwaySection(runway, 27, 'R', param27L);
        runway.addRunwaySection(section09R);
        runway.addRunwaySection(section27L);

        Calculator calculator = new Calculator("myCalculator", plane, obstacle, runway);
        ArrayList<RunwayParameters> results = calculator.calculate();

        RunwayParameters params1 = results.get(0);
        RunwayParameters params2 = results.get(1);
        //assertEquals("", calculator.calcAsString(), "Wrong");
        assertEquals(1850d, params1.getTORA(), "TORA incorrectly calculated for 09L");
        assertEquals(1850d, params1.getASDA(), "ASDA incorrectly calculated for 09L");
        assertEquals(1850d, params1.getTODA(), "TODA incorrectly calculated for 09L");
        assertEquals(2553d, params1.getLDA(), "LDA incorrectly calculated for 09L");

        assertEquals(2860d, params2.getTORA(), "TORA incorrectly calculated for 27R");
        assertEquals(2860d, params2.getASDA(), "ASDA incorrectly calculated for 27R");
        assertEquals(2860d, params2.getTODA(), "TODA incorrectly calculated for 27R");
        assertEquals(1850d, params2.getLDA(), "LDA incorrectly calculated for 27R");
    }
}