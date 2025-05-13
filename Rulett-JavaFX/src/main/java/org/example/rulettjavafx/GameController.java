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
    private final Random random = new Random();

    Integer[] rouletteNumbers = new Integer[]{0, 32, 15, 19, 4, 21,
                                                2, 25, 17, 34, 6, 27,
                                                13, 36, 11, 30, 8, 23,
                                                10, 5, 24, 16, 33, 1,
                                                20, 14, 31, 9, 22, 18,
                                                29, 7, 28, 12, 35, 3, 26};

    String[] rouletteColors = new String[]{"PIROS", "FEKETE", "ZÖLD"};

    int placedBet;
    final double correctNumberBet = 2.25;
    final double correctColorBet = 1.5;
    final double correctNumberAndColorBet = 2.75;

    @FXML
    private ChoiceBox<String> numberChoiceBox;
    private String[] numbers = {"0", "32", "15", "19", "4", "21",
            "2", "25", "17", "34", "6", "27",
            "13", "36", "11", "30", "8", "23",
            "10", "5", "24", "16", "33", "1",
            "20", "14", "31", "9", "22", "18",
            "29", "7", "28", "12", "35", "3", "26"};



    @FXML
    private ChoiceBox<String> colorsChoiceBox;

    @FXML
    private Text coinAmount;

    @FXML
    private Text resultText;

    @FXML
    private void handleSpin(){

        String numberValue = numberChoiceBox.getValue();
        String colorValue = colorsChoiceBox.getValue();

        if (numberValue == null || colorValue == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hiányzó adatok");
            alert.setHeaderText(null);
            alert.setContentText("Kérlek válassz ki egy számot és egy színt a pörgetéshez!");
            alert.showAndWait();
            return;
        }

        int betAmount = 10;

        if (coins < betAmount) {
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
                // Érmék visszaállítása és UI frissítése
                coins = 200;
                coinAmount.setText(String.valueOf(coins));
                resultText.setText("Új játék indult.");
                DatabaseHelper.updateUserCoins(userId, coins);
            } else if (result.isPresent() && result.get() == logoutButton) {
                // Alkalmazás bezárása vagy kijelentkezés logika
                Platform.exit(); // Vagy navigálj vissza a bejelentkező képernyőre
            }

            return;
        }


        coins -= betAmount;

        Integer guessedNumber = Integer.valueOf(numberChoiceBox.getValue());
        String guessedColor = colorsChoiceBox.getValue();

        int drawnIndex = random.nextInt(rouletteNumbers.length);
        int drawnNumber = rouletteNumbers[drawnIndex];
        String drawnColor;

        if (drawnIndex == 0){
            drawnColor = rouletteColors[2];
        } else if ((drawnIndex % 2 == 0)) {
            drawnColor = rouletteColors[0];
        }else {
            drawnColor = rouletteColors[1];
        }

        if (guessedNumber.equals(drawnNumber) && guessedColor.equals(drawnColor)) {
            resultText.setText("Gratulálunk! Nyertél!\n Kisorsolt szám: " + drawnNumber);
            coins += (int)(betAmount * correctNumberAndColorBet);
        } else if (guessedColor.equals(drawnColor)) {
            resultText.setText("Gratulálunk! Nyertél!\n Kisorsolt szám: " + drawnNumber);
            coins += (int)(betAmount * correctColorBet);
        } else if (guessedNumber.equals(drawnNumber)) {
            resultText.setText("Gratulálunk! Nyertél!\n Kisorsolt szám: " + drawnNumber);
            coins += (int)(betAmount * correctNumberBet);
        } else {
            resultText.setText("Sajnálom, nem nyertél.\n Kisorsolt szám: " + drawnNumber);
        }
        DatabaseHelper.updateUserCoins(userId, coins);
        coinAmount.setText(String.valueOf(coins));
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

    private void ensureGameRowExists(int userId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rulett", "root", "")) {

            // Check if the user_id already exists in the game table
            String checkSql = "SELECT * FROM game WHERE user_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                // If no row exists, insert a new row
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
}
