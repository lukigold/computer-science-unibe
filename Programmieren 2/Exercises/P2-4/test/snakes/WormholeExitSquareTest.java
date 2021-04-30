package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.Square;
import snakes.squares.StandardSquare;
import snakes.squares.WormholeExitSquare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WormholeExitSquareTest {
    @Test
    public void TestToString(){
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        Square testSquare;

        testSquare = new WormholeExitSquare(game, 1);

        assertEquals("[1 (Exit)]", testSquare.toString(), "Testing the WormholeExitSquare toString method with the Label.");

    }
    @Test
    public void TestIsWormholeExit(){
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        Square testSquare;

        testSquare = new WormholeExitSquare(game, 1);

        assertTrue(testSquare.isWormholeExit(), "Tests the isWormholeSquare method of WormholeExit Square it should return True");
    }
}
