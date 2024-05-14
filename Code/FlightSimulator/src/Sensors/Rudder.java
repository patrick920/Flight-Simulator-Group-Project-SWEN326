package Sensors;

public class Rudder {
	public double expected_yaw;
	private static Rudder rudder = new Rudder();
	private Rudder() {
		expected_yaw = 0;
	}
	public static Rudder getRudder() {
		return rudder;
	}
}
