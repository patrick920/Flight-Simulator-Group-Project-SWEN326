package sensors;

public class Sensor {
	public double variance;
	public Sensor() {
		variance = 0;
	}
	public double getRoll() {
		return Environment.roll + Math.random()*180*variance;
	}
	public static void updateValues(double time, double thrust, Ailerons aileron, Rudder rudder, Elevator elevator) {
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
		double airspeedChange = (Engine.thrust - 0.30*(Environment.altitude/42000)*Plane.maxThrust) - Environment.pitch;
	}
}
