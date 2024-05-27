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
        stage = userInterface.stage();

        homePage = new HomePage(main, simulator, userInterface, stage);
    }

    @Test
    void getSelectedAircraftType() {
//        boolean exceptionThrow = false;
//        try{
//            homePage.getSelectedAircraftType();
//        } catch(IllegalArgumentException iae){exceptionThrow = true;}
    }

    @Test
    void startSimulationButtonClicked() {
//        boolean exceptionThrown = false;
//        try{
//            homePage.startSimulationButtonClicked(null);
//        } catch(IllegalArgumentException iae){exceptionThrown = true;}
//        assertTrue(exceptionThrown);
    }

    @Test
    void displayInvalidInputMessage() {
    }

    @Test
    void display() {
    }
}