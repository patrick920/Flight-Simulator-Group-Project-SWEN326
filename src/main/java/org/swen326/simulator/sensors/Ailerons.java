  package org.swen326.simulator.sensors;

  import static org.junit.Assert.assertTrue;

  public class Ailerons {
	public double expected_roll;
	private double turn_speed;
	private static Ailerons aileron = new Ailerons();
	private Ailerons() {
		expected_roll = 0;
	}
	public static Ailerons getAileron() {
		return aileron;
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
