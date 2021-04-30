package snakes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class WormholeTest {
	private Player jack;
	private Player jill;
	private Game game;

	@BeforeEach
	public void initializeGame() {
		jack = new Player("Jack");
		jill = new Player("Jill");
		Player[] args = { jack, jill};
		game = new Game(24, args);
		game.setSquareToLadder(2, 2);
		game.setSquareToLadder(7, -5);
		game.setSquare(4, new WormholeEntrance(game, 4));
		game.setSquare(10, new WormholeExit(game, 10));
		game.setSquare(23,new WormholeExit(game, 23));
		game.setSquare(12,new WormholeEntrance(game,12));
	}

	@Test
	public void newGame() {
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
		assertEquals("Jack", jack.toString());
		assertEquals("Jill", jill.toString());
		assertEquals("[1<Jack><Jill>]", game.firstSquare().toString());
		assertEquals("[4 (Entrance)]", game.getSquare(4).toString());
		assertEquals("[12 (Entrance)]", game.getSquare(12).toString());
		assertEquals("[10 (Exit)]", game.getSquare(10).toString());
		assertEquals("[23 (Exit)]", game.getSquare(23).toString());
	}

	@Test
	public void jackMove1() {
		game.movePlayer(3);
		assertTrue(jack.position()==10 && jack.position()!=23||jack.position()==23 && jack.position()!=10);
		assertEquals(1, jill.position());
		assertTrue(game.notOver());
	}

	@Test
	public void jillMove1() {
		game.movePlayer(3);
		game.movePlayer(3);
		assertTrue(jill.position()!=jack.position());
		assertTrue(jill.position()== 1 || jill.position() == 10 || jill.position() == 23);
		assertTrue(game.notOver());
	}

	@Test
	public void JackAndJillLandOn22() {
		jack.moveForward(22);
		assertEquals(23, jack.position());
		jill.moveForward(22);
		assertEquals(1,jill.position());
		assertTrue(game.notOver());
	}

	@Test
	public void JackIsOn22JillEntersWormhole() {
		jack.moveForward(22);
		assertEquals(23, jack.position());
		jill.moveForward(3);
		assertTrue(jill.position()==1||jill.position()==10);
		assertTrue(game.notOver());
	}

	@Test
	public void Test6() {

	}
}
