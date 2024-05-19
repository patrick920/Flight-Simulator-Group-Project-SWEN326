package sensors;

import map.Flight;
import map.Point;

public class Sensor {
	
	public double variance;
	public Sensor() {
		variance = 0;
	}
	
	/**
	 * Return the roll with some deviance
	 * @return - Roll as picked up by the sensor
	 */
	public double getRoll() {
		return Environment.roll + Math.random()*180*variance;
	}
	
	/**
	 * Update the environment values after each time tick
	 * @param time - Time of update in seconds
	 * @param aileron - Ailerons for adjusting roll
	 * @param rudder - Rudder for adjusting yaw
	 * @param elevator - Elevator for adjusting pitch
	 */
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
		double airspeedChange = ((Engine.getThrust() - 0.30*(Environment.altitude/42000)*Plane.maxThrust) - Environment.pitch)*(equilibrium_speed(Engine.getThrust(), Environment.pitch) - Environment.airspeed)*timeChange;
		
		double roll_change = ((aileron.expected_roll - Environment.roll)*aileron.getTurnSpeed() + asymmetric_thrust())*timeChange;
		double yaw_change = ((rudder.expected_yaw - Environment.yaw)*rudder.getTurnSpeed() + asymmetric_thrust())*timeChange;
		double pitch_change = ((elevator.expected_pitch - Environment.pitch)*elevator.getTurnSpeed()*thrust_differential(Engine.getThrust()))*timeChange;
		
		double headingChange = (Environment.roll*0.1 + Environment.yaw*0.1)*timeChange;
		
		double deltaX = 0;
		double deltaY = 0;
		
		if (Flight.heading <= 90 && Flight.heading >= 0) {
			deltaX = distMoved*Math.sin(Flight.heading);
			deltaY = distMoved*Math.cos(Flight.heading);
		}
		
		else if (Flight.heading > 90) {
			deltaX = distMoved*Math.cos(Flight.heading-90);
			deltaY = distMoved*Math.sin(Flight.heading-90);
		}
		
		else if (Flight.heading < 0 && Flight.heading >= -90) {
			deltaX = distMoved*Math.sin(-Flight.heading);
			deltaY = distMoved*Math.cos(-Flight.heading);
		}
		
		else if (Flight.heading < -90) {
			deltaX = distMoved*Math.cos(-Flight.heading-90);
			deltaY = distMoved*Math.sin(-Flight.heading-90);
		}
		
		Flight.currPos.latitude += deltaY;
		Flight.currPos.longitude += deltaX;
		Environment.airspeed += airspeedChange;
		Environment.setRoll(Environment.roll + roll_change);
		Environment.setYaw(Environment.yaw + yaw_change);
		Environment.setPitch(Environment.pitch + pitch_change);
		Environment.altitude += altitudeClimbed;
		Environment.time = time;
		Flight.setHeading(Flight.heading + (float)headingChange);
		
	}
	
	/**
	 * Calculate impact of thrust on pitch
	 */
	public static double thrust_differential(double thrust) {
		return (thrust - Plane.minThrust)/(Plane.maxThrust-Plane.minThrust);
	}
	
	/**
	 * Simulate asymmetric thrust effect on roll and yaw.
	 * @return - Difference between thrust of left and right engines
	 */
	public static double asymmetric_thrust() {
		return Engine.left_thrust - Engine.right_thrust;
	}
	
	/**
	 * Calculate speed at which speed does not change assuming thrust and pitch stays the same
	 * @param thrust - Thrust of airplane
	 * @param pitch - Pitch of airplane
	 * @return
	 */
	public static double equilibrium_speed(double thrust, double pitch) {
		return 1.467 * 600 * ((550 + (600-550)*(thrust - Plane.minThrust)/(Plane.maxThrust - Plane.minThrust))/600)/Math.pow(1.2, Environment.pitch);
	}
}