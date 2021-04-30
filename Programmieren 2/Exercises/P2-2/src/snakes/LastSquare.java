package snakes;

/**
 * Special square when player enters he wins the game extends Square.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class LastSquare extends Square {

	/**
	 * Creates the Last Square.
	 * @param game		the current game.
	 * @param position	the position where the last square should be at.
	 * @see 			snakes.Game
	 */
	public LastSquare(Game game, int position) {
		super(game, position);
	}

	/**
	 * Checks if it is the last square.
	 * @return 			always true.
	 */
	@Override
	public boolean isLastSquare() {
		return true;
	}
}
