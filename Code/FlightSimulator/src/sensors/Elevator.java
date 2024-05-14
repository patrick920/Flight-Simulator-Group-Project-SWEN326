package sensors;

public class Elevator {
	public double expected_pitch;
	private static Elevator elevator = new Elevator();
	private Elevator() {
		expected_pitch = 0;;
	}
	public static Elevator getElevator() {
		return elevator;
	}
}
