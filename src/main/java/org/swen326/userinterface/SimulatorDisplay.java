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

public class SimulatorDisplay {
    
    private Scene homeScene;
    private BorderPane borderPane;
    private VBox centreVBox;
    private ComboBox<String> aircraftTypeDropdown;
    private TextField startLatitudeField;
    private TextField startLongitudeField;
    private TextField endLatitudeField;
    private TextField endLongitudeField;
    private Button startSimulation;
    private Button closeProgram;

    public SimulatorDisplay(Stage stage){
        centreVBox = new VBox();
        
        // Aircraft type dropdown
        aircraftTypeDropdown = new ComboBox<>();
        aircraftTypeDropdown.getItems().addAll("Boeing 747", "Airbus A320", "Cessna 172"); // Add aircraft types dynamically in the future
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

        homeScene = new Scene(borderPane, 1280, 720);

        // Add action to closeProgram button to close the application
        closeProgram.setOnAction(e -> stage.close());
    }

    public void display(Stage stage){
        stage.setScene(homeScene);
        stage.show();
    }
}
