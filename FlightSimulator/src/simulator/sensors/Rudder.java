package sensors;

import static org.junit.Assert.assertTrue;

public class Rudder {
	public double expected_yaw;
	private double turn_speed;
	private static Rudder rudder = new Rudder();
	private Rudder() {
		expected_yaw = 0;
	}
	public static Rudder getRudder() {
		return rudder;
	}
	public void setTurnSpeed(double speed) {
		assertTrue("Turn speed must be between 0 and 1", speed <= 1);
		assertTrue("Turn speed must be between 0 and 1", speed >= 0);
		turn_speed = speed;
	}
	
	public double getTurnSpeed() {
		return turn_speed;
	}
}
