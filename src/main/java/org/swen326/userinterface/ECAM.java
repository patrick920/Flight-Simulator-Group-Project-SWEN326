package org.swen326.userinterface;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * The ECAM class represents an Electronic Centralized Aircraft Monitor.
 * It is a custom JavaFX control that displays warning and normal messages.
 * 
 * ECAM extends the HBox class and provides methods to send warning messages
 * with different levels of severity.
 */
public class ECAM extends HBox {

    private final ECAMLogic logic;
    final VBox warningColumn;
    final VBox normalColumn;

    /**
     * Constructs a new ECAM with the specified width and height.
     * 
     * @param width  the preferred width of the ECAM
     * @param height the preferred height of the ECAM
     */
    public ECAM(double width, double height) {
        setPrefSize(width, height);
        setStyle("-fx-background-color: black;");

        logic = new ECAMLogic();

        warningColumn = new VBox();
        normalColumn = new VBox();

        warningColumn.setPrefWidth(width / 2);
        normalColumn.setPrefWidth(width / 2);

        getChildren().addAll(warningColumn, normalColumn);
    }

    /**
     * Sends a warning message with the specified level of severity.
     * Up to 10 messages are displayed in each column, with the left column reserved for warnings.
     * 
     * @param message the warning message to send
     * @param level   the level of severity (0-3)
     * @throws IllegalArgumentException if the message is null
     */
    public synchronized void sendWarning(String message, int level) {
        logic.sendWarning(message, level);

        Platform.runLater(() -> {
            Label label = createLabel(message, level);
            if (level >= 1 && level <= 3) {
                warningColumn.getChildren().add(label);
                if (warningColumn.getChildren().size() > 10) {
                    warningColumn.getChildren().remove(0);
                }
            } else {
                normalColumn.getChildren().add(label);
                if (normalColumn.getChildren().size() > 10) {
                    normalColumn.getChildren().remove(0);
                }
            }
        });
    }

    /**
     * Creates a label for the message with the specified level of severity.
     * 
     * @param message the warning message
     * @param level   the level of severity (0-3)
     * @return the created Label
     */
    private Label createLabel(String message, int level) {
        Label label = new Label(message);
        label.setFont(new Font("Arial", 16));
        switch (level) {
            case 3:
                label.setTextFill(Color.RED);
                break;
            case 2:
                label.setTextFill(Color.ORANGE);
                break;
            case 1:
                label.setTextFill(Color.YELLOW);
                break;
            case 0:
                label.setTextFill(Color.LIGHTGREEN);
                break;
            default:
                label.setTextFill(Color.WHITE);
                break;
        }
        return label;
    }

    public List<String> getWarningMessages() {
        return logic.getWarningMessages();
    }

    public List<String> getNormalMessages() {
        return logic.getNormalMessages();
    }

    /**
     * The ECAMLogic class is responsible for managing the warning and normal messages.
     */
    private static class ECAMLogic {
        private final List<String> warningMessages;
        private final List<String> normalMessages;

        public ECAMLogic() {
            warningMessages = new ArrayList<>();
            normalMessages = new ArrayList<>();
        }

        public synchronized void sendWarning(String message, int level) {
            if (message == null) {
                throw new IllegalArgumentException("Message cannot be null");
            }

            switch (level) {
                case 3:
                case 2:
                case 1:
                    warningMessages.add(message);
                    if (warningMessages.size() > 10) {
                        warningMessages.remove(0);
                    }
                    break;
                case 0:
                default:
                    normalMessages.add(message);
                    if (normalMessages.size() > 10) {
                        normalMessages.remove(0);
                    }
                    break;
            }
        }

        public List<String> getWarningMessages() {
            return warningMessages;
        }

        public List<String> getNormalMessages() {
            return normalMessages;
        }
    }
}
