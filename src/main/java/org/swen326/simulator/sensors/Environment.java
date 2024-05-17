package org.swen326.simulator.sensors;

public class Environment {
	public static double time = 0;
	public static double altitude = 0;
	public static double roll = 0;
	public static double yaw = 0;
	public static double pitch = 0;
	public static double airspeed = 0;
	
	
	public static void setYaw(double yaw) {
		if (yaw > 180) {
			Environment.yaw = (yaw - 360);
			return;
		}
		else if (yaw < -180) {
			Environment.yaw = (yaw + 360);
			return;
		}
		Environment.yaw = yaw;
	}
	
	
	
	public static void setRoll(double roll) {
		if (roll > 60) {
			Environment.roll = roll - 120;
			return;
		}
		else if (roll < -60) {
			Environment.roll = roll + 120;
			return;
		}
		Environment.roll = roll;
	}
	
	public static void setPitch(double pitch) {
		if (pitch > 30) {
			Environment.pitch = pitch - 60;
			return;
		}
		else if (pitch < -30) {
			Environment.pitch = pitch + 60;
			return;
		}
		Environment.pitch = pitch;
	}
}
