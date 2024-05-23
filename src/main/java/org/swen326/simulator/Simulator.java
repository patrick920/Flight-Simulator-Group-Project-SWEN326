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
}
