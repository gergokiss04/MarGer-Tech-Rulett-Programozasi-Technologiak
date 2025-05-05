package org.example.rulettjavafx;

import java.util.concurrent.ThreadLocalRandom;

public class GameController {
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
    colors guessedColor;

    public Integer[] getRouletteNumbers() {
        return rouletteNumbers;
    }

    public int getPlacedBet() {
        return placedBet;
    }

    public int getGuessedNumber() {
        return guessedNumber;
    }

    public colors getGuessedColor() {
        return guessedColor;
    }


}
