package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SkipSquareTest {
    @Test
    public void TestLandHereOrGoHome(){
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Queue<Player> players = new LinkedList<Player>();
        players.add(player1);
        players.add(player2);
        
    	Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        Square testSquare = new SkipSquare(game, 2);
        Square firstSquare = new StandardSquare(game, 1);

        Square test;

        when(game.firstSquare()).thenReturn(firstSquare);
        doAnswer((i) -> { //remove and add player, simulates skipNextPlayer
        	Player curr = players.remove();
        	players.add(curr);
        	return null;
        }).when(game).skipNextPlayer();
        
        Player curr = players.remove(); //Simulate game, take current player off queue
        players.add(curr); //add player to end of queue
        test = testSquare.landHereOrGoHome();

        assertEquals(testSquare, test, "We expect that LandHereOrGoHome returns the current square.");
        assertEquals(players.peek(), player1, "We expect that player 2 is skipped.");

    }
    
    @Test
    public void landHereOrGoHomeOccupied() {
    	Game game = mock(Game.class);
    	Player player1 = mock(Player.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        Square testSquare = new SkipSquare(game, 2);
        Square firstSquare = mock(Square.class);
        
        when(game.firstSquare()).thenReturn(firstSquare);
        testSquare.enter(player1);
        Square test = testSquare.landHereOrGoHome();
        
        assertEquals(firstSquare, test, "We expect that first square is returned.");
        
    }
    
    @Test
    public void TestSquareLabel() {
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        StandardSquare testSquare;

        testSquare = new SkipSquare(game, 1);

        assertEquals("[1 (Skip)]", testSquare.toString(), "SkipSquare on Position 1.");
    }
}
