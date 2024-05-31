package org.swen326.userinterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ECAMTest extends ApplicationTest {

    private ECAM ecam;

    @Override
    public void start(Stage stage) {
        // Initialize the ECAM instance
        ecam = new ECAM(400, 300);
    }

    @Test
    public void testGetNormalMessages() {
        interact(() -> {
            ecam.sendWarning("Normal message 1", 0);
            ecam.sendWarning("Normal message 2", 0);
        });
        List<String> normalMessages = ecam.getNormalMessages();
        assertEquals(2, normalMessages.size());
        assertEquals("Normal message 1", normalMessages.get(0));
        assertEquals("Normal message 2", normalMessages.get(1));
    }

    @Test
    public void testGetWarningMessages() {
        interact(() -> {
            ecam.sendWarning("Warning message 1", 3);
            ecam.sendWarning("Warning message 2", 2);
        });
        List<String> warningMessages = ecam.getWarningMessages();
        assertEquals(2, warningMessages.size());
        assertEquals("Warning message 1", warningMessages.get(0));
        assertEquals("Warning message 2", warningMessages.get(1));
    }

    @Test
    public void testSendWarningWithNullMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            interact(() -> ecam.sendWarning(null, 1));
        });
    }

    @Test
    public void testSendWarningWithInvalidLevel() {
        String message = "Invalid level message";
        interact(() -> ecam.sendWarning(message, -1));
        assertEquals(1, ecam.getNormalMessages().size());
        assertEquals(message, ecam.getNormalMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel3() {
        String message = "Critical warning message";
        interact(() -> ecam.sendWarning(message, 3));
        assertEquals(1, ecam.getWarningMessages().size());
        assertEquals(message, ecam.getWarningMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel2() {
        String message = "Severe warning message";
        interact(() -> ecam.sendWarning(message, 2));
        assertEquals(1, ecam.getWarningMessages().size());
        assertEquals(message, ecam.getWarningMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel1() {
        String message = "Moderate warning message";
        interact(() -> ecam.sendWarning(message, 1));
        assertEquals(1, ecam.getWarningMessages().size());
        assertEquals(message, ecam.getWarningMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel0() {
        String message = "Normal message";
        interact(() -> ecam.sendWarning(message, 0));
        assertEquals(1, ecam.getNormalMessages().size());
        assertEquals(message, ecam.getNormalMessages().get(0));
    }

    @Test
    public void testWarningMessagesLimit() {
        interact(() -> {
            for (int i = 0; i < 12; i++) {
                ecam.sendWarning("Warning " + i, 3);
            }
        });
        assertEquals(10, ecam.getWarningMessages().size());
        assertEquals("Warning 2", ecam.getWarningMessages().get(0));
    }

    @Test
    public void testNormalMessagesLimit() {
        interact(() -> {
            for (int i = 0; i < 12; i++) {
                ecam.sendWarning("Normal " + i, 0);
            }
        });
        assertEquals(10, ecam.getNormalMessages().size());
        assertEquals("Normal 2", ecam.getNormalMessages().get(0));
    }

    @Test
    public void testDefaultCaseForInvalidLevel() {
        String message = "Default normal message";
        interact(() -> ecam.sendWarning(message, 99));
        assertEquals(1, ecam.getNormalMessages().size());
        assertEquals(message, ecam.getNormalMessages().get(0));
    }

    @Test
    public void testUIColumns() {
        String warningMessage = "Warning message";
        interact(() -> ecam.sendWarning(warningMessage, 3));
        assertEquals(1, ecam.getWarningMessages().size());
        assertEquals(warningMessage, ecam.getWarningMessages().get(0));
        assertEquals(1, ecam.warningColumn.getChildren().size());

        String normalMessage = "Normal message";
        interact(() -> ecam.sendWarning(normalMessage, 0));
        assertEquals(1, ecam.getNormalMessages().size());
        assertEquals(normalMessage, ecam.getNormalMessages().get(0));
        assertEquals(1, ecam.normalColumn.getChildren().size());
    }
}
