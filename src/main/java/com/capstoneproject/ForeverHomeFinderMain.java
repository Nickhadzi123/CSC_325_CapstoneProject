package com.capstoneproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ForeverHomeFinderMain extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginScene();
    }

    private void showLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            LoginController loginController = loader.getController();
            loginController.setMainApplication(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegistrationScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("User Registration");
            primaryStage.setScene(new Scene(root));

            RegistrationController registrationController = loader.getController();
            registrationController.setStage(primaryStage);
            registrationController.setMainApplication(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showEmployeeDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("employeeDashboard.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Employee Dashboard");
            primaryStage.setScene(new Scene(root));

            EmployeeDashboardController employeeDashboardController = loader.getController();
            employeeDashboardController.initialize(user); // Initialize employee data
            employeeDashboardController.loadAnimals();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showMainApplication(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Animal Dashboard");
            primaryStage.setScene(new Scene(root));

            DashboardController dashboardController = loader.getController();
            dashboardController.initialize(user); // Initialize animal list

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}