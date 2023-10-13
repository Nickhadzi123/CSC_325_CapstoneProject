package com.example.capstoneproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class ForeverHomeFinderController {
    //Initializing Variables
    @FXML
    private Button loginButton;
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
    @FXML
    private Button escapeButton;

    //Universal Integer Width Variable
    private final int TEXTBOXWIDTH = 200;

    //Boolean if you press the escape button
    private boolean wentBackToScreen;

    @FXML
    protected void onLoginButtonClick() { //When Button is clicked, this function will activate the initialization of login UI

        escapeButton.setVisible(true);

        //Will make the GUI elements visible
        loginButton.setVisible(false);
        passwordVisibleBox.setVisible(true);
        username.setVisible(true);
        password.setVisible(true);
        usernameText.setVisible(true);
        passwordText.setVisible(true);

        //Will set the width of the Username box
        username.prefWidth(TEXTBOXWIDTH);
        username.setMaxWidth(TEXTBOXWIDTH);
        username.requestLayout();

        //Will set the width of the Password box
        password.prefWidth(TEXTBOXWIDTH);
        password.setMaxWidth(TEXTBOXWIDTH);
        password.requestLayout();

        //Will set width of the visiblePassword Box
        visiblePassword.setMaxWidth(TEXTBOXWIDTH);

        //Title text for username and password elements respectively
        usernameText.setText("Username: ");
        passwordText.setText("Password: ");

        //Will set the same text properties as the password variable for the visiblePassword
        password.textProperty().bindBidirectional(visiblePassword.textProperty());

        if(wentBackToScreen){
            visiblePassword.setVisible(false);
        }
    }

    @FXML
    protected void onEscapeButtonClick(){//If the button is clicked, the user will be brought back to login/sign in screen
        //Will change the visibility of the objects
        escapeButton.setVisible(false);
        loginButton.setVisible(true);
        passwordVisibleBox.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        usernameText.setVisible(false);
        passwordText.setVisible(false);
        visiblePassword.setVisible(false);

        wentBackToScreen = true;//Boolean value to switch password visibility to be hidden
        passwordVisibleBox.setSelected(false); //Will uncheck the visible password box

        //Will clear the text from the username and password boxes
        username.clear();
        password.clear();
        visiblePassword.clear();
    }

    @FXML
    protected void checkedPasswordBox(){ //Function will execute if the checkbox is checked/unchecked
        if(passwordVisibleBox.isSelected()){
            //Show the TextField and hide the PasswordField
            visiblePassword.setVisible(true);
            password.setVisible(false);
        } else {
            //Will do the vice versa here
            visiblePassword.setVisible(false);
            password.setVisible(true);
        }
    }
}