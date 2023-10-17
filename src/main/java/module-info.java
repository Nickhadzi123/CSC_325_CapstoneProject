module com.capstoneproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.capstoneproject to javafx.fxml;
    exports com.capstoneproject;
}