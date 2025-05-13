package org.example.rulettjavafx;

public class GameFacade {
    private final DatabaseHelper databaseHelper;
    private final RouletteGame rouletteGame;

    public GameFacade() {
        this.databaseHelper = new DatabaseHelper();
        this.rouletteGame = new RouletteGame();
    }

    public void updateUserCoins(int userId, int coins) {
        databaseHelper.updateUserCoins(userId, coins);
    }

    public int getUserCoins(int userId) {
        return databaseHelper.getUserCoins(userId);
    }

    public int spinRoulette() {
        return rouletteGame.spinRoulette();
    }

    public int getDrawnNumber(int index) {
        return rouletteGame.getDrawnNumber(index);
    }

    public String getDrawnColor(int index) {
        return rouletteGame.getDrawnColor(index);
    }

    public double calculateWinnings(int betAmount, boolean correctNumber, boolean correctColor) {
        return rouletteGame.calculateWinnings(betAmount, correctNumber, correctColor);
    }

}
