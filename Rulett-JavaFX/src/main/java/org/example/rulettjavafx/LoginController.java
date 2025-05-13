package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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

            String sql = "SELECT id, password FROM User WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {
                    int userId = rs.getInt("id");
                    showAlert("Siker", "Sikeres bejelentkezés!", Alert.AlertType.INFORMATION);
                    openGameView(userId);
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
    private void openGameView(int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            Scene gameViewScene = new Scene(loader.load());
            GameController controller = loader.getController();
            controller.setUserId(userId);

            Stage gameViewStage = new Stage();
            gameViewStage.setTitle("Játék");
            gameViewStage.setScene(gameViewScene);
            gameViewStage.show();

            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            showAlert("Hiba", "Nem sikerült megnyitni a játékot: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    private void ensureGameRowExists(int userId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rulett", "root", "")) {


            String checkSql = "SELECT * FROM game WHERE user_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {

                String insertSql = "INSERT INTO game (user_id, coin_value, chosen_color, result_color, won) " +
                        "VALUES (?, 200, 'white', 'white', 0)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, userId);
                insertStmt.executeUpdate();
                System.out.println("Inserted new row for user_id: " + userId);
            } else {
                System.out.println("Row already exists for user_id: " + userId);
            }

        } catch (Exception e) {
            System.out.println("Error ensuring game row exists: " + e.getMessage());
            e.printStackTrace();
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
