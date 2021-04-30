package snakes;

import java.util.*;

/**
 * Special square when player enters he's put to a random Wormhole Exit Square.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class WormholeEntrance extends Square {


	/**
	 * Creates a Wormhole Entrance Square.
	 * @param game		the current game.
	 * @param position	the position where the wormhole entrance square should be at.
	 * @see 			snakes.Game
	 */
	public WormholeEntrance(Game game, int position) {
		super(game, position);
	}

	/**
	 * Adds current player to position of a random Wormhole Exit Square.
	 * @param player the player who enters this square.
	 */
	@Override
	public void enter(Player player) {
		ISquare randSquare = findRandomExit();
		ISquare actualSquare = randSquare.landHereOrGoHome();
		player.setSquare(actualSquare);
		actualSquare.enter(player);
	}

	/**
	 * Generates the label of this square with position and (Entrance).
	 * @return			string of the position and (Entrance).
	 */
	@Override
	public String squareLabel() {
		return String.format("%d (Entrance)", position);
	}
	//returns random square that is a wormhole exit

	/**
	 * Helper-method to find a Random Exit for the player to be sent to.
	 * @return			Wormhole exit square.
	 */
	private ISquare findRandomExit(){
		Random rnd = new Random();
		return (game.wormholeExits().get(rnd.nextInt(game.wormholeExits().size())));
	}
}