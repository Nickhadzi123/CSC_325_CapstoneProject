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

    //Universal Integer Width Variable
    private int textBoxWidth = 200;

    @FXML
    protected void onHelloButtonClick() { //When Button is clicked, this function will activate the initialization of login UI

        //Will make the GUI elements visible
        loginButton.setVisible(false);
        passwordVisibleBox.setVisible(true);
        username.setVisible(true);
        password.setVisible(true);
        usernameText.setVisible(true);
        passwordText.setVisible(true);

        //Will set the width of the Username box
        username.prefWidth(textBoxWidth);
        username.setMaxWidth(textBoxWidth);
        username.requestLayout();

        //Will set the width of the Password box
        password.prefWidth(textBoxWidth);
        password.setMaxWidth(textBoxWidth);
        password.requestLayout();

        //Will set width of the visiblePassword Box
        visiblePassword.setMaxWidth(textBoxWidth);

        //Title text for username and password elements respectively
        usernameText.setText("Username: ");
        passwordText.setText("Password: ");

        //Will set the same text properties as the password variable for the visiblePassword
        password.textProperty().bindBidirectional(visiblePassword.textProperty());
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