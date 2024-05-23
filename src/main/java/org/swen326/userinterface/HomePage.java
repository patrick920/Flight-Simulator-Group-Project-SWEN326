package org.swen326.userinterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.swen326.simulator.Simulator;

/**
 * This is the home page, which is displayed when the user first displays the program.
 */
public class HomePage {
    /**
     * The scene contains all of the buttons and other components for the home page.
     */
    private Scene homeScene;

    /**
     * BorderPane layout for the home page.
     */
    private BorderPane borderPane;

    /**
     * The VBox displays elements vertically. It is used for the home screen.
     */
    private VBox centreVBox;

    /**
     * Dropdown to choose types of aircraft.
     */
    private ComboBox<String> aircraftTypeDropdown;

    /**
     * Text field to enter the starting latitude for when the simulation starts.
     */
    private TextField startLatitudeField;

    /**
     * Text field to enter the starting longitude for when the simulation starts.
     */
    private TextField startLongitudeField;

    /**
     * Text field to enter the ending latitude (of the plane's intended destination).
     */
    private TextField endLatitudeField;

    /**
     * Text field to enter the ending longitude (of the plane's intended destination.)
     */
    private TextField endLongitudeField;

    /**
     * Button to start the simulation.
     */
    private Button startSimulation;

    /**
     * Button to close the program.
     */
    private Button closeProgram;

    /**
     * A reference to the simulator object which contains the simulation code.
     */
    private Simulator simulator;

    /**
     * A reference to the user interface object, to calls methods inside of there.
     */
    private UserInterface userInterface;

    /**
     * A reference to the stage (which is the JavaFX window.)
     */
    private Stage stage;

    /**
     * Create the home page.
     * @param userInterface
     * @param stage a reference to the stage of the JavaFX application, which is essentially the window.
     */
    public HomePage(Simulator simulator, UserInterface userInterface, Stage stage) {
        //Check for invalid input.
        if(simulator == null){
            throw new IllegalArgumentException("simulator is null.");
        } else if(userInterface == null){
            throw new IllegalArgumentException("userInterface is null.");
        } else if(stage == null){
            throw new IllegalArgumentException("stage is null.");
        }

        this.simulator = simulator;
        this.userInterface = userInterface;
        this.stage = stage;
        centreVBox = new VBox();
        
        // Aircraft type dropdown
        aircraftTypeDropdown = new ComboBox<>();
        // Add aircraft types dynamically in the future
        aircraftTypeDropdown.getItems().addAll("Airbus A320", "Airbus A350", "Boeing 737-800", "Boeing 777-300ER");
        aircraftTypeDropdown.setPromptText("Select Aircraft Type");

        // Start latitude and longitude inputs
        startLatitudeField = new TextField();
        startLatitudeField.setPromptText("Start Latitude");
        startLongitudeField = new TextField();
        startLongitudeField.setPromptText("Start Longitude");
        
        HBox startCoordinatesBox = new HBox(10, 
            new Label("Start Latitude:"), startLatitudeField,
            new Label("Start Longitude:"), startLongitudeField);
        startCoordinatesBox.setAlignment(Pos.CENTER);

        // End latitude and longitude inputs
        endLatitudeField = new TextField();
        endLatitudeField.setPromptText("End Latitude");
        endLongitudeField = new TextField();
        endLongitudeField.setPromptText("End Longitude");

        HBox endCoordinatesBox = new HBox(10, 
            new Label("End Latitude:"), endLatitudeField,
            new Label("End Longitude:"), endLongitudeField);
        endCoordinatesBox.setAlignment(Pos.CENTER);

        // Start simulation and close program buttons
        startSimulation = new Button("Start Simulation");
        startSimulation.setOnAction(e -> {startSimulationButtonClicked(stage);});
        closeProgram = new Button("Close Program");

        // Add all elements to the VBox
        centreVBox.getChildren().addAll(
            new Label("Aircraft Type:"), aircraftTypeDropdown,
            startCoordinatesBox,
            endCoordinatesBox,
            startSimulation, closeProgram
        );
        centreVBox.setAlignment(Pos.CENTER);
        centreVBox.setSpacing(10);

        borderPane = new BorderPane();
        borderPane.setCenter(centreVBox);

        homeScene = new Scene(borderPane, UserInterface.WINDOW_WIDTH, UserInterface.WINDOW_HEIGHT);

        // Add action to closeProgram button to close the application
        closeProgram.setOnAction(e -> stage.close());
    }

    public String getSelectedAircraftType() {
        return aircraftTypeDropdown.getSelectionModel().getSelectedItem();
    }

    /**
     * This method is triggered when the "startSimulation" button is clicked.
     */
    public void startSimulationButtonClicked(Stage stage){
        if(stage == null){
            throw new IllegalArgumentException("stage is null.");
        }
        //TODO: Must do input validation.
        //Source: https://www.geeksforgeeks.org/javafx-textfield/

        //TODO: This violates the power of ten rules: using the new keyword not during the initialisation of
        // the program. Must initialise CockpitView before. Can keep the display() method here however.
        CockpitView cockpitView = new CockpitView(stage);
        cockpitView.display();

        System.out.println("startLatitudeField.getText() = " + startLatitudeField.getText());
    }

    /**
     * Display the home page onto the stage (JavaFX window).
     * @param stage
     */
    public void display(Stage stage){
        if(stage == null){
            throw new IllegalArgumentException("stage is null.");
        }
        stage.setScene(homeScene);
        stage.show();
    }
}
