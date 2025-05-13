package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private final UserRepository userRepository = new UserRepository();

    @FXML
    private void onRegisterButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Hiba", "A mezők nem lehetnek üresek!", Alert.AlertType.WARNING);
            return;
        }

        try {
            if (userRepository.usernameExists(username)) {
                showAlert("Hiba", "A felhasználónév már létezik!", Alert.AlertType.WARNING);
                return;
            }

            userRepository.insertUser(username, password);
            showAlert("Siker", "Sikeres regisztráció!", Alert.AlertType.INFORMATION);

            usernameField.clear();
            passwordField.clear();

        } catch (Exception e) {
            showAlert("Hiba", "Nem sikerült regisztrálni: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}