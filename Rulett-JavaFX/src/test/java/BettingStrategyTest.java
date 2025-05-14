import org.example.rulettjavafx.BettingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BettingStrategyTest {
    @Test
    void testNumberBettingStrategy() {
        BettingStrategy strategy = new BettingStrategy.NumberBettingStrategy();

        double winnings = strategy.calculateWinnings(100, true, false);
        assertEquals(225.0, winnings, "Winnings should be 225.0 for a correct number bet.");

        winnings = strategy.calculateWinnings(100, false, false);
        assertEquals(-100.0, winnings, "Winnings should be -100.0 for an incorrect number bet.");
    }

    @Test
    void testColorBettingStrategy() {
        BettingStrategy strategy = new BettingStrategy.ColorBettingStrategy();

        double winnings = strategy.calculateWinnings(100, false, true);
        assertEquals(150.0, winnings, "Winnings should be 150.0 for a correct color bet.");

        winnings = strategy.calculateWinnings(100, false, false);
        assertEquals(-100.0, winnings, "Winnings should be -100.0 for an incorrect color bet.");
    }
}
