package com.capstoneproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private LocalDateTime dateTime;
    private Animal animal;
    private User user;

    // Constructors, getters, and setters...

    public Appointment(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.animal = animal;
        this.user = user;
    }

    public Appointment(String name, LocalDateTime selectedDateTime) {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
