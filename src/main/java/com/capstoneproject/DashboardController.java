package com.capstoneproject;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DashboardController {

    @FXML
    private ListView<Animal> animalListView; // Displaying Animal objects in the ListView

    @FXML
    private TextField searchField;

    private Firestore db;
    private ObservableList<Animal> animalList;

    @FXML
    public void initialize(User user) {
        initializeFirestore();
        initializeListView();
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
}

