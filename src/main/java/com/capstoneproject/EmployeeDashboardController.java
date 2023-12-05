package com.capstoneproject;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EmployeeDashboardController {

    @FXML
    private TableView<Animal> animalTableView;

    @FXML
    private TableColumn<Animal, String> nameColumn;

    @FXML
    private TableColumn<Animal, String> speciesColumn;

    @FXML
    private TableColumn<Animal, Integer> ageColumn;

    @FXML
    private TableColumn<Animal, String> descriptionColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField speciesField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField descriptionField;

    private User authenticatedUser;
    private Firestore db;
    private ObservableList<Animal> animalList;

    public void initialize(User user) {
        this.authenticatedUser = user;
            initializeFirestore();
            initializeTableView();
            loadAnimals();
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

    private void initializeTableView() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        speciesColumn.setCellValueFactory(cellData -> cellData.getValue().speciesProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        animalList = FXCollections.observableArrayList();
        animalTableView.setItems(animalList);
    }

    void loadAnimals() {
        try {
            // Retrieve animals from Firestore and add them to the ObservableList
            CollectionReference animalsRef = db.collection("animals");
            animalsRef.addSnapshotListener((queryDocumentSnapshots, e) -> {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }

                if (queryDocumentSnapshots != null) {
                    animalList.clear(); // Clear existing data

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Animal animal = document.toObject(Animal.class);
                        animalList.add(animal);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addAnimal() throws ExecutionException, InterruptedException {
        // Get data from input fields
        String name = nameField.getText();
        String species = speciesField.getText();
        int age = Integer.parseInt(ageField.getText());
        String description = descriptionField.getText();

        // Create a new Animal object
        Animal newAnimal = new Animal(name, species, age, description);

        // Add the new animal to Firestore
        addAnimalToFirestore(newAnimal);

        // Clear input fields
        clearInputFields();

        // Refresh the table view
        refreshTableView();
    }

    private void addAnimalToFirestore(Animal animal) throws ExecutionException, InterruptedException {
        // Add the new animal to the "animals" collection in Firestore
        DocumentReference docRef = db.collection("animals").add(animal).get();
    }

    @FXML
    private void deleteAnimal() {
        // Get selected animal
        Animal selectedAnimal = animalTableView.getSelectionModel().getSelectedItem();

        if (selectedAnimal != null) {
            // Delete the selected animal from Firestore
            deleteAnimalFromFirestore(selectedAnimal);

            // Remove the selected animal from the table view
            animalTableView.getItems().remove(selectedAnimal);
        }
    }

    private void deleteAnimalFromFirestore(Animal animal) {
        // Delete the animal from the "animals" collection in Firestore
        db.collection("animals").document(animal.getName()).delete();
    }

    private void clearInputFields() {
        nameField.clear();
        speciesField.clear();
        ageField.clear();
        descriptionField.clear();
    }

    private void refreshTableView() {
        // Clear the table and reload animals from Firestore
        animalTableView.getItems().clear();
        loadAnimals();
    }
}
