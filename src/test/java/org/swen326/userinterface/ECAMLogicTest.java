package org.swen326.userinterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ECAMLogicTest {

    private ECAMLogic ecamLogic;

    @BeforeEach
    public void setUp() {
        ecamLogic = new ECAMLogic();
    }

    @Test
    public void testSendWarningWithNullMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            ecamLogic.sendWarning(null, 1);
        });
    }

    @Test
    public void testSendWarningWithInvalidLevel() {
        String message = "Test warning message";
        assertDoesNotThrow(() -> {
            ecamLogic.sendWarning(message, -1);
        });
        assertEquals(1, ecamLogic.getNormalMessages().size());
        assertEquals(message, ecamLogic.getNormalMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel3() {
        String message = "Test warning message";
        ecamLogic.sendWarning(message, 3);
        assertEquals(1, ecamLogic.getWarningMessages().size());
        assertEquals(message, ecamLogic.getWarningMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel2() {
        String message = "Test warning message";
        ecamLogic.sendWarning(message, 2);
        assertEquals(1, ecamLogic.getWarningMessages().size());
        assertEquals(message, ecamLogic.getWarningMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel1() {
        String message = "Test warning message";
        ecamLogic.sendWarning(message, 1);
        assertEquals(1, ecamLogic.getWarningMessages().size());
        assertEquals(message, ecamLogic.getWarningMessages().get(0));
    }

    @Test
    public void testSendWarningWithLevel0() {
        String message = "Test normal message";
        ecamLogic.sendWarning(message, 0);
        assertEquals(1, ecamLogic.getNormalMessages().size());
        assertEquals(message, ecamLogic.getNormalMessages().get(0));
    }

    @Test
    public void testWarningMessagesLimit() {
        for (int i = 0; i < 12; i++) {
            ecamLogic.sendWarning("Warning " + i, 3);
        }
        assertEquals(10, ecamLogic.getWarningMessages().size());
        assertEquals("Warning 2", ecamLogic.getWarningMessages().get(0));
    }

    @Test
    public void testNormalMessagesLimit() {
        for (int i = 0; i < 12; i++) {
            ecamLogic.sendWarning("Normal " + i, 0);
        }
        assertEquals(10, ecamLogic.getNormalMessages().size());
        assertEquals("Normal 2", ecamLogic.getNormalMessages().get(0));
    }
}
