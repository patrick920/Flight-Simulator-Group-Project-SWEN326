package sensors;

import static org.junit.Assert.assertTrue;

public class Elevator {
	public double expected_pitch;
	private double turn_speed;
	private static Elevator elevator = new Elevator();
	private Elevator() {
		expected_pitch = 0;;
	}
	public static Elevator getElevator() {
		return elevator;
	}
	
	/**
	 * Set the turn speed of the elevators
	 * @param speed - Number between 0 and 1, 1 being the maximum speed and 0 being the minimum
	 */
	public void setTurnSpeed(double speed) {
		assertTrue("Turn speed must be between 0 and 1", speed <= 1);
		assertTrue("Turn speed must be between 0 and 1", speed >= 0);
		turn_speed = speed;
	}
	
	public double getTurnSpeed() {
		return turn_speed;
	}
}
