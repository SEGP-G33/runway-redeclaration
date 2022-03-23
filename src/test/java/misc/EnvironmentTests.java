package misc;

import org.junit.jupiter.api.*;
import seg.g33.DataHolders.Environment;
import seg.g33.Entitites.Airport;

import static org.junit.jupiter.api.Assertions.*;

public class EnvironmentTests {

    private final Airport airport1 = new Airport("Airport1", "AIR1");

    @DisplayName("Testing Environment Setter")
    @Test
    public void testEnvironmentSetter() {
        var env = Environment.getInstance();
        env.setSelectedAirport(airport1);

        var returned = env.getSelectedAirport();
        assertEquals(airport1, returned);
    }

    @DisplayName("Testing Environment Getter/Setter")
    @Test
    public void testEnvironmentGetterSetter() {
        var env = Environment.getInstance();
        env.setSelectedAirport(airport1);

        var env1 = Environment.getInstance();
        var returned = env1.getSelectedAirport();
        assertEquals(airport1, returned);
    }


}
