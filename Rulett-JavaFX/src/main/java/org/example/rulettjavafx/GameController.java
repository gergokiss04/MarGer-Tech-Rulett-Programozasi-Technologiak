package org.example.rulettjavafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Random;

public class GameController implements Initializable {
    private int userId;
    private int coins = 200;
    private final RouletteGame rouletteGame = new RouletteGame();
    private final DatabaseHelper databaseHelper=new DatabaseHelper();


    @FXML
    private ChoiceBox<String> numberChoiceBox;
    @FXML
    private ChoiceBox<String> colorsChoiceBox;
    @FXML
    private Text coinAmount;
    @FXML
    private Text resultText;

    private final String[] numbers = {"0", "32", "15", "19", "4", "21", "2", "25", "17", "34", "6", "27", "13", "36", "11", "30", "8", "23", "10", "5", "24", "16", "33", "1", "20", "14", "31", "9", "22", "18", "29", "7", "28", "12", "35", "3", "26"};
    private final String[] rouletteColors = {"PIROS", "FEKETE", "ZÖLD"};

    @FXML
    private void handleSpin() {
        String numberValue = numberChoiceBox.getValue();
        String colorValue = colorsChoiceBox.getValue();

        if (numberValue == null || colorValue == null) {
            showAlert("Hiányzó adatok", "Kérlek válassz ki egy számot és egy színt a pörgetéshez!");
            return;
        }

        int betAmount = 10;

        if (coins < betAmount) {
            handleInsufficientCoins();
            return;
        }

        coins -= betAmount;

        int guessedNumber = Integer.parseInt(numberValue);
        String guessedColor = colorValue;

        int drawnIndex = rouletteGame.spinRoulette();
        int drawnNumber = rouletteGame.getDrawnNumber(drawnIndex);
        String drawnColor = rouletteGame.getDrawnColor(drawnIndex);

        boolean correctNumber = guessedNumber == drawnNumber;
        boolean correctColor = guessedColor.equals(drawnColor);

        if (correctNumber && correctColor) {
            rouletteGame.setBettingStrategy(new BettingStrategy.CombinedBettingStrategy());
        } else if (correctNumber) {
            rouletteGame.setBettingStrategy(new BettingStrategy.NumberBettingStrategy());
        } else if (correctColor) {
            rouletteGame.setBettingStrategy(new BettingStrategy.ColorBettingStrategy());
        }

        // Calculate winnings using the selected strategy
        double winnings = rouletteGame.calculateWinnings(betAmount, correctNumber, correctColor);
        coins += winnings;

        if (winnings > 0) {
            resultText.setText("Gratulálunk! Nyertél!\n Kisorsolt szám: " + drawnNumber);
        } else {
            resultText.setText("Sajnálom, nem nyertél.\n Kisorsolt szám: " + drawnNumber);
        }

        databaseHelper.updateUserCoins(userId, coins);
        coinAmount.setText(String.valueOf(coins));
    }

    private void handleInsufficientCoins() {
        Alert gameOverAlert = new Alert(Alert.AlertType.CONFIRMATION);
        gameOverAlert.setTitle("Vége a játéknak");
        gameOverAlert.setHeaderText("Elfogytak az érméid!");
        gameOverAlert.setContentText("Mit szeretnél tenni?");

        ButtonType newGameButton = new ButtonType("Új játék");
        ButtonType logoutButton = new ButtonType("Kijelentkezés");
        ButtonType cancelButton = new ButtonType("Mégse", ButtonBar.ButtonData.CANCEL_CLOSE);

        gameOverAlert.getButtonTypes().setAll(newGameButton, logoutButton, cancelButton);

        Optional<ButtonType> result = gameOverAlert.showAndWait();

        if (result.isPresent() && result.get() == newGameButton) {
            coins = 200;
            coinAmount.setText(String.valueOf(coins));
            resultText.setText("Új játék indult.");
            databaseHelper.updateUserCoins(userId,coins);
        } else if (result.isPresent() && result.get() == logoutButton) {
            Platform.exit();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberChoiceBox.getItems().addAll(numbers);
        colorsChoiceBox.getItems().addAll(rouletteColors);
        coinAmount.setText(String.valueOf(coins));
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("User ID set to: " + userId); // Debugging output
    }
}