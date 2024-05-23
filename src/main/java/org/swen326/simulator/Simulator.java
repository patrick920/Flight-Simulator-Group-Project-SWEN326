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
public class Simulator implements TimerRun {

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
     * The simulator timer runs on a separate thread.
     */
    private SimulatorTimer simulatorTimer;

    /**
     * The simulator is responsible for the aircraft simulation.
     */
    public Simulator(){
        simulatorTimer = new SimulatorTimer(this, this, 120, 10, 36000);
    }

    /**
     * @param maximum_thrust - Maximum thrust of plane model
     * @param minimum_thrust - Minimum thrust of plane model
     * Start running the simulation. This method is called from the user interface when the
     * "Start Simulation" button is clicked.
     */
    public void runSimulator(double maximum_thrust, double minimum_thrust){
        //TODO: This code violates the Power of ten rules as you are calling the "new"
        // keyword not during initialisation of the program.
        // Additionally, the values double maximum_thrust, double minimum_thrust are must be validated.
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

        //Start the simulation loop. This starts a new method that will call the "runEveryFrame()" method and the
        //"runEverySecond" method 120 times per second. These methods are in this file (scroll down to see them.)
        if (simulatorTimer.getTimerState() == SimulatorTimer.TimerState.NOT_ACTIVE) {
            simulatorTimer.startTimer();
        }
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

    public double getPitch() {
        Map<Double, Integer> vals = new HashMap<>();

        for (Sensor sens : Simulator.elevator_sensors) {
            double val = sens.getYaw();
            if (!vals.keySet().contains(val)) {
                vals.put(val, 1);
            } else {
                vals.put(val, vals.get(val) + 1);
            }
        }

        if (vals.keySet().size() != 1) {
            elevator_redundancy = false;
        } else {
            elevator_redundancy = true;
        }

        return Collections.max(vals.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    /**
     * Validate the latitude before the simulation starts.
     * The latitude is the North-South measurement (Y axis).
     * It must be between 90 and -90 (inclusive).
     * @param latitudeValue The latitude value to validate.
     * @return
     */
    public ValidateProblem validateLatitude(String latitudeValue){
        if(latitudeValue == null){
            throw new IllegalArgumentException("startLatitudeValue is null.");
        }
        //Source: https://education.nationalgeographic.org/resource/latitude/
        double startLatitudeNum;
        String regularMessage = "Please enter a number that is less than or equal to 90 and greater than or equal to -90.";
        try{
            startLatitudeNum = Double.parseDouble(latitudeValue);
        } catch(NumberFormatException nfe){
            //The string is not a number so return false.
            return new ValidateProblem(false, "Error. The latitude you entered (" + latitudeValue + ") is not a valid number."
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
    }

    /**
     * Validate the longitude before the simulation starts.
     * The longitude is the East-West measurement (X axis).
     * It must be between 180 and -180 (inclusive).
     * @param longitudeValue The longitude value to validate.
     * @return
     */
    public ValidateProblem validateLongitude(String longitudeValue){
        if(longitudeValue == null){
            throw new IllegalArgumentException("startLatitudeValue is null.");
        }
        //Source: https://www.nationalgeographic.org/encyclopedia/longitude/
        double startLatitudeNum;
        String regularMessage = "Please enter a number that is less than or equal to 90 and greater than or equal to -90.";
        try{
            startLatitudeNum = Double.parseDouble(longitudeValue);
        } catch(NumberFormatException nfe){
            //The string is not a number so return false.
            return new ValidateProblem(false, "Error. The longitude you entered (" + longitudeValue + ") is not a valid number. "
                    + regularMessage);
        }
        if(startLatitudeNum > 180){
            return new ValidateProblem(false, "Error. The longitude you entered is greater than " +
                    "180. " + regularMessage);
        } else if(startLatitudeNum < -180){
            return new ValidateProblem(false, "Error. The latitude you entered is less than 180. "
                    + regularMessage);
        }
        return new ValidateProblem(true, "");
    }

    /**
     * This method is automatically executed by the timer every frame.
     *
     * @param timer
     */
    @Override
    public void runEveryFrame(SimulatorTimer timer) {
        //TODO: Update the plane's location etc... as it is moving.
    }

    /**
     * This method is automatically executed by the timer every
     *
     * @param timer
     */
    @Override
    public void runEverySecond(SimulatorTimer timer) {
        System.out.println("Running timer: timer.currentSecond() = " + timer.currentSecond());
    }
}
