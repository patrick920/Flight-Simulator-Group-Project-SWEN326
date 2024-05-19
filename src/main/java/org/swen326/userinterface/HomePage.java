package org.swen326.userinterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The home page is what the user sees when they initially start the program. It contains buttons.
 */
public class HomePage {
    /**
     * The scene contains all of the elements currently being displayed on the window for the home screen.
     */
    private Scene homeScene;

    /**
     * The BorderPane layout for the home screen, which can have top, bottom, left, right and center elements.
     */
    private BorderPane borderPane;

    /**
     * The main VBox in the center of the home screen containing the buttons. A VBox aligns components vertically.
     */
    private VBox centreVBox;

    /**
     * A button to load an aircraft simulation.
     */
    private Button loadSimulation;

    /**
     * A button to view the map of the world.
     */
    private Button viewMap;

    /**
     * Create a new HomePage object, used to display the home page. Calling the constructor only initialises the
     * objects. It does not actually display the home screen on the screen.
     * @param stage
     */
    public HomePage(Stage stage){
        //Create a VBox, used to align components vertically.
        centreVBox = new VBox();
        //Create the buttons:
        loadSimulation = new Button("Load Simulation");
        viewMap = new Button("View Map");
        centreVBox.getChildren().addAll(loadSimulation, viewMap); //Add the buttons to the VBox.
        centreVBox.setAlignment(Pos.CENTER); //Align components in the VBox in the center of the screen.

        //Create a new BorderPane layout, and put the VBox containing the main buttons in the center position.
        borderPane = new BorderPane();
        borderPane.setCenter(centreVBox);

        //Initialise the scene which contains all of the elements of the home screen.
        homeScene = new Scene(borderPane, 400, 400);
    }

    /**
     * Display the home page on the screen.
     * @param stage The JavaFX stage.
     */
    public void display(Stage stage){
        stage.setScene(homeScene);
        stage.show(); //Display the stage (window).
    }
}
