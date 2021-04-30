package snakes;

/**
 * Special square when player enters the next player on the queue is skipped extends Square.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class SkipSquare extends Square {

	/**
	 * Creates a Skip Square
	 * @param game 		the current game.
	 * @param position	the position where the skip square should be at.
	 * @see 			snakes.Game
	 */
	public SkipSquare(Game game, int position) {
		super(game, position);
	}

	/**
	 * Generates a string with the position of the square and the (Skip) field.
	 * @return string with position and (Skip).
	 */
	@Override
	public String squareLabel() {
		return String.format("%d (Skip)", position);
	}

	/**
	 * Adds Player to this Square and removes the next player in the queue.
	 * @param player the player who enters this square.
	 */
	@Override
	public void enter(Player player) {
		super.enter(player);
		Player curr = game.getTopElementOfQueue();
		game.removePlayerFromQueue();
		game.addPlayerToQueue(curr);
	}
}
