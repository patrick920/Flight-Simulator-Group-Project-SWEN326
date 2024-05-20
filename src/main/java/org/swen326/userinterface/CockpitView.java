package org.swen326.userinterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CockpitView extends Application {

    private Stage primaryStage;

    public CockpitView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        display();
    }

    public void display() {
        BorderPane pane = new BorderPane();
        Pane artificialHorizonPane = new Pane();
        artificialHorizonPane.setPrefSize(250, 250);

        Canvas canvas = new Canvas(250, 250);
        drawArtificialHorizon(canvas.getGraphicsContext2D());

        artificialHorizonPane.getChildren().add(canvas);
        pane.setCenter(artificialHorizonPane);

        Scene scene = new Scene(pane, 1280, 720);
        primaryStage.setTitle("Cockpit View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawArtificialHorizon(GraphicsContext gc) {
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();

        double centerX = width / 2;
        double centerY = height / 2;
        double radius = Math.min(width, height) / 3;

        // Draw sky
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, 0, width, centerY);

        // Draw ground
        gc.setFill(Color.BROWN);
        gc.fillRect(0, centerY, width, centerY);

        // Draw the circle
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Draw the pitch lines
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        for (int i = -3; i <= 3; i++) {
            if (i != 0) {
                double yOffset = i * (radius / 6);
                gc.strokeLine(centerX - radius / 2, centerY + yOffset, centerX + radius / 2, centerY + yOffset);
            }
        }

        // Draw the yaw line (horizontal)
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(2);
        gc.strokeLine(centerX - radius, centerY, centerX + radius, centerY);

        // Draw the roll indicator (vertical)
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeLine(centerX, centerY - radius, centerX, centerY + radius);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
