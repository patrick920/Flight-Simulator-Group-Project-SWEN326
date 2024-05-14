package map;

public class Point {
	double latitude;
	double longitude;
	public Point(double x, double y) {
		longitude = x;
		latitude = y;
	}
	public double getX() {
		return longitude;
	}
	public double getY() {
		return latitude;
	}
}
