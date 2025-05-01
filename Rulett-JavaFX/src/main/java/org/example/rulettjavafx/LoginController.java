package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Bejelentkezési logika ide jön
        System.out.println("Bejelentkezés: " + username);
        // Itt adhatsz hozzá adatbázis-kezelést
    }
}
