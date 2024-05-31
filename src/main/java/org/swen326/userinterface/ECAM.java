package org.swen326.userinterface;

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

    private ECAMLogic logic;
    VBox warningColumn;
    VBox normalColumn;

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
    public void sendWarning(String message, int level) {
        logic.sendWarning(message, level);

        Label label = new Label(message);
        label.setFont(new Font("Arial", 16));

        switch (level) {
            case 3:
                label.setTextFill(Color.RED);
                warningColumn.getChildren().add(label);
                break;
            case 2:
                label.setTextFill(Color.ORANGE);
                warningColumn.getChildren().add(label);
                break;
            case 1:
                label.setTextFill(Color.YELLOW);
                warningColumn.getChildren().add(label);
                break;
            case 0:
                label.setTextFill(Color.LIGHTGREEN);
                normalColumn.getChildren().add(label);
                break;
            default:
                label.setTextFill(Color.WHITE);
                normalColumn.getChildren().add(label);
                break;
        }

        // Maintain the message limit for both columns
        if (logic.getWarningMessages().size() > 10) {
            warningColumn.getChildren().remove(0);
        }
        if (logic.getNormalMessages().size() > 10) {
            normalColumn.getChildren().remove(0);
        }
    }

    public List<String> getWarningMessages() {
        return logic.getWarningMessages();
    }

    public List<String> getNormalMessages() {
        return logic.getNormalMessages();
    }
}

class ECAMLogic {
    private List<String> warningMessages;
    private List<String> normalMessages;

    public ECAMLogic() {
        warningMessages = new ArrayList<>();
        normalMessages = new ArrayList<>();
    }

    public void sendWarning(String message, int level) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        switch (level) {
            case 3:
            case 2:
            case 1:
                warningMessages.add(message);
                break;
            case 0:
            default:
                normalMessages.add(message);
                break;
        }

        // Maintain the message limit for both lists
        if (warningMessages.size() > 10) {
            warningMessages.remove(0);
        }
        if (normalMessages.size() > 10) {
            normalMessages.remove(0);
        }
    }

    public List<String> getWarningMessages() {
        return warningMessages;
    }

    public List<String> getNormalMessages() {
        return normalMessages;
    }
}
