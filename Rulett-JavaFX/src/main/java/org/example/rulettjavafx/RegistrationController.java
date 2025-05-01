package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private void onRegisterButtonClick() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Regisztrációs logika ide jön
        System.out.println("Regisztráció: " + username + ", " + email);
        // Itt adhatsz hozzá adatbázis-kezelést
    }
}
