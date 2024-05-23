package org.swen326.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.swen326.simulator.Simulator;
import org.swen326.userinterface.HomePage;
import org.swen326.userinterface.UserInterface;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * SWEN326 Group Project.
 * This is a flight simulation program.
 */
public class Main extends Application{
    private String model = "Default Model";
    private String engine_type = "Default Engine Type";
    private double maximum_thrust = 100;
    private double minimum_thrust = 1;

    /**
     * This method is used to start the JavaFX application.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        System.out.println("Starting the JavaFX application.");
        if(stage == null){}
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        System.out.println("Initialising JavaFX application. JavaFX version " + javafxVersion + ", running on Java " +
                javaVersion + ".");
        stage.setTitle("Aircraft Simulation");
        System.out.println("simulator = " + simulator);
        //homePage = new HomePage(simulator, this, stage); //Initialise the home page. This will NOT display it on the screen.
        //homePage.display(stage); //Actually display the home page on the screen.
        initialise(); //Initialising main program components.

        userInterface.initialise(stage);
    }

    /**
     * This is a reference to the simulator object in the code.
     */
    private Simulator simulator;

    /**
     * The UserInterface class contains the JavaFX application.
     */
    private UserInterface userInterface;

    /**
     * Initialise main program components.
     */
    private void initialise(){
        simulator = new Simulator();
        userInterface = new UserInterface(this, simulator);
        System.out.println("DEBUG: In Main.java, simulator = " + simulator);
    }

    /**
     * The main method is called when the Java program starts.
     * @param args program arguments. We're not using these in this project.
     */
    public static void main(String[] args) {
        System.out.println("Starting application.");
        launch();
    }

    /**
     * Method for parsing the JSON file.
     * @param filename the name of the JSON file.
     * @return a list of strings containing the aircraft details.
     */
    public List<String> parseJSON(String filename){
        List<String> aircraftDetails = new ArrayList<String>();
        try {
            //Read JSON file.
            String content = new String(Files.readAllBytes(Paths.get("src/main/java/org/swen326/application/Planes/" + filename + ".json")));
            JSONObject jsonObject = new JSONObject(content);

            //Get the aircraft details.
            model = jsonObject.getString("model");
            engine_type = jsonObject.getString("engine_type");
            maximum_thrust = jsonObject.getDouble("maximum_thrust");
            minimum_thrust = jsonObject.getDouble("minimum_thrust");

            //Print the aircraft details.
            System.out.println("Model: " + model);
            System.out.println("Engine Type: " + engine_type);
            System.out.println("Maximum Thrust: " + maximum_thrust);
            System.out.println("Minimum Thrust: " + minimum_thrust);

            //Add the aircraft details to the list.
            aircraftDetails.add("Model: " + model);
            aircraftDetails.add("Engine Type: " + engine_type);
            aircraftDetails.add("Maximum Thrust: " + maximum_thrust);
            aircraftDetails.add("Minimum Thrust: " + minimum_thrust);
        } catch (Exception e) {
            //This may be triggered if the JSON file can't be found.
            e.printStackTrace();
        }
        return aircraftDetails;
    }

    /*
     * Method for starting the simulation.
     * Note: This method is not needed as this happens in UserInterface.java.
     */
    //public void startSimulation(){
    //    simulator.runSimulator(maximum_thrust,minimum_thrust);
    //}

    /**
     * Get the simulation object.
     * @return simulation object.
     */
    public Simulator simulator(){return simulator;}
}