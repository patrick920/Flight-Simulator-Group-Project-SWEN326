package org.swen326.userinterface;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.media.AudioClip;

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

    List<String> warningMessages;
    List<String> normalMessages;
    private VBox warningColumn;
    private VBox normalColumn;

    /**
     * Constructs a new ECAM with the specified width and height.
     * 
     * @param width  the preferred width of the ECAM
     * @param height the preferred height of the ECAM
     */
    public ECAM(double width, double height) {
        setPrefSize(width, height);
        setStyle("-fx-background-color: black;");

        warningMessages = new ArrayList<>();
        normalMessages = new ArrayList<>();

        warningColumn = new VBox();
        normalColumn = new VBox();

        warningColumn.setPrefWidth(width / 2);
        normalColumn.setPrefWidth(width / 2);

        getChildren().addAll(warningColumn, normalColumn);
    }

    /**
     * Sends a warning message with the specified level of severity.
     * Up to 10 messages are displayed in each column. with the left column reserved for warnings
     * 
     * @param message the warning message to send
     * @param level   the level of severity (0-3)
     * @throws IllegalArgumentException if the message is null
     */
    public void sendWarning(String message, int level) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        Label label = new Label(message);
        label.setFont(new Font("Arial", 16));

        switch (level) {
            case 3:
                label.setTextFill(Color.RED);
                warningMessages.add(message);
                warningColumn.getChildren().add(label);
                break;
            case 2:
                label.setTextFill(Color.ORANGE);
                warningMessages.add(message);
                warningColumn.getChildren().add(label);
                break;
            case 1:
                label.setTextFill(Color.YELLOW);
                warningMessages.add(message);
                warningColumn.getChildren().add(label);
                break;
            case 0:
                label.setTextFill(Color.LIGHTGREEN);
                normalMessages.add(message);
                normalColumn.getChildren().add(label);
                break;
            default:
                label.setTextFill(Color.WHITE);
                normalMessages.add(message);
                normalColumn.getChildren().add(label);
                break;
        }

        // Maintain the message limit for both columns
        if (warningMessages.size() > 10) {
            warningColumn.getChildren().remove(0);
            warningMessages.remove(0);
        }
        if (normalMessages.size() > 10) {
            normalColumn.getChildren().remove(0);
            normalMessages.remove(0);
        }
    }
}
