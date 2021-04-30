package snakes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class SwapSquareTest extends SquareTest {
	@Override
	@BeforeEach
	public void newGame() {
		initializeGame(15);
	}

	@Test
	public void moveToSwapSquare() {
		game.setSquare(2, new SwapSquare(game, 2));
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(1); //moves Jack
		assertEquals(1, jack.position());
		assertEquals(2, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(2); //moves Jill
		assertEquals(1, jack.position());
		assertEquals(4, jill.position());
		assertEquals(1, joe.position());
	}
	
	@Test
	public void multipleSwapSquares() {
		game.setSquare(2, new SwapSquare(game, 2));
		game.setSquare(5, new SwapSquare(game, 5));
		game.movePlayer(1); //moves Jack
		assertEquals(1, jack.position());
		assertEquals(2, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(2); //moves Jill
		assertEquals(1, jack.position());
		assertEquals(4, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(4); //moves Joe
		assertEquals(5, jack.position());
		assertEquals(4, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(3); //moves jack
		assertEquals(8, jack.position());
		assertEquals(4, jill.position());
		assertEquals(1, joe.position());
	}
	
	@Test
	public void allSwapSquares() {
		for(int i = 2; i < 15; i++) {
			game.setSquare(i, new SwapSquare(game, i));
		}
		game.movePlayer(3); //moves Jack
		assertEquals(1, jack.position());
		assertEquals(4, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(2); //moves Jill
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(6, joe.position());
		game.movePlayer(4); //moves Joe
		assertEquals(10, jack.position());
		assertEquals(1, jill.position());
		assertEquals(1, joe.position());
		game.movePlayer(2); //moves Jack
		assertEquals(1, jack.position());
		assertEquals(12, jill.position());
		assertEquals(1, joe.position());
	}

}