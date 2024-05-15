package sensors;

public class Sensor {
	public double variance;
	public Sensor() {
		variance = 0;
	}
	public double getRoll() {
		return Environment.roll + Math.random()*180*variance;
	}
	public static void updateValues(double time, Ailerons aileron, Rudder rudder, Elevator elevator) {
		double timeChange = time - Environment.time;
		double distMoved;
		if (Environment.pitch > 0) {
			distMoved = timeChange*Environment.airspeed*Math.cos(Environment.pitch);
		}
		else {
			distMoved = timeChange*Environment.airspeed*Math.cos(-Environment.pitch);
		}
		double altitudeClimbed;
		if (Environment.pitch > 0) {
			altitudeClimbed = timeChange*Environment.airspeed*Math.cos(90 - Environment.pitch);
		}
		else {
			altitudeClimbed = -timeChange*Environment.airspeed*Math.cos(90 + Environment.pitch);
		}
		
		// Fake equation for speed change
		double airspeedChange = ((Engine.thrust - 0.30*(Environment.altitude/42000)*Plane.maxThrust) - Environment.pitch)*(equilibrium_speed(Engine.thrust, Environment.pitch) - Environment.airspeed);
		
		double roll_change = (aileron.expected_roll - Environment.roll)*aileron.getTurnSpeed();
		double yaw_change = (rudder.expected_yaw - Environment.yaw)*rudder.getTurnSpeed();
		double pitch_change = (elevator.expected_pitch - Environment.pitch)*elevator.getTurnSpeed()*thrust_differential(Engine.thrust);
	}
	
	public static double thrust_differential(double thrust) {
		return (thrust - Plane.minThrust)/(Plane.maxThrust-Plane.minThrust);
	}
	
	/**
	 * Calculate expected speed for a given thrust and pitch
	 * @param thrust
	 * @param pitch
	 * @return
	 */
	public static double equilibrium_speed(double thrust, double pitch) {
		return 1.467 * 600 * ((550 + (600-550)*(thrust - Plane.minThrust)/(Plane.maxThrust - Plane.minThrust))/600)/Math.pow(1.2, Environment.pitch);
	}
}
