package org.swen326.userinterface;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

public class ArtificialHorizon extends Canvas {

    private double pitch;
    private double roll;

    public ArtificialHorizon(double width, double height) {
        super(width, height);
        pitch = 0;
        roll = 0;

        // Set a circular clip
        Circle clip = new Circle(width / 2, height / 2, Math.min(width, height) / 2);
        this.setClip(clip);

        draw();
    }

    public double getPitch() {
        return pitch;
    }

    public double getRoll() {
        return roll;
    }

    public synchronized void setPitch(double pitch) {
        if (Double.isNaN(pitch) || Double.isInfinite(pitch)) {
            throw new IllegalArgumentException("Pitch must be a finite number");
        }
        this.pitch = pitch;
        draw();
    }

    public synchronized void setRoll(double roll) {
        if (Double.isNaN(roll) || Double.isInfinite(roll)) {
            throw new IllegalArgumentException("Roll must be a finite number");
        }
        this.roll = roll;
        draw();
    }

    private void draw() {
        Platform.runLater(() -> {
            double width = getWidth();
            double height = getHeight();
            GraphicsContext gc = getGraphicsContext2D();

            // Clear the canvas
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, width, height);

            gc.save();
            Rotate rotate = new Rotate(-roll, width / 2, height / 2);
            gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());

            gc.setFill(Color.SKYBLUE);
            gc.fillRect(0, 0, width, height / 2 - pitch);
            gc.setFill(Color.BURLYWOOD);
            gc.fillRect(0, height / 2 - pitch, width, height);

            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.strokeLine(0, height / 2 - pitch, width, height / 2 - pitch);
            gc.restore();

            // Draw the fixed markings
            gc.setStroke(Color.RED);
            gc.strokeLine(width / 2 - 10, height / 2, width / 2 + 10, height / 2);
            gc.strokeLine(width / 2, height / 2 - 10, width / 2, height / 2 + 10);
        });
    }
}
