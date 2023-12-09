package com.capstoneproject;

import javafx.beans.value.ObservableValue;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private String id;
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty species = new SimpleStringProperty();
    private final SimpleIntegerProperty age = new SimpleIntegerProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();
    private List<Appointment> appointments = new ArrayList<>();

    // Constructors, getters, and setters...

    public Animal() {
        // Default constructor required for Firestore deserialization
    }

    public Animal(String id, String name, String species, int age, String description) {
        this.id = id;
        this.name.set(name);
        this.species.set(species);
        this.age.set(age);
        this.description.set(description);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSpecies() {
        return species.get();
    }

    public SimpleStringProperty speciesProperty() {
        return species;
    }

    public void setSpecies(String species) {
        this.species.set(species);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return String.valueOf(name); // Display the name of the animal in the ListView
    }

}
