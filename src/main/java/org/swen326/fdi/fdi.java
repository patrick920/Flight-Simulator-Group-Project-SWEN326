package org.swen326.fdi;

import org.swen326.simulator.sensors.Sensor;
import org.swen326.userinterface.UserInterface;
import java.util.List;

public class fdi {
    private List<Sensor> sensors;

    public fdi(List<Sensor> sensors, UserInterface ui) {
        this.sensors = sensors;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void monitorSensors() {
        for (Sensor sensor : sensors) {
            double value = sensor.getValue();
            System.out.println("DEBUG: Monitoring sensor " + sensor.getType() + " with value: " + value);
            switch (sensor.getType()) {
                case AIRSPEED:
                    if (value < 50 || value > 500) {
                        triggerAlert("High Alert", "Airspeed out of range: " + value);
                        adjustThrust(value);
                    }
                    break;
                case ALTITUDE:
                    if (value < 1000) {
                        triggerAlert("Critical Alert", "Altitude too low: " + value);
                        adjustAltitude(value);
                    }
                    break;
                case PITCH:
                    if (value > 30 || value < -30) {
                        triggerAlert("Critical Alert", "Pitch angle is critical: " + value);
                        adjustPitch(value);
                    }
                    break;
                case ROLL:
                    if (value > 60 || value < -60) {
                        triggerAlert("Warning", "Roll angle is excessive: " + value);
                        adjustRoll(value);
                    }
                    break;
                case YAW:
                    if (value > 180 || value < -180) {
                        triggerAlert("Warning", "Yaw angle is out of range: " + value);
                        adjustYaw(value);
                    }
                    break;
            }
        }
    }
    
    private void adjustPitch(double value) {
        System.out.println("DEBUG: Adjusting pitch from: " + value);
    }
    
    private void adjustRoll(double value) {
        System.out.println("DEBUG: Adjusting roll from: " + value);
    }
    
    private void adjustYaw(double value) {
        System.out.println("DEBUG: Adjusting yaw from: " + value);
    }
    
    private void triggerAlert(String severity, String message) {
        System.out.println("DEBUG: Triggering alert - " + severity + ": " + message);
        UserInterface.triggerAlert(severity, message);
    }
    
    private void adjustThrust(double value) {
        System.out.println("DEBUG: Adjusting thrust: Current Airspeed = " + value);
    }
    
    private void adjustAltitude(double value) {
        System.out.println("DEBUG: Corrective action: Adjusting altitude");
    }
}
    
