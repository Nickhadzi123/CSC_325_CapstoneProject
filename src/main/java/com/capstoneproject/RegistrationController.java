package com.capstoneproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField ageField;

    private LoginController loginController; // Declare loginController at the class level

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private Stage stage;
    private ForeverHomeFinderMain mainApp;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainApplication(ForeverHomeFinderMain mainApp) {
        this.mainApp = mainApp;
    }

    public void registerButtonClicked(ActionEvent actionEvent) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        boolean isEmployee = false;

        // Validate age input
        int age = 0;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            showAlert("Registration Error", "Please enter a valid age.", Alert.AlertType.ERROR);
            return;
        }

        // Firebase Firestore registration logic
        try {
            // Initialize Firestore
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/key.json");
            FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            Firestore db = firestoreOptions.getService();

            // Register the user with Firestore
            User newUser = new User(username, password, email, firstName, lastName, phoneNumber, age, address, isEmployee);
            newUser.registerUser();

            showAlert("Registration Successful", "Welcome, " + username + "!" + "\n" + "Please log in to continue.", Alert.AlertType.INFORMATION);
            switchToLoginScreen();

        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            showAlert("Registration Error", "Failed to register the user. Please try again.", Alert.AlertType.ERROR);
        }
    }

    private void switchToLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));

            LoginController loginController = loader.getController();
            loginController.setMainApplication(mainApp);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to switch to the login scene.", Alert.AlertType.ERROR);
        }
    }
        // Validation and registration logic
    public void goBackButtonClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));

            LoginController loginController = loader.getController();
            loginController.setMainApplication(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to switch to the login scene.", Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
}
