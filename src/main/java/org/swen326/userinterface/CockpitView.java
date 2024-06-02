package org.swen326.userinterface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;

import org.swen326.simulator.map.Point;

/**
 * The CockpitView class represents the graphical user interface for the cockpit view of an aircraft.
 * It displays various components such as the artificial horizon, yaw indicator, ECAM messages, and a map display.
 */
public class CockpitView extends Application {

    private Stage primaryStage;

    /**
     * Constructs a CockpitView object with the specified primary stage.
     *
     * @param primaryStage the primary stage for the cockpit view
     */
    public CockpitView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Starts the cockpit view by displaying the graphical components and setting up event listeners.
     *
     * @param primaryStage the primary stage for the cockpit view
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        display();
    }

    /**
     * Displays the graphical components of the cockpit view, including the artificial horizon, yaw indicator,
     * ECAM messages, and map display. It also sets up event listeners for sliders and initializes some values.
     */
    public void display() {
        // Create and configure the main pane
        BorderPane pane = new BorderPane();

        // Create and configure the graphical components
        ArtificialHorizon artificialHorizon = new ArtificialHorizon(250, 250);
        YawIndicator yawIndicator = new YawIndicator(250, 100);
        ECAM ecam = new ECAM(400, 300);

        // Create a vertical box to hold the graphical components
        VBox vBox = new VBox();
        vBox.getChildren().addAll(artificialHorizon, yawIndicator, ecam);
        vBox.setSpacing(10);

        // Create sliders for pitch, roll, and yaw
        Slider pitchSlider = createSlider("Pitch", -90, 90, 0);
        Slider rollSlider = createSlider("Roll", -180, 180, 0);
        Slider yawSlider = createSlider("Yaw", -180, 180, 0);

        // Add listeners to sliders to update artificial horizon and yaw indicator
        pitchSlider.valueProperty().addListener((observable, oldValue, newValue) -> artificialHorizon.setPitch(newValue.doubleValue()));
        rollSlider.valueProperty().addListener((observable, oldValue, newValue) -> artificialHorizon.setRoll(newValue.doubleValue()));
        yawSlider.valueProperty().addListener((observable, oldValue, newValue) -> yawIndicator.setYaw(newValue.doubleValue()));

        // Create a vertical box to hold the sliders
        VBox controls = new VBox(10, pitchSlider, rollSlider, yawSlider);
        controls.setPadding(new Insets(10));

        // Configure the main pane
        pane.setCenter(vBox);
        pane.setRight(controls);

        // Create and configure the map display
        MapDisplay mapDisplay = new MapDisplay(400, 400);
        mapDisplay.setWaypoints(Arrays.asList(
            new Point(100, 100),
            new Point(200, 150),
            new Point(300, 200),
            new Point(400, 250)
        ));
        mapDisplay.setCurrentPosition(new Point(250, 175));

        // Add the map display to the left side of the pane
        pane.setLeft(mapDisplay);
        BorderPane.setMargin(mapDisplay, new Insets(10));

        // Create and configure the scene
        Scene scene = new Scene(pane, 1280, 720);
        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setTitle("Cockpit View");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set initial values for the artificial horizon and yaw indicator
        artificialHorizon.setPitch(0);
        artificialHorizon.setRoll(0);
        yawIndicator.setYaw(0);

        // Test the ECAM messages
        ecam.sendWarning("Engine Fire", 3);
        ecam.sendWarning("Fuel Leak", 2);
        ecam.sendWarning("System Redundancy Lost", 1);
        ecam.sendWarning("Normal Operation", 0);
    }

    /**
     * Creates a slider with the specified label, minimum value, maximum value, and initial value.
     *
     * @param label the label for the slider
     * @param min the minimum value for the slider
     * @param max the maximum value for the slider
     * @param value the initial value for the slider
     * @return the created slider
     */
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

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
