package org.example.rulettjavafx;

import java.util.Random;

public class RouletteGame {
    private BettingStrategy bettingStrategy;

    public void setBettingStrategy(BettingStrategy bettingStrategy) {
        this.bettingStrategy = bettingStrategy;
    }

    public double calculateWinnings(int betAmount, boolean correctNumber, boolean correctColor) {
        if (bettingStrategy == null) {
            throw new IllegalStateException("Betting strategy is not set!");
        }
        return bettingStrategy.calculateWinnings(betAmount, correctNumber, correctColor);
    }

    public int spinRoulette() {
        Random random = new Random();
        return random.nextInt(37); // Simulates spinning the roulette wheel
    }

    public int getDrawnNumber(int index) {
        Integer[] rouletteNumbers = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
        return rouletteNumbers[index];
    }

    public String getDrawnColor(int index) {
        String[] rouletteColors = {"PIROS", "FEKETE", "ZÖLD"};
        if (index == 0) {
            return rouletteColors[2];
        } else if (index % 2 != 0) {
            return rouletteColors[0];
        } else {
            return rouletteColors[1];
        }
    }
}