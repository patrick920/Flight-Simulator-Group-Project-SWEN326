package org.swen326.userinterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {
    private Scene homeScene;
    private BorderPane borderPane;

    private VBox centreVBox;

    private Button loadSimulation;
    private Button viewMap;

    /**
     * Create a new HomePage object, used to display the home page.
     * @param stage
     */
    public HomePage(Stage stage){
        centreVBox = new VBox();
        loadSimulation = new Button("Load Simulation");
        viewMap = new Button("View Map");
        centreVBox.getChildren().addAll(loadSimulation, viewMap);
        centreVBox.setAlignment(Pos.CENTER);

        borderPane = new BorderPane();
        borderPane.setCenter(centreVBox);

        homeScene = new Scene(borderPane, 400, 400);
    }

    /**
     * Display the home page on the screen.
     * @param stage The JavaFX stage.
     */
    public void display(Stage stage){
        stage.setScene(homeScene);
        stage.show();
    }
}
