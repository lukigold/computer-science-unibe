package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.FirstSquare;
import snakes.squares.LastSquare;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LastSquareTest {
    @Test
    public void TestIsLastSquare(){
        Game game = mock(Game.class);
        Square testSquare, test2;
        when(game.isValidPosition(anyInt())).thenReturn(true);

        testSquare = new LastSquare(game, 3);

        test2 = new StandardSquare(game, 2);

        boolean test = testSquare.isLastSquare();
        boolean test_2 = test2.isLastSquare();

        assertEquals(true, test, "This is a LastSquare so we expect true.");
        assertEquals(false, test_2, "This is not a LastSquare so we expect false.");
    }
}
