package com.capstoneproject;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

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
    private TextArea descriptionField;

    private Animal selectedAnimal;
    private User authenticatedUser;
    private Firestore db;
    private ObservableList<Animal> animalList;

    public void initialize(User user) {
        this.authenticatedUser = user;
            initializeFirestore();
            initializeTableView();
            loadAnimals();

        animalTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleAnimalSelection(newValue));
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
            // Retrieve animals from Firestore and add them to a temporary ObservableList
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

                    // Create a new ObservableList and set it as the items for the TableView
                    ObservableList<Animal> newAnimalList = FXCollections.observableArrayList(animals);
                    animalTableView.setItems(newAnimalList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAddAnimalButton(ActionEvent event) throws ExecutionException, InterruptedException {
        // Get values from text fields
        String name = nameField.getText();
        String species = speciesField.getText();
        int age = Integer.parseInt(ageField.getText());
        String description = descriptionField.getText();

        // Validate input (you can add more validation as needed)

        // Create a new Animal object
        Animal newAnimal = new Animal(name, species, age, description);

        // Add the new animal to Firestore
        addAnimalToFirestore(newAnimal);

        // Clear text fields after adding the animal
        clearInputFields();

    refreshTableView();
    }

    private void addAnimalToFirestore(Animal animal) throws ExecutionException, InterruptedException {
        // Add the new animal to the "animals" collection in Firestore
        DocumentReference docRef = db.collection("animals").add(animal).get();
    }

    private void deleteAnimalFromFirestore(Animal animal) {
        // Check if animal has a valid ID
        if (animal.getId() != null && !animal.getId().isEmpty()) {
            // Delete the animal from the "animals" collection in Firestore
            db.collection("animals").document(animal.getId()).delete();
            refreshTableView();
        } else {
            // Handle the case where the animal ID is null or empty
            System.out.println("Invalid animal ID");
        }
    }
    @FXML
    private void handleDeleteAnimalButton(ActionEvent event) {
        Animal selectedAnimal = animalTableView.getSelectionModel().getSelectedItem();
        if (selectedAnimal != null) {
            deleteAnimalFromFirestore(selectedAnimal);
            refreshTableView();
        }
    }
    private void updateAnimalInFirestore(Animal animal) {
        try {
            // Update the animal in the "animals" collection in Firestore
            // Ensure the animal has a valid ID
            if (animal.getId() != null && !animal.getId().isEmpty()) {
                DocumentReference docRef = db.collection("animals").document(animal.getId());
                docRef.set(animal);
            } else {
                // Handle the case where the animal ID is null or empty
                System.out.println("Invalid animal ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleAnimalSelection(Animal selected) {
        if (selected != null) {
            // Unbind the text fields from the previously selected animal (if any)
            unbindTextFields();

            // Bind text fields to the selected animal's properties
            nameField.textProperty().bindBidirectional(selected.nameProperty());
            speciesField.textProperty().bindBidirectional(selected.speciesProperty());
            ageField.textProperty().bindBidirectional(selected.ageProperty(), new NumberStringConverter());
            descriptionField.textProperty().bindBidirectional(selected.descriptionProperty());

            // Keep track of the selected animal
            selectedAnimal = selected;
        } else {
            // If no animal is selected, clear the text fields and reset the selectedAnimal
            clearInputFields();
            selectedAnimal = null;
        }
    }

    @FXML
    void handleEditAnimalButton(ActionEvent event) {
        if (selectedAnimal != null) {
            // Update the selected animal in Firestore
            updateAnimalInFirestore(selectedAnimal);

            // Clear the selection and reset text fields
            animalTableView.getSelectionModel().clearSelection();
            clearInputFields();
            refreshTableView();
        }
    }
    private void unbindTextFields() {
        if (selectedAnimal != null) {
            // Unbind text fields from the currently selected animal
            nameField.textProperty().unbindBidirectional(selectedAnimal.nameProperty());
            speciesField.textProperty().unbindBidirectional(selectedAnimal.speciesProperty());
            ageField.textProperty().unbindBidirectional(selectedAnimal.ageProperty());
            descriptionField.textProperty().unbindBidirectional(selectedAnimal.descriptionProperty());
        }
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
