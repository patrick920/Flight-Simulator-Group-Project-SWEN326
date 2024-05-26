package org.swen326.userinterface;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

import org.swen326.simulator.map.Point;

public class MapDisplay extends Canvas {
    private List<Point> waypoints;
    private Point currentPosition;

    public MapDisplay(double width, double height) {
        super(width, height);
    }

    public void setWaypoints(List<Point> waypoints) {
        this.waypoints = waypoints;
        draw();
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
        draw();
    }

    private void draw() {
        if (currentPosition == null || waypoints == null) {
            return;
        }

        double width = getWidth();
        double height = getHeight();
        GraphicsContext gc = getGraphicsContext2D();

        // Clear the canvas
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, width, height);

        // Translate the coordinate system to center the current position
        gc.translate(width / 2 - currentPosition.getX(), height / 2 - currentPosition.getY());

        // Draw waypoints and lines connecting them
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        for (int i = 0; i < waypoints.size() - 1; i++) {
            Point wp1 = waypoints.get(i);
            Point wp2 = waypoints.get(i + 1);
            gc.strokeLine(wp1.getX(), wp1.getY(), wp2.getX(), wp2.getY());
        }

        // Draw the waypoints
        gc.setFill(Color.RED);
        for (Point waypoint : waypoints) {
            gc.fillOval(waypoint.getX() - 3, waypoint.getY() - 3, 6, 6);
        }

        // Draw the current position
        gc.setFill(Color.GREEN);
        gc.fillOval(currentPosition.getX() - 5, currentPosition.getY() - 5, 10, 10);

        // Reset the transformation
        gc.setTransform(1, 0, 0, 1, 0, 0);
    }
}
