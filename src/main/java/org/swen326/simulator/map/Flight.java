  package org.swen326.simulator.map;

  import org.swen326.simulator.sensors.Plane;

  import java.util.ArrayList;
  import java.util.List;

  public class Flight {
	public static List<Point> waypoints;
	public static Point currPos;
	public static float heading;
	public static Plane plane;
	public static double desired_altitude;
	public static double altitude;
	private final static Flight flight = new Flight(new ArrayList<Point>(), new Point(0, 0));
	private Flight(List<Point> waypoints, Point pos) {
		Flight.waypoints = waypoints;
		currPos = pos;
	}

	public Flight getFlight() {
		return flight;
	}
	
	/**
	 * Calculate and return direction to next waypoint
	 * @return - Direction to next waypoint
	 */
	public static float calcDir() {
		return Flight.getAngle(currPos, waypoints.get(0));
	}

	public static float getAngle(Point pos, Point target) {
	    float angle = (float) Math.toDegrees(Math.atan2(pos.getY() - target.getY(), pos.getX() - target.getX()));

	    return angle;
	}

	public static void setHeading(float heading) {
		if (heading > 180) {
			Flight.heading = (float)(heading - 360);
			return;
		}
		else if (heading < -180) {
			Flight.heading = (float)(heading + 360);
			return;
		}
		Flight.heading = heading;
	}

	public void removeWaypoint(Point p) {
		waypoints.remove(p);
	}

	public void addWaypoint(int index, Point p) {
		waypoints.add(index, p);
	}
}
