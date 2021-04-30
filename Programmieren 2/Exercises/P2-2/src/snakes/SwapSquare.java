package snakes;

/**
 * Special square when player enters he swaps position with next player in queue.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class SwapSquare extends Square {

	/**
	 * Creates a Swap Square
	 * @param game 		the current game.
	 * @param position	the position where the swap square should be at.
	 * @see				snakes.Game
	 */
	public SwapSquare(Game game, int position) {

		super(game, position);
	}

	/**
	 * Adds current player to position where next player in queue is and him to this square.
	 * @param player the player who enters this square.
	 */
	@Override
	public void enter(Player player) {
		super.enter(player);
		Player nextPlayer = game.getTopElementOfQueue();
		ISquare nextSquare = nextPlayer.square();
		
		this.leave(player);
		nextSquare.leave(nextPlayer);
		
		nextPlayer.setSquare(this);
		player.setSquare(nextSquare);
		
		super.enter(nextPlayer);
		nextSquare.enter(player);
	}

	/**
	 * Generates the label of this square with position and (Swap).
	 * @return	string of the position and (Swap).
	 */
	@Override
	public String squareLabel() {
		return String.format("%d (Swap)", position);
	}
}