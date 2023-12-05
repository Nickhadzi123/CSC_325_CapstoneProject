package com.capstoneproject;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView logoImageView;
    private ForeverHomeFinderMain mainApp;

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
        try {
            // Initialize Firestore
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/key.json");
            FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            Firestore db = firestoreOptions.getService();

            // Retrieve the user document from the "users" collection
            DocumentReference docRef = db.collection("users").document(username);
            ApiFuture<DocumentSnapshot> future = docRef.get();

            DocumentSnapshot document = future.get();
            if (document.exists()) {
                Map<String, Object> userData = document.getData();

                // Validate the password (replace with your actual password validation logic)
                String storedPassword = (String) userData.get("password");
                if (storedPassword.equals(password)) {
                    // Passwords match, authentication successful
                    return true;
                }
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Authentication failed
        return false;
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