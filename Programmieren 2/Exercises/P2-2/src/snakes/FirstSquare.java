package snakes;

import java.util.ArrayList;
import java.util.List;

/**
 * Special square where players spawn at the beginning of the game.
 * It is the only square which can hold multiple players at once.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class FirstSquare extends Square {

	private List<Player> players;

	/**
	 * Creates the First Square.
	 * @param game  	the current game.
	 * @param position	the position where the first square should be at.
	 * @see 			snakes.Game
	 */
	public FirstSquare(Game game, int position) {
		super(game, position);
		players = new ArrayList<Player>();
	}

	/**
	 * Checks if square is already occupied by another player.
	 * If it is, go to the first square, else enter this square.
	 * @return square	the ISquare which was entered
	 */
	public ISquare landHereOrGoHome() {
		return this;
	}

	/**
	 * Checks if this square is occupied.
	 * @return			boolean with true if it square is not empty, otherwise false.
	 */
	@Override
	public boolean isOccupied() {
		return !players.isEmpty();
	}

	/**
	 * Adds player to this square and asserts that this player has not jet been added.
	 * @param player	the player who enters this square.
	 * @see				snakes.Player
	 */
	@Override
	public void enter(Player player) {
		assert !players.contains(player);
		players.add(player);
	}

	/**
	 * Removes player from this square.
	 * @param player	the player who leaves this square.
	 * @see				snakes.Player
	 */
	@Override
	public void leave(Player player) {
		assert players.contains(player);
		players.remove(player);
	}

	/**
	 * Checks if this is the first square.
	 * @return			always true.
	 */
	@Override
	public boolean isFirstSquare() {
		return true;
	}

	/**
	 * Checks which players are on the first square and prints them.
	 * @return			string of all players on first squares.
	 */
	@Override
	protected String player() {
		StringBuffer buffer = new StringBuffer();
		for (Player player : players) {
			buffer.append("<" + player + ">");
		}
		return buffer.toString();
	}
}
