package org.swen326.simulator.sensors;
import static org.junit.Assert.assertTrue;

public class Plane {
	public static Ailerons aileron;
	public static Elevator elevator;
	public static Rudder rudder;
	public static Sensor sensors;
	public static double maxThrust;
	public static double minThrust;
	
	
	/**
	 * Adjust ailerons to achieve desired roll with desired speed. Throw AssertionError if invalid parameters.
	 * @param desired_roll - Correct roll that the plane will aim to achieve
	 * @param turn_speed - Number between 0 and 1. 1 is the maximum speed and 0 is the minimum.
	 * @return - True if operation successful, false otherwise
	 * @throws AssertionError
	 */
	public static boolean setRoll(double desired_roll, double turn_speed) throws AssertionError {
		assertTrue("Roll must be between 25 and -25 degrees.", desired_roll <= 25 && desired_roll >= -25);
		aileron.expected_roll = desired_roll;
		aileron.setTurnSpeed(turn_speed);
		return true;
	}
	
	/**
	 * Adjust rudders to achieve desired yaw with desired speed. Throw AssertionError if invalid parameters.
	 * @param desired_yaw
	 * @param turn_speed
	 * @return - True if operation successful, false otherwise
	 * @throws AssertionError
	 */
	public static boolean setYaw(double desired_yaw, double turn_speed) throws AssertionError {
		assertTrue("Yaw must be between 5 and -5 degrees.", desired_yaw <= 5 && desired_yaw >= -5);
		rudder.expected_yaw = desired_yaw;
		rudder.setTurnSpeed(turn_speed);
		return true;
	}
	
	/**
	 * Adjust elevators to achieve desired pitch with desired speed. Throw AssertionError if invalid parameters.
	 * @param desired_pitch
	 * @param turn_speed
	 * @return - True if operation successful, false otherwise
	 * @throws AssertionError
	 */
	public static boolean setPitch(double desired_pitch, double turn_speed) throws AssertionError {
		assertTrue("Pitch must be between 30 degrees up and -15 degrees down.", desired_pitch <= 30 && desired_pitch >= -15);
		elevator.expected_pitch = desired_pitch;
		elevator.setTurnSpeed(turn_speed);
		return true;
	}
}
