package org.swen326.simulator;

import org.swen326.simulator.map.Flight;
import org.swen326.simulator.sensors.Ailerons;
import org.swen326.simulator.sensors.Elevator;
import org.swen326.simulator.sensors.Engine;
import org.swen326.simulator.sensors.Plane;
import org.swen326.simulator.sensors.Rudder;
import org.swen326.simulator.sensors.Sensor;

/**
 *
 * @author nickw
 */
public class Simulator {

    public static Sensor sensor;
    public Ailerons aileron;
    public Elevator elevator;
    public Rudder rudder;

    /**
     * @param maximum_thrust - Maximum thrust of plane model
     * @param minimum_thrust - Minimum thrust of plane model
     * Start running the simulation. This method is called from the user interface when the
     * "Start Simulation" button is clicked.
     */
    public void runSimulator(double maximum_thrust, double minimum_thrust){
        System.out.println("Run Simulation");
        Sensor sens = new Sensor();
        Simulator.sensor = sens;
        aileron = Ailerons.getAileron();
        rudder = Rudder.getRudder();
        elevator = Elevator.getElevator();
        Plane.maxThrust = maximum_thrust;
        Plane.minThrust = minimum_thrust;
    }

    /**
     * @param time - Current time in seconds
     */
    public void update(double time) {
        Sensor.updateValues(time, aileron, rudder, elevator);
    }

    public void setThrust(double thrust){
        Engine.setThrust(thrust);
    }

    public void setAltitude(double altitude){
        Flight.altitude = altitude;
    }

    public void setRoll(double roll){
        Plane.setRoll(roll, 0.1);
    }

    public void setYaw(double yaw){
        Plane.setYaw(yaw, 0.1);
    }
}
