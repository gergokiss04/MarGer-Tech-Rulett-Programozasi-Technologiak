import org.example.rulettjavafx.RouletteGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouletteGameTest {

    private RouletteGame game;

    @BeforeEach
    void setUp() {
        game = new RouletteGame();
    }

    @Test
    void testGetDrawnNumberReturnsCorrectValue() {
        assertEquals(32, game.getDrawnNumber(1));
        assertEquals(0, game.getDrawnNumber(0));
        assertEquals(36, game.getDrawnNumber(13));
    }

    @Test
    void testGetDrawnColor() {
        assertEquals("ZÖLD", game.getDrawnColor(0));
        assertEquals("PIROS", game.getDrawnColor(1));
        assertEquals("FEKETE", game.getDrawnColor(2));
    }

    @Test
    void testSpinRouletteInBounds() {
        for (int i = 0; i < 100; i++) {
            int number = game.spinRoulette();
            assertTrue(number >= 0 && number <= 36);
        }
    }


}
