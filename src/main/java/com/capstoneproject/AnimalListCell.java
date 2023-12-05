package com.capstoneproject;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AnimalListCell extends ListCell<Animal> {
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
    protected void updateItem(Animal item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
            setText(null);
        } else {
            // Display all information besides the image
            String animalInfo = "Name: " + item.getName() +
                    "\nSpecies: " + item.getSpecies() +
                    "\nAge: " + item.getAge() +
                    "\nDescription: " + item.getDescription();  // Add other properties as needed

            setText(animalInfo);

            // Set the graphic content only if it hasn't been set
            if (getGraphic() == null) {
                setGraphic(content);
            }
        }
    }
}
