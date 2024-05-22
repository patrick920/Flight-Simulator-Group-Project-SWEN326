package org.swen326.userinterface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
        ECAM ecam = new ECAM(400, 300);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(artificialHorizon, yawIndicator, ecam);
        vBox.setSpacing(10); // Add some spacing between components

        // Create sliders for pitch, roll, and yaw
        Slider pitchSlider = createSlider("Pitch", -90, 90, 0);
        Slider rollSlider = createSlider("Roll", -180, 180, 0);
        Slider yawSlider = createSlider("Yaw", -180, 180, 0);

        // Add listeners to sliders to update artificial horizon and yaw indicator
        pitchSlider.valueProperty().addListener((observable, oldValue, newValue) -> artificialHorizon.setPitch(newValue.doubleValue()));
        rollSlider.valueProperty().addListener((observable, oldValue, newValue) -> artificialHorizon.setRoll(newValue.doubleValue()));
        yawSlider.valueProperty().addListener((observable, oldValue, newValue) -> yawIndicator.setYaw(newValue.doubleValue()));

        VBox controls = new VBox(10, pitchSlider, rollSlider, yawSlider);
        controls.setPadding(new Insets(10));
        
        pane.setCenter(vBox);
        pane.setRight(controls);

        Scene scene = new Scene(pane, 1280, 720);
        scene.setFill(Color.LIGHTGRAY); // Set a neutral background color for the scene
        primaryStage.setTitle("Cockpit View");
        primaryStage.setScene(scene);
        primaryStage.show();

        // For demonstration purposes, let's set some initial values
        artificialHorizon.setPitch(0);
        artificialHorizon.setRoll(0);
        yawIndicator.setYaw(0);

        // Test the ECAM messages
        ecam.sendWarning("Engine Fire", 3);
        ecam.sendWarning("Fuel Leak", 2);
        ecam.sendWarning("System Redundancy Lost", 1);
        ecam.sendWarning("Normal Operation", 0);
    }

    private Slider createSlider(String label, double min, double max, double value) {
        Label sliderLabel = new Label(label);
        Slider slider = new Slider(min, max, value);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit((max - min) / 5);
        slider.setBlockIncrement(1);
        VBox sliderBox = new VBox(5, sliderLabel, slider);
        return slider;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
