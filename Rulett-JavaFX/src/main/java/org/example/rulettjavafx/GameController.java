package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

public class GameController implements Initializable {
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

    private final Random random = new Random();

    @FXML
    private ChoiceBox<String> colorsChoiceBox;

    @FXML
    private Text coinAmount;

    @FXML
    private Text resultText;


    private void handleSpin(){
        Integer guessedNumber = Integer.valueOf(numberChoiceBox.getValue());
        String guessedColor = colorsChoiceBox.getValue();
        int drawnIndex = random.nextInt(0, 39);
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
            resultText.setText("Gratulálunk! Nyertél! Kisorsolt szám: " + drawnNumber);
        } else if (guessedColor.equals(drawnColor)) {
            resultText.setText("Gratulálunk! Nyertél! Kisorsolt szám: " + drawnNumber);
        } else if (guessedNumber.equals(drawnNumber)) {
            resultText.setText("Gratulálunk! Nyertél! Kisorsolt szám: " + drawnNumber);
        } else {
            resultText.setText("Sajnálom, nem nyertél. Kisorsolt szám: " + drawnNumber);
        }
    }

    private int coins = 200;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberChoiceBox.getItems().addAll(numbers);
        colorsChoiceBox.getItems().addAll(rouletteColors);
        coinAmount.setText(String.valueOf(coins));
    }
}
