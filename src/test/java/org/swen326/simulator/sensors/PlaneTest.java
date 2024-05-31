package org.swen326.simulator.sensors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swen326.simulator.map.Flight;
import org.swen326.simulator.map.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A series of JUnit tests to test "Plane.java". It only tests individual methods, NOT the system as a whole.
 * It tests the methods with expected, boundary (edge), and invalid values. However, the last method does not check
 * for expected/boundary/invalid values as there are no parameters.
 */
class PlaneTest {
    /**
     * Test calling the "setRoll()" method in "Plane.java" using expected values.
     */
    @Test
    void setRollExpected() {
        boolean exceptionThrown = false;
        try {
            Plane.aileron = Ailerons.getAileron();
            Plane.setRoll(10, 0.5);
            Plane.setRoll(-10, 0.25);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertFalse(exceptionThrown); //Check that an "AssertionError" was NOT thrown.
    }

    /**
     * Test calling the "setRoll()" method in "Plane.java" using boundary (edge) values.
     */
    @Test
    void setRollBoundary() {
        boolean exceptionThrown = false;
        try {
            Plane.aileron = Ailerons.getAileron();
            Plane.setRoll(25, 1);
            Plane.setRoll(-25, 0);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertFalse(exceptionThrown); //Check that an "AssertionError" was NOT thrown.
    }

    /**
     * Test calling the "setRoll()" method in "Plane.java" using invalid values.
     * If the test succeeds, an AssertionError should be thrown and caught.
     * If no AssertionError gets thrown, then there is a problem with the code.
     */
    @Test
    void setRollInvalid() {
        boolean exceptionThrown = false;
        try {
            Plane.aileron = Ailerons.getAileron();
            Plane.setRoll(30, 2);
            Plane.setRoll(-30, -0.5);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertTrue(exceptionThrown); //Check that an "AssertionError" was thrown.
    }

    /**
     * Test calling the "setYaw()" method in "Plane.java" using valid values.
     * If the test succeeds, an AssertionError should NOT be thrown.
     * If an AssertionError gets thrown, then there is a problem with the code.
     */
    @Test
    void setYawExpected() {
        boolean exceptionThrown = false;
        try{
            Plane.rudder = Rudder.getRudder();
            Plane.setYaw(3, 0.5);
            Plane.setYaw(-3, 0.1);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertFalse(exceptionThrown); //Check that an "AssertionError" was NOT thrown.
    }

    /**
     * Test calling the "setYaw()" method in "Plane.java" using boundary (edge) values.
     * If the test succeeds, an AssertionError should NOT be thrown.
     * If an AssertionError gets thrown, then there is a problem with the code.
     */
    @Test
    void setYawBoundary() {
        boolean exceptionThrown = false;
        try{
            Plane.rudder = Rudder.getRudder();
            Plane.setYaw(5, 1);
            Plane.setYaw(-5, 0);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertFalse(exceptionThrown); //Check that an "AssertionError" was NOT thrown.
    }

    /**
     * Test calling the "setYaw()" method in "Plane.java" using invalid values.
     * If the test succeeds, an AssertionError should be thrown by the "setYaw()" method.
     * If no AssertionError gets thrown, then there is a problem with the code.
     */
    @Test
    void setYawInvalid() {
        boolean exceptionThrown = false;
        try{
            Plane.rudder = Rudder.getRudder();
            Plane.setYaw(10, 2);
            Plane.setYaw(-10, -1);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertTrue(exceptionThrown); //Check that an "AssertionError" was thrown.
    }

    /**
     * Test calling the "setPitch()" method in "Plane.java" using valid values.
     * If the test succeeds, an AssertionError should NOT be thrown.
     * If an AssertionError gets thrown, then there is a problem with the code.
     */
    @Test
    void setPitchExpected() {
        boolean exceptionThrown = false;
        try{
            Plane.elevator = Elevator.getElevator();
            Plane.setPitch(20, 0.5);
            Plane.setPitch(-10, 0.9);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertFalse(exceptionThrown); //Check that an "AssertionError" was NOT thrown.
    }

    /**
     * Test calling the "setPitch()" method in "Plane.java" using boundary (edge) values.
     * If the test succeeds, an AssertionError should NOT be thrown.
     * If an AssertionError gets thrown, then there is a problem with the code.
     */
    @Test
    void setPitchBoundary() {
        boolean exceptionThrown = false;
        try{
            Plane.elevator = Elevator.getElevator();
            Plane.setPitch(30, 1);
            Plane.setPitch(-15, 0);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertFalse(exceptionThrown); //Check that an "AssertionError" was NOT thrown.
    }

    /**
     * Test calling the "setPitch()" method in "Plane.java" using invalid values.
     * If the test succeeds, an AssertionError should be thrown.
     * If no AssertionError gets thrown, then there is a problem with the code.
     */
    @Test
    void setPitchInvalid() {
        boolean exceptionThrown = false;
        try{
            Plane.elevator = Elevator.getElevator();
            Plane.setPitch(40, 2);
            Plane.setPitch(-16, -0.1);
        } catch(AssertionError ae){
            exceptionThrown = true;
            ae.printStackTrace();
        }
        assertTrue(exceptionThrown); //Check that an "AssertionError" was thrown.
    }

    /**
     * Test calling the "setPitch()" method in "Plane.java".
     * If the test succeeds, no errors should be thrown.
     */
    @Test
    void correctHeading() {
        Plane.aileron = Ailerons.getAileron();
        Plane.rudder = Rudder.getRudder();
        Flight.waypoints = new ArrayList<>(List.of(new Point(3, 2), new Point(4, 1)));
        Plane.correctHeading();
    }
}