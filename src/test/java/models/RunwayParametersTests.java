package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import seg.g33.Entitites.RunwayParameters;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunwayParametersTests {

    private static final Logger logger = LogManager.getLogger(PlaneTests.class);

    @Test
    public void testGettersSetters(){
        RunwayParameters params = new RunwayParameters(100d, 100d, 100d, 100d);

        assertEquals(100d, params.getTORA(), "Tora should equal 100.0");
        assertEquals(100d, params.getASDA(), "Asda should equal 100.0");
        assertEquals(100d, params.getTODA(), "Toda should equal 100.0");
        assertEquals(100d, params.getLDA(), "Lda should equal 100.0");

        params.setTODA(200d);
        params.setASDA(200d);
        params.setTODA(200d);
        params.setLDA(200d);

        assertEquals(200d, params.getTORA(), "Tora should equal 200.0");
        assertEquals(200d, params.getASDA(), "Asda should equal 200.0");
        assertEquals(200d, params.getTODA(), "Toda should equal 200.0");
        assertEquals(200d, params.getLDA(), "Lda should equal 200.0");
    }


}
