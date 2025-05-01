package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;


    public void initialize() {
        welcomeText.setText("Köszöntjük a Roulett birodalomban!");
    }


    // Regisztrációs gomb kattintás eseménykezelője
    @FXML
    private void onRegisterButtonClick() {
        try {
            // Új ablak (Stage) létrehozása
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Regisztráció");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült megnyitni a regisztrációs ablakot!", Alert.AlertType.ERROR);
        }
    }

    // Bejelentkezési gomb kattintás eseménykezelője
    @FXML
    private void onLoginButtonClick() {
        try {
            // Új ablak (Stage) létrehozása
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Bejelentkezés");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült megnyitni a bejelentkezési ablakot!", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}