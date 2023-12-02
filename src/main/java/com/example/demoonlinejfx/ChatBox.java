package com.example.demoonlinejfx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class ChatBox {
    @FXML
    private TextField inputTextField;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea messageArea;
    @FXML
    private ToggleButton switchSides;
    private BooleanProperty textOnRightSide = new SimpleBooleanProperty(false);

    public void switchTextSides() {
        switchSides.selectedProperty().bindBidirectional(textOnRightSide);
    }

    public void writeInChatBox() {
        messageArea.setEditable(false);
        sendButton.setOnAction(e -> {
            String temp = inputTextField.getText();
//            StackPane msg = createMessageOnImage(temp);
//            messageArea.getChildrenUnmodifiable().add(msg);
            if (textOnRightSide.get()) {
                messageArea.appendText(temp + "\n");
            } else {
                messageArea.setText(messageArea.getText() + "\n" + temp);
            }
            inputTextField.setText("");
        });

        inputTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String temp = inputTextField.getText();
                if (textOnRightSide.get()) {
                    messageArea.appendText(temp + "\n");
                    System.out.println("right");
                } else {
                    messageArea.setText(messageArea.getText() + "\n" + temp);
                }
                inputTextField.setText("");

            }
        });
    }

    public StackPane createMessageOnImage(String message) {
        String MESSAGE_BACKGROUND_IMAGE_URL = "png-chat-bubble.png";
        Image MESSAGE_BACKGROUND_IMAGE = new Image(MESSAGE_BACKGROUND_IMAGE_URL);

        ImageView backgroundImageView = new ImageView(MESSAGE_BACKGROUND_IMAGE);
        backgroundImageView.setFitHeight(50);
        backgroundImageView.setFitWidth(300);

        Label messageLabel = new Label(message);
        messageLabel.setTranslateX(10);
        messageLabel.setTranslateY(10);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(backgroundImageView, messageLabel);

        StackPane.setAlignment(messageLabel, Pos.CENTER_LEFT);

        return pane;
    }
}
