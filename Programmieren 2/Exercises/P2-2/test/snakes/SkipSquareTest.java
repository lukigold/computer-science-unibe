package snakes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class SkipSquareTest extends SquareTest {
	@Override
	@BeforeEach
	public void newGame() {
		initializeGame(15);
	}

	@Test
	public void moveToSkipSquare() {
		game.setSquare(7, new SkipSquare(game, 7));
		game.movePlayer(6); // moves Jack
		assertEquals(7, jack.position());
		assertEquals(1, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(5); // moves Joe
		assertEquals(7, jack.position());
		assertEquals(1, jill.position());
		assertEquals(6, joe.position());
		game.movePlayer(2); // Jack
		assertEquals(9, jack.position());
		assertEquals(1, jill.position());
		assertEquals(6,joe.position());
	}
	
	@Test
	public void AllSkipSquares() {
		for(int i = 2; i < 15; i++) {
			game.setSquare(i, new SkipSquare(game, i));
		}
		game.movePlayer(1); //moves Jack
		assertEquals(2, jack.position());
		assertEquals(1, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(3); //moves Joe
		assertEquals(2, jack.position());
		assertEquals(1, jill.position());
		assertEquals(4, joe.position());
		game.movePlayer(6); //moves Jill
		assertEquals(2, jack.position());
		assertEquals(7, jill.position());
		assertEquals(4,joe.position());
	}
	
	@Test
	public void AllEvenSkipSquare() {
		for(int i = 2; i < 15; i+=2) {
			game.setSquare(i, new SkipSquare(game, i));
		}
		game.movePlayer(3); //moves Jack
		assertEquals(4, jack.position());
		assertEquals(1, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(3); //moves Joe
		assertEquals(4, jack.position());
		assertEquals(1, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(3); //moves Jill
		assertEquals(7, jack.position());
		assertEquals(1, jill.position());
		assertEquals(1, joe.position());
	}

}
