module com.capstoneproject {
    opens com.capstoneproject to google.cloud.firestore, javafx.fxml;
    requires javafx.controls;
    requires javafx.fxml;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires java.logging;
    requires com.google.api.apicommon;
    requires com.google.auth;
    requires google.cloud.core;


    exports com.capstoneproject;
}