package org.swen326.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    public static List<Sensor> aileron_sensors;
    public static List<Sensor> elevator_sensors;
    public static List<Sensor> rudder_sensors;
    public Ailerons aileron;
    public Elevator elevator;
    public Rudder rudder;
    public boolean aileron_redundancy;
    public boolean rudder_redundancy;
    public boolean elevator_redundancy;

    /**
     * @param maximum_thrust - Maximum thrust of plane model
     * @param minimum_thrust - Minimum thrust of plane model
     * Start running the simulation. This method is called from the user interface when the
     * "Start Simulation" button is clicked.
     */
    public void runSimulator(double maximum_thrust, double minimum_thrust){
        Simulator.rudder_sensors = new ArrayList<Sensor>();
        Simulator.elevator_sensors = new ArrayList<Sensor>();
        Simulator.aileron_sensors = new ArrayList<>();
        System.out.println("Run Simulation");
        for (int i = 0; i < 3; i++){
            rudder_sensors.add(new Sensor());
            elevator_sensors.add(new Sensor());
            aileron_sensors.add(new Sensor());
        }
        aileron = Ailerons.getAileron();
        rudder = Rudder.getRudder();
        elevator = Elevator.getElevator();
        Plane.maxThrust = maximum_thrust;
        Plane.minThrust = minimum_thrust;
        aileron_redundancy = true;
        rudder_redundancy = true;
        elevator_redundancy = true;
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

<<<<<<< HEAD
    public double getRoll(){
        Map<Double, Integer> vals = new HashMap<>();
        
        for (Sensor sens : Simulator.aileron_sensors){
            double val = sens.getRoll();
            if (!vals.keySet().contains(val)){
                vals.put(val, 1);
            }
            else {
                vals.put(val, vals.get(val) + 1);
            }
        }

        if (vals.keySet().size() != 1){
            aileron_redundancy = false;
        }

        else {
            aileron_redundancy = true;
        }

        return Collections.max(vals.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public double getYaw(){
        Map<Double, Integer> vals = new HashMap<>();
        
        for (Sensor sens : Simulator.rudder_sensors){
            double val = sens.getYaw();
            if (!vals.keySet().contains(val)){
                vals.put(val, 1);
            }
            else {
                vals.put(val, vals.get(val) + 1);
            }
        }

        if (vals.keySet().size() != 1){
            rudder_redundancy = false;
        }

        else {
            rudder_redundancy = true;
        }

        return Collections.max(vals.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public double getPitch(){
        Map<Double, Integer> vals = new HashMap<>();
        
        for (Sensor sens : Simulator.elevator_sensors){
            double val = sens.getYaw();
            if (!vals.keySet().contains(val)){
                vals.put(val, 1);
            }
            else {
                vals.put(val, vals.get(val) + 1);
            }
        }

        if (vals.keySet().size() != 1){
            elevator_redundancy = false;
        }
        else {
            elevator_redundancy = true;
        }

        return Collections.max(vals.entrySet(), Map.Entry.comparingByValue()).getKey();
=======
    /**
     * Validate the latitude before the simulation starts.
     * The latitude is the North-South measurement (Y axis).
     * It must be between 90 and -90 (inclusive).
     * @param startLatitudeValue
     * @return
     */
    public ValidateProblem validateLatitude(String startLatitudeValue){
        if(startLatitudeValue == null){
            throw new IllegalArgumentException("startLatitudeValue is null.");
        }
        //Source: https://education.nationalgeographic.org/resource/latitude/
        double startLatitudeNum;
        String regularMessage = "Please enter a number that is less than or equal to 90 and greater than or equal to -90.";
        try{
            startLatitudeNum = Double.parseDouble(startLatitudeValue);
        } catch(NumberFormatException nfe){
            //The string is not a number so return false.
            return new ValidateProblem(false, "Error. The latitude you entered (" + startLatitudeValue + ") is not a valid number."
                    + regularMessage);
        }
        if(startLatitudeNum > 90){
            return new ValidateProblem(false, "Error. The latitude you entered is greater than 90."
                    + regularMessage);
        } else if(startLatitudeNum < -90){
            return new ValidateProblem(false, "Error. The latitude you entered is less than 90."
                    + regularMessage);
        }
        return new ValidateProblem(true, "");
>>>>>>> 0bd7e3107c8ad54c3cad39663dfe260abb4466c3
    }
}
