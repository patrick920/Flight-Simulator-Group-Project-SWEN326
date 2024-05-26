package org.swen326.userinterface;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swen326.application.Main;
import org.swen326.simulator.Simulator;

import static org.junit.jupiter.api.Assertions.*;

class HomePageTest {
    private HomePage homePage;
    private Main main;

    private Simulator simulator;

    private UserInterface userInterface;

    private Stage stage;

    @BeforeEach
    void setUp() {
        main = new Main();
        simulator = new Simulator();
        userInterface = new UserInterface(main, simulator);
        stage = new Stage();

        //public HomePage(Main main, Simulator simulator, UserInterface userInterface, Stage stage)
        homePage = new HomePage(main, simulator, userInterface, stage);
    }

    @Test
    void getSelectedAircraftType() {
        boolean exceptionThrow = false;
        try{

        } catch(IllegalArgumentException iae){exceptionThrow = true;}
    }

    @Test
    void startSimulationButtonClicked() {
    }

    @Test
    void displayInvalidInputMessage() {
    }

    @Test
    void display() {
    }
}