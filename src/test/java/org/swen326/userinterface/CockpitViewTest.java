package org.swen326.userinterface;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class CockpitViewTest extends ApplicationTest {

    private CockpitView cockpitView;
    private ECAM ecam;
    private ArtificialHorizon artificialHorizon;
    private YawIndicator yawIndicator;
    private Slider pitchSlider;
    private Slider rollSlider;
    private Slider yawSlider;

    @Override
    public void start(Stage stage) {
        cockpitView = new CockpitView(stage);
        cockpitView.start(stage);

        ecam = lookup(".vbox").queryAll().stream()
                .filter(node -> node instanceof ECAM)
                .map(node -> (ECAM) node)
                .findFirst()
                .orElse(null);

        artificialHorizon = lookup(".vbox").queryAll().stream()
                .filter(node -> node instanceof ArtificialHorizon)
                .map(node -> (ArtificialHorizon) node)
                .findFirst()
                .orElse(null);

        yawIndicator = lookup(".vbox").queryAll().stream()
                .filter(node -> node instanceof YawIndicator)
                .map(node -> (YawIndicator) node)
                .findFirst()
                .orElse(null);

        pitchSlider = lookup(".slider").nth(0).query();
        rollSlider = lookup(".slider").nth(1).query();
        yawSlider = lookup(".slider").nth(2).query();
    }

    @Test
    public void testInitialization() {
        assertNotNull(ecam, "ECAM should be initialized");
        assertNotNull(artificialHorizon, "ArtificialHorizon should be initialized");
        assertNotNull(yawIndicator, "YawIndicator should be initialized");
        assertNotNull(pitchSlider, "Pitch slider should be initialized");
        assertNotNull(rollSlider, "Roll slider should be initialized");
        assertNotNull(yawSlider, "Yaw slider should be initialized");
    }

    @Test
    public void testSliderInteraction() {
        interact(() -> {
            pitchSlider.setValue(45);
            rollSlider.setValue(90);
            yawSlider.setValue(135);
        });

        assertEquals(45, artificialHorizon.getPitch(), "ArtificialHorizon should update pitch");
        assertEquals(90, artificialHorizon.getRoll(), "ArtificialHorizon should update roll");
        assertEquals(135, yawIndicator.getYaw(), "YawIndicator should update yaw");
    }

    @Test
    public void testECAMMessages() {
        Platform.runLater(() -> {
            ecam.sendWarning("Engine Fire", 3);
            ecam.sendWarning("Fuel Leak", 2);
            ecam.sendWarning("System Redundancy Lost", 1);
            ecam.sendWarning("Normal Operation", 0);
        });

        sleep(500);  // Give some time for the Platform.runLater to execute

        assertEquals(4, ecam.getWarningMessages().size() + ecam.getNormalMessages().size(), "ECAM should have 4 messages");
        assertEquals("Engine Fire", ecam.getWarningMessages().get(0), "First warning message should be 'Engine Fire'");
        assertEquals("Fuel Leak", ecam.getWarningMessages().get(1), "Second warning message should be 'Fuel Leak'");
        assertEquals("System Redundancy Lost", ecam.getWarningMessages().get(2), "Third warning message should be 'System Redundancy Lost'");
        assertEquals("Normal Operation", ecam.getNormalMessages().get(0), "First normal message should be 'Normal Operation'");
    }
}
