package com.capstoneproject;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.scene.control.ButtonType;

public class DashboardController {

    @FXML
    private ListView<Animal> animalListView; // Displaying Animal objects in the ListView

    @FXML
    private TextField searchFieldUser;

    @FXML
    private ImageView logoImageView;

    private Firestore db;
    private ObservableList<Animal> animalList;

    @FXML
    public void initialize(User user) {
        Image logoImage = new Image("/userlogo-removebg-preview.png");  // Adjust the path accordingly
        logoImageView.setImage(logoImage);

        initializeFirestore();
        initializeListView();
        loadAnimals();

        animalListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Animal selectedAnimal = animalListView.getSelectionModel().getSelectedItem();
                if (selectedAnimal != null) {
                    showAppointmentConfirmationDialog(selectedAnimal);
                }
            }
        });
    }
    private void showAppointmentConfirmationDialog(Animal animal) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Adoption Appointment");
        confirmationAlert.setHeaderText("Schedule an adoption appointment?");
        confirmationAlert.setContentText("Do you want to schedule an adoption appointment for " + animal.getName() + "?");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showDateTimeInputDialog(animal);
            }
        });
    }
    private void showDateTimeInputDialog(Animal animal) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Adoption Appointment");
        dialog.setHeaderText("Enter Date and Time");
        dialog.setContentText("Please enter the date and time for the adoption appointment (e.g., 2023-12-31 15:30):");

        // Set up a custom callback to validate user input
        Callback<String, Boolean> inputValidator = input -> {
            // Add your validation logic here
            // For simplicity, we assume a basic format validation
            return input.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
        };
        dialog.setResultConverter(input -> inputValidator.call(String.valueOf(input)) ? String.valueOf(input) : null);

        // Show the dialog and wait for user input
        dialog.showAndWait().ifPresent(dateTime -> showConfirmationMessage(animal, dateTime));
    }

    private void showConfirmationMessage(Animal animal, String dateTime) {
        System.out.println("Show Confirmation Message");
        System.out.println("Animal Name: " + animal.getName());
        System.out.println("Date Time: " + dateTime);

        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Adoption Appointment Scheduled");
        confirmationAlert.setHeaderText("Appointment Scheduled Successfully");
        confirmationAlert.setContentText("You have successfully scheduled an adoption appointment for "
                + animal.getName() + " on " + dateTime);

        confirmationAlert.show();
    }

    private void initializeFirestore() {
        try {
            FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("src/main/resources/key.json")))
                    .build();
            db = firestoreOptions.getService();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., show an error message
        }
    }

    private void initializeListView() {
        // Displaying Animal objects in the ListView
        animalList = FXCollections.observableArrayList();
        animalListView.setItems(animalList);

        // Define how to display Animal objects in the ListView
        animalListView.setCellFactory(param -> new ListCell<Animal>() {
            @Override
            protected void updateItem(Animal animal, boolean empty) {
                super.updateItem(animal, empty);

                if (empty || animal == null) {
                    setText(null);
                } else {
                    setText(String.format("Name: %s" +"\n"+"Species: %s" + "\n" + "%d years old" + "\n" + "Description: %s",
                            animal.getName(), animal.getSpecies(), animal.getAge(), animal.getDescription()));
                }
            }
        });
    }

    private void loadAnimals() {
        try {
            // Retrieve animals from Firestore and add them to the ListView
            CollectionReference animalsRef = db.collection("animals");
            animalsRef.addSnapshotListener((queryDocumentSnapshots, e) -> {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }

                if (queryDocumentSnapshots != null) {
                    List<Animal> animals = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Animal animal = document.toObject(Animal.class);
                        animals.add(animal);
                    }

                    // Create a new ObservableList and set it as the items for the ListView
                    ObservableList<Animal> newAnimalList = FXCollections.observableArrayList(animals);
                    animalListView.setItems(newAnimalList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  private void showAlert(String title, String content) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle(title);
      alert.setHeaderText(null);
      alert.setContentText(content);
      alert.showAndWait();
  }
}

