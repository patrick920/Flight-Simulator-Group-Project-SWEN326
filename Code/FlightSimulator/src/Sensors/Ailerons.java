package Sensors;

public class Ailerons {
	public double expected_roll;
	private static Ailerons aileron = new Ailerons();
	private Ailerons() {
		expected_roll = 0;
	}
	public static Ailerons getAileron() {
		return aileron;
	}
}
