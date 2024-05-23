package org.swen326.userinterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import org.swen326.application.Main;
import org.swen326.simulator.Simulator;
import org.swen326.simulator.ValidateProblem;

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
     * A reference to the main class of the project.
     */
    private Main main;

    /**
     * Create the home page.
     * @param userInterface
     * @param stage a reference to the stage of the JavaFX application, which is essentially the window.
     */
    public HomePage(Main main, Simulator simulator, UserInterface userInterface, Stage stage) {
        //Check for invalid input.
        if(main == null){
            throw new IllegalArgumentException("main is null.");
        } else if(simulator == null){
            throw new IllegalArgumentException("simulator is null.");
        } else if(userInterface == null){
            throw new IllegalArgumentException("userInterface is null.");
        } else if(stage == null){
            throw new IllegalArgumentException("stage is null.");
        }

        this.main = main;
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
        } else if(startLatitudeField == null){
            throw new IllegalArgumentException("startLatitudeField is null.");
        }


        //TODO: Must do input validation.
        //Source: https://www.geeksforgeeks.org/javafx-textfield/
        ValidateProblem startLatitudeVP = simulator.validateLatitude(startLatitudeField.getText());
        if(!startLatitudeVP.validated()){
            displayInvalidInputMessage("The starting latitude value you entered is invalid:", startLatitudeVP);
            return;
        }

        ValidateProblem startLongitudeVP = simulator.validateLongitude(startLongitudeField.getText());
        if(!startLongitudeVP.validated()){
            displayInvalidInputMessage("The starting longitude value you entered is invalid:", startLongitudeVP);
            return;
        }

        ValidateProblem endLatitudeVP = simulator.validateLatitude(endLatitudeField.getText());
        if(!endLatitudeVP.validated()){
            displayInvalidInputMessage("The end latitude value you entered is invalid:", endLatitudeVP);
            return;
        }

        ValidateProblem endLongitudeVP = simulator.validateLongitude(endLongitudeField.getText());
        if(!endLongitudeVP.validated()){
            displayInvalidInputMessage("The starting latitude value you entered is invalid:", endLongitudeVP);
            return;
        }
//        ValidateProblem startLatitudeVP = simulator.validateLatitude(startLatitudeField.getText());
//        if(!startLatitudeVP.validated()){
//            //Code from: https://www.tutorialspoint.com/how-to-create-a-dialog-in-javafx
//            //Creating a dialog
//            Dialog<String> dialog = new Dialog<String>();
//            //Setting the title
//            dialog.setTitle("Error validating input.");
//            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
//            //Setting the content of the dialog
//            dialog.setContentText("The starting latitude value you entered is invalid:\n" +
//                    startLatitudeVP.errorMessage());
//            dialog.setHeight(400);
//            //Adding buttons to the dialog pane
//            dialog.getDialogPane().getButtonTypes().add(type);
//            dialog.showAndWait();
//            return; //Return from this function to prevent the simulator from being run.
//        }

        //TODO: This violates the power of ten rules: using the new keyword not during the initialisation of
        // the program. Must initialise CockpitView before. Can keep the display() method here however.
        CockpitView cockpitView = new CockpitView(stage);
        cockpitView.display();

        System.out.println("startLatitudeField.getText() = " + startLatitudeField.getText());

        //Call the main class for JSON parsing.
        //Main main = new Main();
        String aircraftType = getSelectedAircraftType();
        List<String> aircraftDetails = main.parseJSON(aircraftType);
        simulator.runSimulator(0, 0); //TODO: Change placeholder values of 0.
    }

    public void displayInvalidInputMessage(String startingMessage, ValidateProblem vp){
        //Source: https://www.geeksforgeeks.org/javafx-textfield/
        if (startingMessage == null){
            throw new IllegalArgumentException("startingMessage is null");
        } else if(vp.validated()){
            throw new IllegalArgumentException("vp.validated() is true. It should be false, as the" +
                    " input is incorrect!");
        }
        //Code from: https://www.tutorialspoint.com/how-to-create-a-dialog-in-javafx
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Error validating input.");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(startingMessage + "\n" +
                vp.errorMessage());
        dialog.setHeight(400);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

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
