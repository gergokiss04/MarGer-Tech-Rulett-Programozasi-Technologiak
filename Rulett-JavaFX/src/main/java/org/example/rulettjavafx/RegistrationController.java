package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void onRegisterButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Hiba", "A mezők nem lehetnek üresek!", Alert.AlertType.WARNING);
            return;
        }

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rulett", "root", "");

            String sql = "INSERT INTO User (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password); //Még nincs Hashelve a jelszó!

            stmt.executeUpdate();
            conn.close();

            showAlert("Siker", "Sikeres regisztráció!", Alert.AlertType.INFORMATION);
            usernameField.clear();
            passwordField.clear();

        } catch (Exception e) {
            showAlert("Hiba", "Nem sikerült regisztrálni: " + e.getMessage(), Alert.AlertType.ERROR);
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
