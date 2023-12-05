package com.capstoneproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.Arrays;
import java.util.List;

public class DashboardController {
    @FXML
    private ListView<Animal> animalListView;

    public void initialize(User user) {

        animalListView.setCellFactory(param -> new AnimalListCell());

        animalListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            displayAnimalDetails(newValue);
        });
    }




    private void displayAnimalDetails(Animal animal) {
        System.out.println("Selected Animal: " + animal.getName());
        // Implement logic to display detailed information about the selected animal
        // This could involve showing a new scene or updating labels on the current scene
    }
}