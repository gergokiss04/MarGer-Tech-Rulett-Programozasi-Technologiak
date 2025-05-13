package org.example.rulettjavafx;

import java.util.Random;

public class RouletteGame {
    private final Random random = new Random();
    private final Integer[] rouletteNumbers = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
    private final String[] rouletteColors = {"PIROS", "FEKETE", "ZÖLD"};

    public int spinRoulette() {
        return random.nextInt(rouletteNumbers.length);
    }

    public int getDrawnNumber(int index) {
        return rouletteNumbers[index];
    }

    public String getDrawnColor(int index) {
        if (index == 0) {
            return rouletteColors[2];
        } else if (index % 2 != 0) {
            return rouletteColors[0];
        } else {
            return rouletteColors[1];
        }
    }

    public double calculateWinnings(int betAmount, boolean correctNumber, boolean correctColor) {
        if (correctNumber && correctColor) {
            return betAmount * 2.75;
        } else if (correctNumber) {
            return betAmount * 2.25;
        } else if (correctColor) {
            return betAmount * 1.5;
        }
        return -betAmount; // Loss
    }
}