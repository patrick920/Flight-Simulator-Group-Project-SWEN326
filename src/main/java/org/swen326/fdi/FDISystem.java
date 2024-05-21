package org.swen326.fdi;

package org.swen326.fdi;

import org.swen326.simulator.sensors.Sensor;
import org.swen326.simulator.sensors.Environment;
import org.swen326.userinterface.CockpitView;
import org.swen326.userinterface.Alert;
import org.swen326.userinterface.UrgencyLevel;

public class FDISystem {
    private CockpitView cockpitView;

    public FDISystem(CockpitView cockpitView) {
        this.cockpitView = cockpitView;
    }

    public void observeSensorData(Environment environment) {
        Sensor[] sensors = environment.getSensors();
        
        for (Sensor sensor : sensors) {
            double value = sensor.getValue();
            
            if (sensor.getType().equals("AirSpeed") && (value < 50 || value > 300)) {
                sendAlert("Air speed is out of range!", UrgencyLevel.HIGH);
            }
            
            if (sensor.getType().equals("Altitude") && value < 1000) {
                sendAlert("Altitude is too low!", UrgencyLevel.CRITICAL);
            }

            if (sensor.getType().equals("Thrust") && value < 20) {
                sendAlert("Thrust is too low!", UrgencyLevel.CRITICAL);
                performCorrectiveAction("Increase thrust to maintain altitude.");
            }
        }
    }

    private void sendAlert(String message, UrgencyLevel urgencyLevel) {
        Alert alert = new Alert(message, urgencyLevel);
        cockpitView.displayAlert(alert);
    }

    private void performCorrectiveAction(String action) {
        cockpitView.performCorrectiveAction(action);
    }
}
