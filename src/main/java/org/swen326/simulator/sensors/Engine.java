package org.swen326.simulator.sensors;

public class Engine {
	public static double left_thrust;
	public static double right_thrust;
	
	/**
	 * Simplify calculations by taking average of thrust produced by left and right engines
	 * @return
	 */
	public static double getThrust() {
		return (left_thrust + right_thrust)/2;
	}

	public static void setThrust(double thrust) {
		assert thrust <= Plane.maxThrust;
		assert thrust >= Plane.minThrust;
		Engine.left_thrust = thrust;
		Engine.right_thrust = thrust;
	}
	
}
