package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


import java.util.LinkedList;
import java.util.Queue;

public class SwapSquareTest {
    @Test
    public void TestSquareLabel() {
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        StandardSquare testSquare;

        testSquare = new SwapSquare(game, 1);

        assertEquals("[1 (Swap)]", testSquare.toString(), "SkipSquare on Position 1.");
    }
    @Test
    public void TestLandHereNotGoHome() {
    	Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        
        Queue<Player> players = new LinkedList<Player>();
        players.add(player1);
        players.add(player2);
        
    	Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        Square testSquare = new SwapSquare(game, 2);
        Square firstSquare = new StandardSquare(game, 1);
        
        when(game.firstSquare()).thenReturn(firstSquare);
        when(player1.position()).thenReturn(2);
        when(player2.position()).thenReturn(1);
        when(player1.swap(firstSquare)).thenReturn(testSquare);
        when(player2.swap(testSquare)).thenReturn(firstSquare);
        when(game.nextPlayer()).thenReturn(player2);
        when(game.getSquare(2)).thenReturn(testSquare);
        when(game.getSquare(1)).thenReturn(firstSquare);
        
        Square test = testSquare.landHereOrGoHome(); //player 1 lands on square

        assertEquals(1, test.position(), "Square should be first square");
    }
}
