package com.capstoneproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import java.io.FileInputStream;
import java.io.IOException;

    public class DBConnection {
        private static Firestore firestore;

        public static void initialize() throws IOException {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/key.json");

            FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            firestore = firestoreOptions.getService();
        }

        public static Firestore getFirestore() {
            if (firestore == null) {
                throw new IllegalStateException("Firestore is not initialized. Call initialize() first.");
            }
            return firestore;
        }
    }




