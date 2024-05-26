package org.swen326.userinterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ECAMTest {
    private ECAM ecam;

    @BeforeEach
    void setUp() {
        ecam = new ECAM(200, 200);
    }

    @Test
    void testSendWarningNullMessage() {
        assertThrows(IllegalArgumentException.class, () -> ecam.sendWarning(null, 1));
    }

    @Test
    void testSendWarningSeverity3() {
        ecam.sendWarning("Test message", 3);
        assertEquals(1, ecam.warningMessages.size());
        assertEquals("Test message", ecam.warningMessages.get(0));
    }

    @Test
    void testSendWarningSeverity2() {
        ecam.sendWarning("Test message", 2);
        assertEquals(1, ecam.warningMessages.size());
        assertEquals("Test message", ecam.warningMessages.get(0));
    }

    @Test
    void testSendWarningSeverity1() {
        ecam.sendWarning("Test message", 1);
        assertEquals(1, ecam.warningMessages.size());
        assertEquals("Test message", ecam.warningMessages.get(0));
    }

    @Test
    void testSendWarningSeverity0() {
        ecam.sendWarning("Test message", 0);
        assertEquals(1, ecam.normalMessages.size());
        assertEquals("Test message", ecam.normalMessages.get(0));
    }

    @Test
    void testSendWarningInvalidSeverity() {
        ecam.sendWarning("Test message", -1);
        assertEquals(1, ecam.normalMessages.size());
        assertEquals("Test message", ecam.normalMessages.get(0));
    }

    @Test
    void testSendWarningMessageLimit() {
        for (int i = 0; i < 11; i++) {
            ecam.sendWarning("Test message " + i, 3);
        }
        assertEquals(10, ecam.warningMessages.size());
        assertEquals("Test message 1", ecam.warningMessages.get(0));
    }
}