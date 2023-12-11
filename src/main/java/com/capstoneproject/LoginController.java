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
        User authenticatedUser = authenticateUser(username, password);

        if (authenticatedUser != null) {
            // If login successful, load the appropriate dashboard
            if (authenticatedUser.isEmployee()) {
                mainApp.showEmployeeDashboard(authenticatedUser);
            } else {
                mainApp.showMainApplication(authenticatedUser);
            }
        } else {
            showAlert("Login Failed", "Invalid username or password. Please try again.");
        }
    }

    private User authenticateUser(String username, String password) {
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

                    // Create a User object with the retrieved data
                    User authenticatedUser = new User();
                    authenticatedUser.setUsername(username);
                    authenticatedUser.setPassword(storedPassword);
                    authenticatedUser.setEmail((String) userData.get("email"));
                    authenticatedUser.setFirstName((String) userData.get("firstName"));
                    authenticatedUser.setLastName((String) userData.get("lastName"));
                    authenticatedUser.setPhone((String) userData.get("phone"));
                    authenticatedUser.setAge(((Long) userData.get("age")).intValue());
                    authenticatedUser.setAddress((String) userData.get("address"));

                    Object isEmployeeObject = userData.get("isEmployee");
                    if (isEmployeeObject instanceof Boolean) {
                        authenticatedUser.setEmployee((Boolean) isEmployeeObject);
                    } else {
                        // Default to false if the isEmployee field is missing or null
                        authenticatedUser.setEmployee(false);
                    }

                    return authenticatedUser;
                }
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Authentication failed
        return null;
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