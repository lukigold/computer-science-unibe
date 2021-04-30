package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.*;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LadderSquareTest {
    @Test
    public void TestToString() {
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        StandardSquare testSquare, other;

        testSquare = new LadderSquare(5, game, 1);
        other = new StandardSquare(game, 6);
        when(game.getSquare(anyInt())).thenReturn(other);

        assertEquals("[1->6]", testSquare.toString(), "Ladder Square to 6.");
    }
    @Test
    public void TestLandHereOrGoHome(){
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        Square testSquare, other, test;

        testSquare = new LadderSquare(5, game, 1);
        other = new StandardSquare(game, 6);

        when(game.getSquare(6)).thenReturn(other);
        test = testSquare.landHereOrGoHome();

        assertEquals(other, test);
    }

}
