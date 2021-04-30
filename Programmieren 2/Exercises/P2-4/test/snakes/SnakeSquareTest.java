package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SnakeSquareTest {
    @Test
    public void TestToString() {
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        StandardSquare testSquare, other;

        testSquare = new SnakeSquare(-5, game, 6);
        other = new StandardSquare(game, 1);
        when(game.getSquare(anyInt())).thenReturn(other);

        assertEquals("[1<-6]", testSquare.toString(), "Snake Square from 6 to 1.");
    }
}
