package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class GameController implements Initializable {
    Integer[] rouletteNumbers = new Integer[]{0, 32, 15, 19, 4, 21,
                                                2, 25, 17, 34, 6, 27,
                                                13, 36, 11, 30, 8, 23,
                                                10, 5, 24, 16, 33, 1,
                                                20, 14, 31, 9, 22, 18,
                                                29, 7, 28, 12, 35, 3, 26};

    int placedBet;
    final double correctNumberBet = 2.25;
    final double correctColorBet = 1.5;
    final double correctNumberAndColorBet = 2.75;

    int randomNumber = rouletteNumbers[ThreadLocalRandom.current().nextInt(rouletteNumbers.length)];
    int guessedNumber = 0; //Változni fog, csak a logika működésének ellenőrzése miatt van jelenleg így

    public Integer[] getRouletteNumbers() {
        return rouletteNumbers;
    }

    public int getPlacedBet() {
        return placedBet;
    }

    public int getGuessedNumber() {
        return guessedNumber;
    }



    @FXML
    private ChoiceBox<String> numberChoiceBox;
    private String[] numbers = {"0", "32", "15", "19", "4", "21",
            "2", "25", "17", "34", "6", "27",
            "13", "36", "11", "30", "8", "23",
            "10", "5", "24", "16", "33", "1",
            "20", "14", "31", "9", "22", "18",
            "29", "7", "28", "12", "35", "3", "26"};

    @FXML
    private ChoiceBox<Colors> colorsChoiceBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberChoiceBox.getItems().addAll(numbers);
        colorsChoiceBox.getItems().addAll(Colors.values());
    }
}
