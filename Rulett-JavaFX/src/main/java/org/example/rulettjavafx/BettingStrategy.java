package org.example.rulettjavafx;

public interface BettingStrategy {
    double calculateWinnings(int betAmount, boolean correctNumber, boolean correctColor);

    public class NumberBettingStrategy implements BettingStrategy {
        @Override
        public double calculateWinnings(int betAmount, boolean correctNumber, boolean correctColor) {
            return correctNumber ? betAmount * 2.25 : -betAmount;
        }
    }

    public class ColorBettingStrategy implements BettingStrategy {
        @Override
        public double calculateWinnings(int betAmount, boolean correctNumber, boolean correctColor) {
            return correctColor ? betAmount * 1.5 : -betAmount;
        }
    }

    public class CombinedBettingStrategy implements BettingStrategy {
        @Override
        public double calculateWinnings(int betAmount, boolean correctNumber, boolean correctColor) {
            return (correctNumber && correctColor) ? betAmount * 2.75 : -betAmount;
        }
    }
}




