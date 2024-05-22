package org.swen326.userinterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        ArtificialHorizon artificialHorizon = new ArtificialHorizon(250, 250);
        YawIndicator yawIndicator = new YawIndicator(250, 100); // Adjusted height for better visibility

        VBox vBox = new VBox();
        vBox.getChildren().addAll(artificialHorizon, yawIndicator);
        vBox.setSpacing(10); // Add some spacing between components
        // Remove the black background color from the VBox
        // vBox.setStyle("-fx-background-color: black;");

        pane.setCenter(vBox);

        Scene scene = new Scene(pane, 1280, 720);
        scene.setFill(Color.LIGHTGRAY); // Set a neutral background color for the scene
        primaryStage.setTitle("Cockpit View");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set up the mouse movement handler to update pitch, roll, and yaw
        scene.setOnMouseMoved(event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            double centerX = scene.getWidth() / 2;
            double centerY = scene.getHeight() / 2;

            double pitch = centerY - mouseY; // Invert Y axis for pitch
            double roll = mouseX - centerX;
            double yaw = mouseX - centerX;

            artificialHorizon.setPitch(pitch);
            artificialHorizon.setRoll(roll);
            yawIndicator.setYaw(yaw);
        });

        // For demonstration purposes, let's set some initial values
        artificialHorizon.setPitch(0);
        artificialHorizon.setRoll(0);
        yawIndicator.setYaw(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
