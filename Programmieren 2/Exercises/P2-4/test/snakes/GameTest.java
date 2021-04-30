package snakes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snakes.squares.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

public class GameTest {

	@Test
	public void testPlayMockito() throws GameNotOverException {
		Player player1 = mock(Player.class);
		Player player2 = mock(Player.class);
		IDie die = mock(IDie.class);

		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);

		Game game = new Game(10, players, 6);
		
		when(die.roll()).thenReturn(4);
		doNothing().when(player1).moveForward(anyInt());
		doNothing().when(player2).moveForward(anyInt());
		when(player1.wins()).thenReturn(false);
		when(player2.wins()).thenReturn(true);
		
		game.play(die);
		
		assertEquals(4, die.roll());
		assertEquals(true, game.isOver());
		assertEquals(false, game.notOver());
		assertEquals(player1, game.currentPlayer());
		assertEquals(player2, game.winner());
	}
	
	@Test
	public void testPlayMockClass() throws GameNotOverException {
		Player player1 = mock(Player.class);
		Player player2 = mock(Player.class);
		IDie die = new MockDie();

		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		
		Game game = new Game(10, players, 6);
		
		doNothing().when(player1).moveForward(anyInt());
		doNothing().when(player2).moveForward(anyInt());
		when(player1.wins()).thenReturn(false);
		when(player2.wins()).thenReturn(true);
		
		game.play(die);
		
		assertEquals(4, die.roll());
		assertEquals(true, game.isOver());
		assertEquals(false, game.notOver());
		assertEquals(player1, game.currentPlayer());
		assertEquals(player2, game.winner());
	}
	@Test
	public void TestToString() {
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		String test = game.toString();
		assertEquals("[1<Kevin><Lukas>][2][3][4][5][6][7][8][9][10]", test);

		game.play(die);
		test = game.toString();
		assertEquals("[1][2][3][4][5][6][7][8<Lukas>][9][10<Kevin>]", test);
	}
	@Test
	public void TestMovePlayer(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertEquals(1, Kevin.position());
		assertEquals(1, Lukas.position());

		game.movePlayer(3);
		game.movePlayer(6);

		assertEquals(4, Kevin.position());
		assertEquals(7, Lukas.position());
	}
	@Test
	public void TestCurrentPlayer(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertEquals(Kevin, game.currentPlayer());
		game.movePlayer(5);
		assertEquals(Lukas, game.currentPlayer());
	}
	@Test
	public void TestNextPlayer(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertEquals(Lukas, game.nextPlayer(), "The next player should be Lukas.");
		game.movePlayer(5);
		assertEquals(Kevin, game.nextPlayer(), "The next player should be Kevin since its now Lukas turn.");
	}
	@Test
	public void TestSkipNextPlayer(){

		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");
		Player Thomas = new Player("Thomas");

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);
		players.add(Thomas);

		Game game = new Game(10, players, 6);

		game.skipNextPlayer();
		game.movePlayer(2);
		assertEquals(3, Lukas.position());
		game.movePlayer(1);
		assertEquals(2, Thomas.position());
		game.skipNextPlayer();
		game.movePlayer(4);
		assertEquals(7, Lukas.position());
	}
	@Test
	public void TestRemovePlayerFromGame(){
		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");
		Player Thomas = new Player("Thomas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);
		players.add(Thomas);

		Game game = new Game(10, players, 6);

		assertEquals("[1<Kevin><Lukas><Thomas>][2][3][4][5][6][7][8][9][10]", game.toString());
		game.removePlayerFromGame(Kevin);
		game.currentPlayer();
		assertEquals("[1<Kevin><Lukas><Thomas>][2][3][4][5][6][7][8][9][10]", game.toString());
		game.play(die);
		assertEquals("[1<Kevin>][2][3][4][5][6][7][8<Thomas>][9][10<Lukas>]", game.toString());
	}
	@Test
	public void TestIsValidPosition(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(6, players, 6);

		assertTrue(game.isValidPosition(1), "Testing is valid position 1.");
		assertTrue(game.isValidPosition(2), "Testing is valid position 2.");
		assertTrue(game.isValidPosition(3), "Testing is valid position 3.");
		assertTrue(game.isValidPosition(4), "Testing is valid position 4.");
		assertTrue(game.isValidPosition(5),"Testing is valid position 5.");
		assertTrue(game.isValidPosition(6),"Testing is valid position 6.");
		assertFalse(game.isValidPosition(10),"Testing is valid position 10.");
		assertFalse(game.isValidPosition(-1),"Testing is valid position -1.");
		assertFalse(game.isValidPosition(-0),"Testing is valid position -0.");
		assertFalse(game.isValidPosition(0),"Testing is valid position 0.");
	}
	@Test
	public void TestFirstSquare(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");
		Player Thomas = new Player("Thomas");

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);
		players.add(Thomas);

		Game game = new Game(4, players, 1);

		assertTrue(game.firstSquare().isFirstSquare());
	}
	@Test
	public void TestOver(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(3);

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertFalse(game.isOver());
		assertTrue(game.notOver());
		game.play(die);
		assertTrue(game.isOver());
	}
	@Test
	public void TestSetSquare(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		game.setSquare(5, new LadderSquare(4, game, 5));
		assertEquals("[1<Kevin><Lukas>][2][3][4][5->9][6][7][8][9][10]", game.toString());

		game.setSquare(7, new WormholeExitSquare(game, 7));
		assertEquals("[1<Kevin><Lukas>][2][3][4][5->9][6][7 (Exit)][8][9][10]", game.toString());

		game.setSquare(8, new WormholeEntranceSquare(game, 8));
		assertEquals("[1<Kevin><Lukas>][2][3][4][5->9][6][7 (Exit)][8 (Entrance)][9][10]", game.toString());
	}
	@Test
	public void TestWinner(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		Game game = new Game(10, players, 6);

		assertThrows(GameNotOverException.class, () -> game.winner());
		game.play(die);
		assertDoesNotThrow(() -> game.winner());

		try { assertEquals(Kevin, game.winner()); } catch (GameNotOverException exception){ }
	}
	@Test
	public void TestSetSquareToLadder(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertEquals("[1<Kevin><Lukas>][2][3][4][5][6][7][8][9][10]", game.toString());
		game.setSquareToLadder(2,5);
		assertEquals("[1<Kevin><Lukas>][2->7][3][4][5][6][7][8][9][10]", game.toString());
	}
	@Test
	public void TestSetSquareToSnake(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertEquals("[1<Kevin><Lukas>][2][3][4][5][6][7][8][9][10]", game.toString());
		game.setSquareToSnake(7,-5);
		assertEquals("[1<Kevin><Lukas>][2][3][4][5][6][2<-7][8][9][10]", game.toString());
	}
	@Test
	public void TestFindSquare(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertEquals(1, game.findSquare(1, 0).position(), "findsquare(1, 0");
		assertEquals(3, game.findSquare(3, 0).position(), "findsquare(3, 0");
		assertEquals(10, game.findSquare(1, 9).position(), "findsquare(1, 9");
		assertEquals(5, game.findSquare(10, 5).position(), "findsquare(10, 5");

	}
	@Test
	public void TestWormholeExits() {
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);

		assertEquals("[]", game.wormholeExits().toString());
		game.setSquare(2, new WormholeExitSquare(game, 2));
		assertEquals("[[2 (Exit)]]", game.wormholeExits().toString());
		game.setSquare(5, new WormholeExitSquare(game, 5));
		assertEquals("[[2 (Exit)], [5 (Exit)]]", game.wormholeExits().toString());
	}
	@Test
	public void TestSetTie(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);
		assertThrows(GameNotOverException.class, () -> game.winner());
		game.setTie();
		assertDoesNotThrow(() -> game.winner());
		try { assertEquals("Nobody", game.winner().toString()); } catch (GameNotOverException exception){ }
	}
	@Test
	public void TestGetSquare(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(10, players, 6);
		Square test = game.getSquare(2);
		Square regular = new StandardSquare(game, 2);
		assertEquals(regular.toString(), test.toString());
	}
	@Test
	public void TestPlay(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(15, players, 6);

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		assertEquals(1, Kevin.position());
		assertEquals(1, Lukas.position());

		game.play(die);

		assertEquals(15, Kevin.position());
		assertEquals(13, Lukas.position());

		assertFalse(game.notOver());

		assertTrue(game.isOver());
	}
	@Test
	public void TestMain(){
		Player Kevin = new Player("Kevin");
		Player Lukas = new Player("Lukas");

		Queue<Player> players = new LinkedList<Player>();
		players.add(Kevin);
		players.add(Lukas);

		Game game = new Game(15, players, 6);

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		game.main(null);
	}

}
