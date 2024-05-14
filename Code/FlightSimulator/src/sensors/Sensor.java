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
		
	}
}
