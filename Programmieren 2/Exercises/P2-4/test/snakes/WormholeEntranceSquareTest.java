package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.SkipSquare;
import snakes.squares.Square;
import snakes.squares.StandardSquare;
import snakes.squares.WormholeEntranceSquare;
import snakes.squares.WormholeExitSquare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

public class WormholeEntranceSquareTest {
    @Test
    public void TestSquareLabel() {
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        StandardSquare testSquare;

        testSquare = new WormholeEntranceSquare(game, 1);

        assertEquals("[1 (Entrance)]", testSquare.toString(), "WormholeEntranceSquare on Position 1.");
    }
    @Test
    public void testLandHereOrGoHome() {
        Game game = mock(Game.class);
        List<Square> wormholeExits = new LinkedList<Square>();
        Square testEntrance;
        Square testExit;

        when(game.isValidPosition(anyInt())).thenReturn(true);
        when(game.wormholeExits()).thenReturn(wormholeExits);
        testEntrance = new WormholeEntranceSquare(game, 1);
        testExit = new WormholeExitSquare(game, 2);
        wormholeExits.add(testExit);
        
        Square test = testEntrance.landHereOrGoHome();
        assertEquals(test, testExit); //returned square should be WormholeExit
    }
    
    @Test
    public void noWormholeExits() {
    	Game game = mock(Game.class);
        List<Square> wormholeExits = new LinkedList<Square>(); //Empty wormhole exits
        Square testEntrance;
        Square testExit;

        when(game.isValidPosition(anyInt())).thenReturn(true);
        when(game.wormholeExits()).thenReturn(wormholeExits);
        testEntrance = new WormholeEntranceSquare(game, 1);
        
        Square test = testEntrance.landHereOrGoHome();
        assertEquals(test, testEntrance); //returned Entrance square because there arent any wormhole exits
    }
}
