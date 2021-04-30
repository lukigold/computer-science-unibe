package snakes;

/**
 * Special square where players can be teleported to upon entering Wormhole Entrance Square.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class WormholeExit extends Square {

	/**
	 * Creates Wormhole Exit Square.
	 * @param game		the current game.
	 * @param position	the position where the wormhole exit square should be at.
	 * @see 			snakes.Game
	 */
	public WormholeExit(Game game, int position) {
		super(game, position);

	}

	/**
	 * Checks if this game is a Wormhole Exit Square.
	 * @return 			always true since this is a Wormhole Exit Square.
	 */
	public boolean isWormholeExit() {
		return true;
	}

	/**
	 * Generates a string with the position of the square and the (Exit) field.
	 * @return			string with position and (Exit).
	 */
	@Override
	public String squareLabel() {
		return String.format("%d (Exit)", position);
	}
}