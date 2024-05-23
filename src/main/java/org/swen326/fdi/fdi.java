package org.swen326.fdi;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.swen326.simulator.sensors.Sensor;

import java.util.List;

public class fdi {

    private List<Sensor> sensors;

    public fdi(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public void checkSensors() {
        for (Sensor sensor : sensors) {
            double value = sensor.getRoll(); // Example usage, assuming `getRoll()` simulates getting some value
            if (isValueAbnormal(value)) {
                alertPilot(sensor.getClass().getSimpleName(), value);
                performCorrectiveAction(sensor);
            }
        }
    }

    protected boolean isValueAbnormal(double value) {
        // Define criteria for abnormal values
        return value < 50 || value > 150;  // Example criteria
    }

    protected void alertPilot(String sensorType, double value) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Sensor Alert");
            alert.setHeaderText(null);
            alert.setContentText(sensorType + " sensor value abnormal: " + value);
            alert.showAndWait();
        });
    }

    protected void performCorrectiveAction(Sensor sensor) {
        // Logic to perform corrective actions
        System.out.println("Performing corrective action for sensor: " + sensor.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        List<Sensor> sensors = List.of(
            new Sensor(),
            new Sensor()
        );

        fdi faultDetection = new fdi(sensors);
        faultDetection.checkSensors();
    }
}
