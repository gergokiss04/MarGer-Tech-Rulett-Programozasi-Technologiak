package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Hiba", "A mezők nem lehetnek üresek!", Alert.AlertType.WARNING);
            return;
        }

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rulett", "root", "");

            String sql = "SELECT password FROM User WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {
                    showAlert("Siker", "Sikeres bejelentkezés!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Hiba", "Hibás felhasználónév vagy jelszó!", Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Hiba", "Felhasználó nem található!", Alert.AlertType.ERROR);
            }

            conn.close();

        } catch (Exception e) {
            showAlert("Hiba", "Hiba történt: " + e.getMessage(), Alert.AlertType.ERROR);
            //e.printStackTrace();
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
