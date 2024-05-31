package org.swen326.userinterface;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class YawIndicator extends Canvas {

    private double yaw;

    public YawIndicator(double width, double height) {
        super(width, height);
        yaw = 0;
        draw();
    }

    public synchronized void setYaw(double yaw) {
        if (Double.isNaN(yaw) || Double.isInfinite(yaw)) {
            throw new IllegalArgumentException("Yaw must be a finite number");
        }
        this.yaw = yaw;
        draw();
    }

    private void draw() {
        // Use JavaFX thread to ensure thread safety in UI operations
        Platform.runLater(() -> {
            double width = getWidth();
            double height = getHeight();
            GraphicsContext gc = getGraphicsContext2D();

            // Clear the canvas
            gc.setFill(Color.DARKGRAY); // Set a dark gray background to distinguish from black
            gc.fillRect(0, 0, width, height);

            // Draw the white horizontal bar
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            double barWidth = width * 0.8;
            double barHeight = 4;
            double barX = (width - barWidth) / 2;
            double barY = height / 2 - barHeight / 2;
            gc.strokeLine(barX, height / 2, barX + barWidth, height / 2);

            // Draw the small black rectangle
            double rectWidth = 10;
            double rectHeight = 20;
            double centerX = width / 2;
            double centerY = height / 2;
            double rectX = centerX + yaw - rectWidth / 2;
            double rectY = centerY - rectHeight / 2;

            gc.setFill(Color.BLACK);
            gc.fillRect(rectX, rectY, rectWidth, rectHeight);

            // Draw the fixed reference marks
            double refMarkHeight = 10;
            double refMarkWidth = 2;
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.strokeLine(centerX - rectWidth / 2, centerY - refMarkHeight, centerX - rectWidth / 2, centerY + refMarkHeight);
            gc.strokeLine(centerX + rectWidth / 2, centerY - refMarkHeight, centerX + rectWidth / 2, centerY + refMarkHeight);

            // Draw the outer frame
            double frameThickness = 5;
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(frameThickness);
            gc.strokeRect(frameThickness / 2, frameThickness / 2, width - frameThickness, height - frameThickness);

            // Draw the box showing current yaw value
            gc.setFill(Color.WHITE);
            gc.fillRect(10, 10, 60, 30);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(10, 10, 60, 30);
            gc.setFill(Color.BLACK);
            gc.fillText("Yaw: " + yaw, 20, 30);
        });
    }

    public Integer getYaw() {
        return (int) yaw;
    }
}
