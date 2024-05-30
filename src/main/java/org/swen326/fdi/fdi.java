package org.swen326.fdi;

import org.swen326.simulator.sensors.Sensor;
import org.swen326.userinterface.UserInterface;

import javafx.application.Platform;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Fault Detection and Isolation (FDI) class for monitoring sensor values and triggering alerts if the values exceeds the limit.
 */
public class fdi {
    private List<Sensor> sensors;
    private ScheduledExecutorService scheduler;
    private UserInterface ui;

    /**
     * Constructor for FDI class.
     * 
     * @param sensors List of sensors to monitor.
     * @param ui User interface to trigger alerts.
     */
    public fdi(List<Sensor> sensors, UserInterface ui) {
        this.sensors = sensors;
        this.ui = ui;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    /**
     * Starts monitoring the sensors at a fixed rate.
     */
    public void startMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            for (Sensor sensor : sensors) {
                double change = Math.random() * 10 - 5;
                double newValue = sensor.getValue() + change;
                sensor.setValue(newValue);
                System.out.println("DEBUG: Updated sensor " + sensor.getType() + " to value: " + newValue);
                monitorSensors();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * Stops monitoring the sensors and shuts down the scheduler.
     */
    public void stopMonitoring() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Scheduler did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("DEBUG: Monitoring stopped.");
        
        // Exit the application
        Platform.exit(); // Exit JavaFX application
        System.exit(0); // Ensure the JVM terminates
    }

    /**
     * Monitors the sensor values and triggers alerts if values are out of range.
     */
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
                default:
                    System.err.println("Unknown sensor type: " + sensor.getType());
                    break;
            }
        }
    }

    /**
     * Adjusts the pitch of the aircraft.
     * 
     * @param value The current pitch value.
     */
    private void adjustPitch(double value) {
        System.out.println("DEBUG: Adjusting pitch from: " + value);
    }

    /**
     * Adjusts the roll of the aircraft.
     * 
     * @param value The current roll value.
     */
    private void adjustRoll(double value) {
        System.out.println("DEBUG: Adjusting roll from: " + value);
    }

    /**
     * Adjusts the yaw of the aircraft.
     * 
     * @param value The current yaw value.
     */
    private void adjustYaw(double value) {
        System.out.println("DEBUG: Adjusting yaw from: " + value);
    }

    /**
     * Triggers an alert on the user interface.
     * 
     * @param severity The severity of the alert.
     * @param message The alert message.
     */
    private void triggerAlert(String severity, String message) {
        System.out.println("DEBUG: Triggering alert - " + severity + ": " + message);
        ui.triggerAlert(severity, message);
    }

    /**
     * Adjusts the thrust of the aircraft based on the current airspeed.
     * 
     * @param value The current airspeed value.
     */
    private void adjustThrust(double value) {
        System.out.println("DEBUG: Adjusting thrust: Current Airspeed = " + value);
    }

    /**
     * Adjusts the altitude of the aircraft.
     * 
     * @param value The current altitude value.
     */
    private void adjustAltitude(double value) {
        System.out.println("DEBUG: Corrective action: Adjusting altitude");
    }
}
