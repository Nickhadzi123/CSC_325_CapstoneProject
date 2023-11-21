package com.capstoneproject;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView logoImageView;
    private ForeverHomeFinderMain mainApp;

    private ArrayList<User> userList;

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    public void initialize() {
        // This method will be automatically called when the FXML is loaded
        Image image = new Image(getClass().getResourceAsStream("/FHFlogo.png"));
        logoImageView.setImage(image);
    }

    public void setMainApplication(ForeverHomeFinderMain mainApp) {
        this.mainApp = mainApp;
    }

    public void loginButtonClicked(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform actual login authentication logic here
        boolean authenticationSuccessful = authenticateUser(username, password);

        if (authenticationSuccessful) {
            // If login successful, proceed to the main application or user dashboard
            mainApp.showMainApplication(); // Method to display the main application or dashboard
        } else {
            showAlert("Login Failed", "Invalid username or password. Please try again.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Placeholder for authentication logic (replace with your actual authentication mechanism)
        // Example: Check against a database or hardcoded credentials
        return username.equals("exampleUser") && password.equals("examplePassword");
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
        mainApp.showRegistrationScene();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
}