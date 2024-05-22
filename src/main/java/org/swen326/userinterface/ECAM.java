package org.swen326.userinterface;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.List;

public class ECAM extends VBox {

    private List<String> messages;
    //private AudioClip level3Sound;
    //private AudioClip level2Sound;

    public ECAM(double width, double height) {
        setPrefSize(width, height);
        setStyle("-fx-background-color: black;");
        messages = new ArrayList<>();
        
        // Load sounds
        //level3Sound = new AudioClip(getClass().getResource("/sounds/level3.mp3").toString());
        //level2Sound = new AudioClip(getClass().getResource("/sounds/level2.mp3").toString());
    }

    public void sendWarning(String message, int level) {
        Label label = new Label(message);
        label.setFont(new Font("Arial", 16));
        
        switch (level) {
            case 3:
                label.setTextFill(Color.RED);
                //level3Sound.play();
                break;
            case 2:
                label.setTextFill(Color.ORANGE);
                //level2Sound.play();
                break;
            case 1:
                label.setTextFill(Color.YELLOW);
                break;
            case 0:
                label.setTextFill(Color.GREEN);
                break;
            default:
                label.setTextFill(Color.WHITE);
                break;
        }
        
        messages.add(message);
        getChildren().add(label);
        
        if (messages.size() > 10) {
            getChildren().remove(0);
            messages.remove(0);
        }
    }
}
