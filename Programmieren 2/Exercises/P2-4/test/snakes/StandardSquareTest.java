package snakes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import snakes.squares.FirstSquare;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StandardSquareTest {
	@Test
	public void testMoveAndLandOnly() {
		Game game = mock(Game.class);
		Square testSquare;
		Square start, stop;

		when(game.isValidPosition(anyInt())).thenReturn(true); //tell the mocked game class what to do if isValidPosition() is called
		testSquare = new StandardSquare(game, 1); //create square on which we want to test moveAndLand()
		start = mock(Square.class); //mock for findSquare()
		stop = mock(Square.class); //mock for landHereOrGoHome()

		when(game.findSquare(1, 2)).thenReturn(start);
		when(start.landHereOrGoHome()).thenReturn(stop);

		Square destination = testSquare.moveAndLand(2);
		assertEquals(stop, destination); //actual test for testSquare
	}
	@Test
	public void TestToString() {
		Game game = mock(Game.class);
		Player tom = new Player("tom");
		when(game.isValidPosition(anyInt())).thenReturn(true);
		StandardSquare testSquare;

		testSquare = new StandardSquare(game, 1);
		testSquare.enter(tom);

		assertEquals("[1<tom>]", testSquare.toString(), "Square with Tom.");
	}
	@Test
	public void TestToStringEmpty() {
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		StandardSquare testSquare;

		testSquare = new StandardSquare(game, 1);


		assertEquals("[1]", testSquare.toString(), "Square without any Player.");
	}
	@Test
	public void TestisOccupied(){
		Game game = mock(Game.class);
		Player tom = new Player("tom");
		when(game.isValidPosition(anyInt())).thenReturn(true);
		StandardSquare testSquare;

		testSquare = new StandardSquare(game, 1);
		testSquare.enter(tom);

		assertTrue(testSquare.isOccupied(), "The Square should be occupied by Tom.");
	}
	@Test
	public void TestisNotOccupied(){
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		StandardSquare testSquare;

		testSquare = new StandardSquare(game, 1);

		assertFalse(testSquare.isOccupied(), "The Square should not be occupied.");
	}
	@Test
	public void TestLandHereNOTGoHome() {
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		Square testSquare, test;


		testSquare = new StandardSquare(game, 15);
		when(testSquare.isOccupied()).thenReturn(false);

		test = testSquare.landHereOrGoHome();

		assertEquals(testSquare, test, "The Square is not occupied should Land here.");

	}
}
