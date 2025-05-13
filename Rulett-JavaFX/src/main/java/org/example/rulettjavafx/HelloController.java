package org.example.rulettjavafx;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    public void initialize() {
        welcomeText.setText("Köszöntjük a Roulett birodalomban!");
    }

    @FXML
    private void onRegisterButtonClick() {
        try {
            NavigationHelper.openWindow("registration-view.fxml", "Regisztráció");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült megnyitni a regisztrációs ablakot!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onLoginButtonClick() {
        try {
            NavigationHelper.openWindow("login-view.fxml", "Bejelentkezés");
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