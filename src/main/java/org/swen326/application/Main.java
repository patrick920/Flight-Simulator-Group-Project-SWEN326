package org.swen326.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.swen326.simulator.Simulator;
import org.swen326.userinterface.UserInterface;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

/**
 * SWEN326 Group Project.
 * This is a flight simulation program.
 */
public class Main{
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
        userInterface = new UserInterface();
        userInterface.initialise(this, simulator);
        try {
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
            e.printStackTrace();
        }
    }

    /**
     * The main method is called when the Java program starts.
     * @param args program arguments. We're not using these in this project.
     */
    public static void main(String[] args) {
        System.out.println("Starting application.");
        Main main = new Main();
        main.initialise();
    }

    /**
     * Get the simulation object.
     * @return simulation object.
     */
    public Simulator simulator(){return simulator;}
}