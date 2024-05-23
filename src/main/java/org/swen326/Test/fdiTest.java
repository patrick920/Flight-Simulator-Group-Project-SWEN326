package org.swen326.Test;
import org.junit.Before;
import org.junit.Test;
import org.swen326.simulator.sensors.Sensor;
import org.swen326.fdi.fdi;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
}
