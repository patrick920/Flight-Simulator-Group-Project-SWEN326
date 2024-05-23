package org.swen326.simulator.map;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Point> waypoints;

    public Map() {
        waypoints = new ArrayList<>();
    }

    public void addWaypoint(double x, double y, double z) {
        waypoints.add(new Point(x, y, z));
    }

    public List<Point> getWaypoints() {
        return waypoints;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Map:\n");
        for (Point waypoint : waypoints) {
            sb.append(waypoint).append("\n");
        }
        return sb.toString();
    }
}
