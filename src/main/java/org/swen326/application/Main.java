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

/**
 * SWEN326 Group Project.
 * This is a flight simulation program.
 */
public class Main extends Application{
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
        //userInterface.initialise();
        try {
            //Read JSON file.
            String content = new String(Files.readAllBytes(Paths.get("Boeing 737-800.json")));
            JSONObject jsonObject = new JSONObject(content);

            String model = jsonObject.getString("model");
            String engine_type = jsonObject.getString("engine_type");
            double maximum_thrust = jsonObject.getDouble("maximum_thrust");
            double minimum_thrust = jsonObject.getDouble("minimum_thrust");

            System.out.println("Model: " + model);
            System.out.println("Engine Type: " + engine_type);
            System.out.println("Maximum Thrust: " + maximum_thrust);
            System.out.println("Minimum Thrust: " + minimum_thrust);
        } catch (Exception e) {
            //This may be triggered if the JSON file can't be found.
            e.printStackTrace();
        }
    }

    /**
     * The main method is called when the Java program starts.
     * @param args program arguments. We're not using these in this project.
     */
    public static void main(String[] args) {
        System.out.println("Starting application.");
        launch();
        //Note: there is no need to call the constructor for this class, as calling JavaFX's "launch" method
        //seems to deal with everything.
        //Main main = new Main();
        //main.initialise();
    }

    /**
     * Get the simulation object.
     * @return simulation object.
     */
    public Simulator simulator(){return simulator;}
}