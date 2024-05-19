package org.swen326.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.swen326.simulator.Simulator;
import org.swen326.userinterface.UserInterface;

/**
 * SWEN326 Group Project.
 * This is a flight simulation program.
 */
public class Main{
    /**
     * This is a reference to the simulator object in the code.
     */
    private Simulator simulator;

    private UserInterface userInterface;

    private void initialise(){
        userInterface = new UserInterface();
        userInterface.initialise();
    }

    public static void main(String[] args) {
        System.out.println("Starting application.");
        Main main = new Main();
        main.initialise();
    }

}