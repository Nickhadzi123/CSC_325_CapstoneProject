package com.example.capstoneproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class ForeverHomeFinderController {
    @FXML
    private Button loginButton;
    @FXML
    private Label welcomeText;
    @FXML
    private Label usernameText;
    @FXML
    private Label passwordText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField visiblePassword;
    @FXML
    private CheckBox passwordVisibleBox;

    private int textBoxWidth = 200;

    @FXML
    protected void onHelloButtonClick() {
        loginButton.setVisible(false);
        passwordVisibleBox.setVisible(true);

        username.setVisible(true);
        username.prefWidth(textBoxWidth);
        username.setMaxWidth(textBoxWidth);
        username.requestLayout();

        password.setVisible(true);
        password.prefWidth(textBoxWidth);
        password.setMaxWidth(textBoxWidth);
        password.requestLayout();

        usernameText.setVisible(true);
        passwordText.setVisible(true);

        usernameText.setText("Username: ");
        passwordText.setText("Password: ");



        visiblePassword.setMaxWidth(textBoxWidth);

        password.textProperty().bindBidirectional(visiblePassword.textProperty());
    }

    @FXML
    protected void checkedPasswordBox(){
        if(passwordVisibleBox.isSelected()){
            // Show the TextField and hide the PasswordField
            visiblePassword.setVisible(true);
            password.setVisible(false);
        } else {
            // Hide the TextField and show the PasswordField
            visiblePassword.setVisible(false);
            password.setVisible(true);
        }
    }
}