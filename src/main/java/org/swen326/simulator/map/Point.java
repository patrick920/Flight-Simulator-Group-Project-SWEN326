package org.swen326.simulator.map;

public class Point {
	public double latitude;
	public double longitude;
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
