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
 */
public class UserInterface extends Application {
    /**
     * The home page is displayed to the user when they start the application.
     */
    private HomePage homePage;

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        //Scene scene = new Scene(new StackPane(l), 640, 480);
        //stage.setScene(scene);
        //stage.show();
        stage.setTitle("Aircraft Simulation");
        homePage = new HomePage(stage); //Initialise the home page. This will NOT display it on the screen.
        homePage.display(stage); //Actually display the home page on the screen.
    }

    public void initialise(){
        launch();
    }
}
