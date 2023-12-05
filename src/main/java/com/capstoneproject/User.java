package com.capstoneproject;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String address;
    private boolean isEmployee;

    // Constructors

    public User() {
        // Default constructor
    }

    public User(String username, String password, String email, String firstName, String lastName,
                String phone, int age, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.address = address;
        this.isEmployee = isEmployee;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public void registerUser() throws IOException, ExecutionException, InterruptedException {
        // Initialize Firestore
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/key.json");
        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        Firestore db = firestoreOptions.getService();

        // Create a new user document in the "users" collection
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        user.put("phone", phone);
        user.put("address", address);
        user.put("age", age);

        ApiFuture<WriteResult> result = db.collection("users").document(username).set(user);

        System.out.println("User registered with ID: " + username);
        System.out.println("Write result: " + result.get().getUpdateTime());
    }

    public void loginUser() throws IOException, ExecutionException, InterruptedException {
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
            // Do something with the user data
            System.out.println("User logged in: " + username);
        } else {
            System.err.println("User not found: " + username);
        }
    }
}
