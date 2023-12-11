package com.capstoneproject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.util.Optional;

public class AnimalListCell extends ListCell<Animal> {
    private Button scheduleButton;
    private HBox content;
    private ImageView imageView;
    private Label nameLabel;
    private Label speciesLabel;
    private Label ageLabel;

    public AnimalListCell() {
        content = new HBox();
        imageView = new ImageView();
        nameLabel = new Label();
        speciesLabel = new Label();
        ageLabel = new Label();

        content.getChildren().addAll(imageView, nameLabel, speciesLabel, ageLabel);
        content.setSpacing(10);

        setGraphic(content);
    }

    @Override
    protected void updateItem(Animal animal, boolean empty) {
        super.updateItem(animal, empty);

        if (empty || animal == null) {
            setGraphic(null);
            setText(null);
        } else {
            // Display all information besides the image
            String animalInfo = "Name: " + animal.getName() +
                    "\nSpecies: " + animal.getSpecies() +
                    "\nAge: " + animal.getAge() +
                    "\nDescription: " + animal.getDescription();  // Add other properties as needed
            setText(animalInfo);
            setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    showAppointmentDialog(animal);
                }
            });

            // Set the graphic content only if it hasn't been set
            if (getGraphic() == null) {
                setGraphic(content);
            }
        }
    }
    private void showAppointmentDialog(Animal animal) {
        AppointmentDialog appointmentDialog = new AppointmentDialog();
        Optional<Appointment> result = appointmentDialog.showAndWait();

        result.ifPresent(appointmentDetails -> {
            // Process the appointment details (e.g., save to the database)
            LocalDateTime dateTime = appointmentDetails.getDateTime();
            // Add your logic here...
            System.out.println("Scheduled appointment for " + animal.getName() + " at " + dateTime);
        });
    }
}
