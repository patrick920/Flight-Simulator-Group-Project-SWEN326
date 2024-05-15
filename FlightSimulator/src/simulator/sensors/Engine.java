package sensors;

public class Engine {
	public static double left_thrust;
	public static double right_thrust;
	
	public static double getThrust() {
		return (left_thrust + right_thrust)/2;
	}
	
}
