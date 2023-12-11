package com.capstoneproject;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class AppointmentDialog extends Dialog<Appointment> {
    private DatePicker datePicker;
    private ComboBox<Integer> hourComboBox;
    private ComboBox<Integer> minuteComboBox;

    public AppointmentDialog() {
        init();
    }

    private void init() {
        setTitle("Schedule Appointment");
        setHeaderText("Choose a date and time for the appointment.");

        ButtonType scheduleButtonType = new ButtonType("Schedule", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(scheduleButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        datePicker = new DatePicker();
        hourComboBox = new ComboBox<>();
        minuteComboBox = new ComboBox<>();

        // Populate hour and minute ComboBoxes
        for (int i = 0; i < 24; i++) {
            hourComboBox.getItems().add(i);
        }
        for (int i = 0; i < 60; i += 15) {
            minuteComboBox.getItems().add(i);
        }

        gridPane.add(new Label("Date:"), 0, 0);
        gridPane.add(datePicker, 1, 0);
        gridPane.add(new Label("Hour:"), 0, 1);
        gridPane.add(hourComboBox, 1, 1);
        gridPane.add(new Label("Minute:"), 0, 2);
        gridPane.add(minuteComboBox, 1, 2);

        getDialogPane().setContent(gridPane);

        setResultConverter(dialogButton -> {
            if (dialogButton == scheduleButtonType) {
                // User clicked Schedule button
                LocalDateTime selectedDateTime = getSelectedDateTime();
                if (selectedDateTime != null) {
                    return new Appointment(selectedDateTime);
                }
            }
            return null;
        });
    }

    private LocalDateTime getSelectedDateTime() {
        LocalDate selectedDate = datePicker.getValue();
        Integer selectedHour = hourComboBox.getValue();
        Integer selectedMinute = minuteComboBox.getValue();

        if (selectedDate != null && selectedHour != null && selectedMinute != null) {
            return LocalDateTime.of(selectedDate, LocalTime.of(selectedHour, selectedMinute));
        }
        return null;
    }
}
