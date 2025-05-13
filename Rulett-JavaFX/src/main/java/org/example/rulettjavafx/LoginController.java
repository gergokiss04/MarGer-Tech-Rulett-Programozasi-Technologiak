package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private final UserRepository userRepository = new UserRepository();

    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Hiba", "A mezők nem lehetnek üresek!", Alert.AlertType.WARNING);
            return;
        }

        try {
            int userId = userRepository.getUserIdIfValid(username, password);
            if (userId != -1) {
                showAlert("Siker", "Sikeres bejelentkezés!", Alert.AlertType.INFORMATION);
                openGameView(userId);
            } else {
                showAlert("Hiba", "Hibás felhasználónév vagy jelszó!", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("Hiba", "Hiba történt: " + e.getMessage(), Alert.AlertType.ERROR);
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

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}