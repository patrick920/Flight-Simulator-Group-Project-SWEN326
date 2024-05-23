package org.swen326.simulator.map;

public class Point {
	public double latitude;
	public double longitude;
	public double altitude;
	public Point(double x, double y, double z) {
		latitude = y;
		longitude = x;
		altitude = z;
	}
	public double getX() {
		return longitude;
	}
	public double getY() {
		return latitude;
	}
	public double getZ() {
		return altitude;
	}
}
