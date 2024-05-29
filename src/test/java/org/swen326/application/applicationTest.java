package org.swen326.application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class applicationTest {
    @Test
    void testMain() {
        Main main = new Main();
        assertNotNull(main);
    }

    @Test
    void testParseJSON_A320() {
        Main main = new Main();
        List<String> result = main.parseJSON("Airbus A320");
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("Model: Airbus A320", result.get(0));
        assertEquals("Engine Type: CFM56", result.get(1));
        assertEquals("Maximum Thrust: 120.0", result.get(2));
        assertEquals("Minimum Thrust: 6.0", result.get(3));
    }

    @Test
    void testParseJSON_A350() {
        Main main = new Main();
        List<String> result = main.parseJSON("Airbus A350");
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("Model: Airbus A350", result.get(0));
        assertEquals("Engine Type: Rolls-Royce Trent XWB", result.get(1));
        assertEquals("Maximum Thrust: 430.0", result.get(2));
        assertEquals("Minimum Thrust: 10.0", result.get(3));
    }

    @Test
    void testParseJSON_737_800() {
        Main main = new Main();
        List<String> result = main.parseJSON("Boeing 737-800");
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("Model: Boeing 737-800", result.get(0));
        assertEquals("Engine Type: CFM56-7B", result.get(1));
        assertEquals("Maximum Thrust: 130.0", result.get(2));
        assertEquals("Minimum Thrust: 6.5", result.get(3));
    }

    @Test
    void testParseJSON_777_300ER() {
        Main main = new Main();
        List<String> result = main.parseJSON("Boeing 777-300ER");
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("Model: Boeing 777-300ER", result.get(0));
        assertEquals("Engine Type: GE90-115B", result.get(1));
        assertEquals("Maximum Thrust: 512.0", result.get(2));
        assertEquals("Minimum Thrust: 10.0", result.get(3));
    }
}
