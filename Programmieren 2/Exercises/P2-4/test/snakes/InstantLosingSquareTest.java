package snakes;

import org.junit.jupiter.api.Test;

import snakes.squares.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * String and Label method will not be tested here because it is the same as in FirstSquare
 */
public class InstantLosingSquareTest {
	@Test
	public void testLandHereOrGoHome() {
		Player player1 = mock(Player.class);
		Player player2 = mock(Player.class);
		
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		
		Game game = new Game(4, players, 6); //Game cannot be mocked because game.isOver() is important to determine if square works properly
		InstantLosingSquare testSquare = new InstantLosingSquare(game, 2);
		
		assertEquals(0, testSquare.howManyPlayers());
		
		testSquare.landHereOrGoHome(); //player1 steps on square
		
		assertEquals(player2, game.currentPlayer());
		assertEquals(false, game.isOver()); //player 2 can play on
		
		testSquare.landHereOrGoHome(); //player2 steps on square
		
		assertEquals(player2, game.currentPlayer());
		assertEquals(true, game.isOver()); //Both players are on the square => tie
	}
	
	@Test
	public void threePlayers() {
		Player player1 = mock(Player.class);
		Player player2 = mock(Player.class);
		Player player3 = mock(Player.class);
		
		
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		
		Game game = mock(Game.class);
		
        when(game.isValidPosition(anyInt())).thenReturn(true);
		InstantLosingSquare testSquare = new InstantLosingSquare(game, 3);
		Square standard = new StandardSquare(game, 2);
        doAnswer((i) -> {
        	players.remove();
        	return null;
        }).when(game).removePlayerFromGame(any());
        doNothing().when(game).setTie();
        
        doNothing().when(game).setTie();
        when(game.nextPlayer()).thenReturn(players.peek());
		
        testSquare.landHereOrGoHome();//player 1 loses
        assertEquals(2, players.size());
        assertEquals(player2, players.peek());
        
        standard.landHereOrGoHome(); //player 2 gets on standard square
        Player curr = players.remove();
        players.add(curr);
        
        testSquare.landHereOrGoHome();//player 3 loses
        assertEquals(1, players.size());
        assertEquals(player2, players.peek());
        
        testSquare.landHereOrGoHome();//player 2 loses
		assertEquals(0, players.size());
        assertEquals(null, players.peek());
	}
	
	@Test
	public void enter() {
		Game game = mock(Game.class);
		Player player1 = mock(Player.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        InstantLosingSquare testSquare = new InstantLosingSquare(game, 2);
		
		testSquare.enter(player1);
		assertEquals(1, testSquare.howManyPlayers());
		assertEquals(player1, testSquare.getPlayers().get(0));
	}
	@Test
	public void TestToString() {
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		StandardSquare testSquare, other;

		testSquare = new InstantLosingSquare(game, 1);
		when(game.getSquare(anyInt())).thenReturn(testSquare);

		assertEquals("[1(Losing)]", testSquare.toString(), "Losing square on 1.");
	}
}
