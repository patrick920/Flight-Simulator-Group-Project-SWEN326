  package org.swen326.simulator.sensors;

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

	public void setTurnSpeed(double speed) {
		assertTrue("Turn speed must be between 0 and 1", speed <= 1);
		assertTrue("Turn speed must be between 0 and 1", speed >= 0);
		turn_speed = speed;
	}

	public double getTurnSpeed() {
		return turn_speed;
	}
  }
