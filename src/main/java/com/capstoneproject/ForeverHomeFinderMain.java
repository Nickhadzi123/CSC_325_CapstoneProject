package com.example.capstoneproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ForeverHomeFinderMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {//Sets up GUI for window
        FXMLLoader fxmlLoader = new FXMLLoader(ForeverHomeFinderMain.class.getResource("ForeverHomeFinderGUI-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Forever Home Finder");//Sets title for window
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {//Main Method
        launch();
    }
}