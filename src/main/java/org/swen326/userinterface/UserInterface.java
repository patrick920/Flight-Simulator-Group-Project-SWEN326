package org.swen326.userinterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.swen326.application.Main;
import org.swen326.simulator.Simulator;

/**
 * This class contains the JavaFX application and its main components.
 * Sources:
 * https://www.tutorialspoint.com/javafx/javafx_borderpane_layout.htm
 * https://www.tutorialspoint.com/javafx/javafx_vbox_layout.htm
 * https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm
 */
public class UserInterface extends Application {
    /**
     * Constant that defines the window width.
     */
    public static final int WINDOW_WIDTH = 1280;

    /**
     * Constant that defines the window height.
     */
    public static final int WINDOW_HEIGHT = 720;

    /**
     * The home page is displayed to the user when they start the application.
     */
    private HomePage homePage;

    /**
     * A reference to the main class of the program.
     */
    private Main main;

    /**
     * A reference to the simulator.
     */
    private Simulator simulator;

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
        homePage = new HomePage(simulator, this, stage); //Initialise the home page. This will NOT display it on the screen.
        homePage.display(stage); //Actually display the home page on the screen.
    }

    /*
    public UserInterface(Main main, Simulator simulator){
        this.main = main;
        this.simulator = simulator;
    }
    */

    /**
     * This method is used to initialise components for the user interface.
     */
    public void initialise(Main main, Simulator simulator){
        this.main = main;
        System.out.println("In UserInterface.java initialise() method: simulator = " + simulator);
        this.simulator = simulator;
        launch();
    }

    /**
     * Get the reference to the simulator object.
     * @return reference to the simulator object.
     */
    public Simulator simulator(){return simulator;}
}
