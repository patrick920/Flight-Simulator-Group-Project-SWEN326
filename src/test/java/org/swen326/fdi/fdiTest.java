package org.swen326.fdi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swen326.simulator.Simulator;
import org.swen326.simulator.sensors.Sensor;
import org.swen326.simulator.sensors.Sensor.SensorType;
import org.swen326.userinterface.UserInterface;
import org.swen326.application.Main;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the fdi class.
 */
public class fdiTest {
    private fdi fdiInstance;
    private List<Sensor> sensors;
    private UserInterfaceTestImpl ui;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize sensors with out of range values for testing
        sensors = new ArrayList<>();

        Sensor airspeedSensor = new Sensor(SensorType.AIRSPEED, 600.0); // Out of range value for testing
        Sensor altitudeSensor = new Sensor(SensorType.ALTITUDE, 800.0); // Out of range value for testing
        Sensor pitchSensor = new Sensor(SensorType.PITCH, 40.0); // Out of range value for testing
        Sensor rollSensor = new Sensor(SensorType.ROLL, 70.0); // Out of range value for testing
        Sensor yawSensor = new Sensor(SensorType.YAW, 190.0); // Out of range value for testing

        sensors.add(airspeedSensor);
        sensors.add(altitudeSensor);
        sensors.add(pitchSensor);
        sensors.add(rollSensor);
        sensors.add(yawSensor);

        // Initialize a simple UserInterface implementation for testing
        Main mockMain = new Main();
        Simulator mockSimulator = new Simulator();
        ui = new UserInterfaceTestImpl(mockMain, mockSimulator);

        // Initialize the FDI class with sensors and UI
        fdiInstance = new fdi(sensors, ui);
    }

    /**
     * Tests the monitorSensors method to ensure it triggers appropriate alerts.
     */
    @Test
    public void testMonitorSensors() {
        // Call the method to test
        fdiInstance.monitorSensors();

        // Check if the alerts were triggered
        assertTrue(ui.getAlerts().contains("High Alert: Airspeed out of range: 600.0"));
        assertTrue(ui.getAlerts().contains("Critical Alert: Altitude too low: 800.0"));
        assertTrue(ui.getAlerts().contains("Critical Alert: Pitch angle is critical: 40.0"));
        assertTrue(ui.getAlerts().contains("Warning: Roll angle is excessive: 70.0"));
        assertTrue(ui.getAlerts().contains("Warning: Yaw angle is out of range: 190.0"));
    }

    /**
     * A simple implementation of UserInterface for testing purposes.
     */
    private static class UserInterfaceTestImpl extends UserInterface {
        private final List<String> alerts = new ArrayList<>();

        /**
         * Constructor for UserInterfaceTestImpl.
         * 
         * @param main The main application class.
         * @param simulator The simulator instance.
         */
        public UserInterfaceTestImpl(Main main, Simulator simulator) {
            super(main, simulator);
        }

        /**
         * Triggers an alert by adding it to the alerts list.
         * 
         * @param severity The severity of the alert.
         * @param message The alert message.
         */
        @Override
        public void triggerAlert(String severity, String message) {
            alerts.add(severity + ": " + message);
        }

        /**
         * Gets the list of alerts triggered during the test.
         * 
         * @return The list of alerts.
         */
        public List<String> getAlerts() {
            return alerts;
        }
    }
}



