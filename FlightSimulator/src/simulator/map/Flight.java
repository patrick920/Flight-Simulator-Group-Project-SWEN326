package map;

import map.Point;

import java.util.ArrayList;
import java.util.List;

public class Flight {
	public static List<Point> waypoints;
	public static Point currPos;
	public static float heading;
	public static Plane plane;
	private static Flight flight = new Flight(new ArrayList<Point>(), new Point(0, 0));
	private Flight(List<Point> waypoints, Point pos) {
		Flight.waypoints = waypoints;
		currPos = pos;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	public float calcDir() {
		return getAngle(currPos, waypoints.get(0));
	}
	
	public float getAngle(Point pos, Point target) {
	    float angle = (float) Math.toDegrees(Math.atan2(pos.getX() - target.getX(), pos.getY() - target.getY()));

	    return angle;
	}
	
	public void removeWaypoint(Point p) {
		waypoints.remove(p);
	}
	
	public void addWaypoint(int index, Point p) {
		waypoints.add(index, p);
	}
}
