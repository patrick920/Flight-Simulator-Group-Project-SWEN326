package sensors;

public class Plane {
	public static Ailerons aileron;
	public static Elevator elevator;
	public static Rudder rudder;
	public static Sensor sensors;
	public static double maxThrust;
	public static double minThrust;
	
	/**
	 * Adjust ailerons to achieve desired roll with desired speed. Throw AssertionError if invalid parameters.
	 * @param desired_roll
	 * @param turn_speed
	 * @return - True if operation successful, false otherwise
	 * @throws AssertionError
	 */
	public static boolean setRoll(double desired_roll, double turn_speed) throws AssertionError {
		aileron.expected_roll = desired_roll;
		aileron.setTurnSpeed(turn_speed);
		return true;
	}
}
