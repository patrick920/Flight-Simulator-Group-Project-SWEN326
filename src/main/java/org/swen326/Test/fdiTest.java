package org.swen326.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.swen326.fdi.fdi;
import org.swen326.simulator.Simulator;
import org.swen326.simulator.sensors.Environment;
import org.swen326.simulator.sensors.Sensor;

public class fdiTest {
    private fdi faultDetection;
    private List<Sensor> sensors;

    @Before
    public void setUp() {
        sensors = new ArrayList<>();
        sensors.add(new Sensor() {
            @Override
            public double getRoll() {
                return 200; // Simulating an abnormal value
            }
        });
        sensors.add(new Sensor() {
            @Override
            public double getRoll() {
                return 100; // Simulating a normal value
            }
        });

        faultDetection = new fdi(sensors) {
            @Override
            protected void alertPilot(String sensorType, double value) {
                super.alertPilot(sensorType, value);
            }

            @Override
            protected void performCorrectiveAction(Sensor sensor) {
                super.performCorrectiveAction(sensor);
            }
        };
    }

    @Test
    public void testCheckSensors() {
        // Capture the output
        List<String> alerts = new ArrayList<>();
        List<String> correctiveActions = new ArrayList<>();

        faultDetection = new fdi(sensors) {
            @Override
            protected void alertPilot(String sensorType, double value) {
                super.alertPilot(sensorType, value);
                alerts.add("Alert: " + sensorType + " sensor value abnormal: " + value);
            }

            @Override
            protected void performCorrectiveAction(Sensor sensor) {
                super.performCorrectiveAction(sensor);
                correctiveActions.add("Performing corrective action for sensor: " + sensor.getClass().getSimpleName());
            }
        };

        faultDetection.checkSensors();

        // Verify the alerts
        assertEquals(1, alerts.size());
        assertEquals("Alert: Sensor sensor value abnormal: 200.0", alerts.get(0));

        // Verify the corrective actions
        assertEquals(1, correctiveActions.size());
        assertTrue(correctiveActions.get(0).contains("Performing corrective action for sensor: Sensor"));
    }
    @Test
    public void test2oo3Architecture(){
        Simulator sim = new Simulator();
        Simulator.aileron_sensors.get(0).variance = 1;
        Environment.roll = 1.0;
        assertTrue("Failed test. Two out of three redundant architecture is non-functional", sim.getRoll() == 1.0);
        assertTrue("Redundancy lost with no warning.", sim.aileron_redundancy == false);
    }
}
