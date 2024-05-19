package org.swen326.userinterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * This class contains the JavaFX application and its main components.
 * Sources:
 * https://www.tutorialspoint.com/javafx/javafx_borderpane_layout.htm
 * https://www.tutorialspoint.com/javafx/javafx_vbox_layout.htm
 * https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm
 */
public class UserInterface extends Application {
    /**
     * The home page is displayed to the user when they start the application.
     */
    private HomePage homePage;

    /**
     * This method is used to start the JavaFX application.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        System.out.println("Initialising JavaFX application. JavaFX version " + javafxVersion + ", running on Java " +
                javaVersion + ".");
        stage.setTitle("Aircraft Simulation");
        homePage = new HomePage(stage); //Initialise the home page. This will NOT display it on the screen.
        homePage.display(stage); //Actually display the home page on the screen.
    }

    /**
     * This method is used to initialise components for the user interface.
     */
    public void initialise(){
        launch();
    }
}
