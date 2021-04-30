package snakes;
/**
 * Basic square which implements ISquare.
 * Its responsibilities are:
 * <ul>
 * 	<li>To know at which position the square is situated</li>
 * 	<li>To know which (if there is any) player is on this square</li>
 * </ul>
 * Its functionalities are to let a player enter and leave this square.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Square implements ISquare {

	protected int position;
	protected Game game;
	private Player player;

	/**
	 * Checks if Square is valid.
	 * @return			boolean true if position valid and game not null
	 */
	private boolean invariant() {
		return game != null && game.isValidPosition(position);
	}

	/**
	 * Generates the Square.
	 * @param game 		the current game.
	 * @param position	the position where the Square should be at.
	 */
	public Square(Game game, int position) {
		this.game = game;
		this.position = position;
		assert invariant();
	}

	/**
	 * Returns position of this square.
	 * @return 			the position of this square.
	 */
	public int position() {
		return this.position;
	}

	/**
	 * Finds square which is given moves away and lands there.
	 * @param moves 	the amount of moves.
	 * @return square	the ISquare where it lands.
	 * @see				snakes.ISquare
	 */
	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves).landHereOrGoHome();
	}

	/**
	 * Finds square to right and returns it.
	 * @return 		Square to the right.
	 */
	protected ISquare nextSquare() {
		return game.getSquare(position + 1);
	}

	/**
	 * Finds square to left and returns it.
	 * @return 		Square to the left.
	 */
	protected ISquare previousSquare() {
		return game.getSquare(position - 1);
	}

	/**
	 * Checks if square is already occupied by another player.
	 * If it is, go to the first square, else enter this square.
	 * @return 	square	the ISquare which was entered
	 */
	public ISquare landHereOrGoHome() {
		return this.isOccupied() ? game.firstSquare() : this;
	}

	/**
	 * Checks if this is a Wormhole-Exit-Square, since it is a regular
	 * square it always returns false.
	 * @return false
	 */
	@Override
	public boolean isWormholeExit() {
		return false;
	}

	/**
	 * Generates a string out of this square with its current player and number.
	 * @return	string of this square.
	 */
	public String toString() {
		return "[" + this.squareLabel() + this.player() + "]";
	}

	/**
	 * Finds position and returns it so toString() method can use it.
	 * @return string of squares Position
	 */
	protected String squareLabel() {
		return Integer.toString(position);
	}

	/**
	 * Checks if this square is occupied.
	 * @return			boolean with true if it square is occupied, otherwise false.
	 */
	public boolean isOccupied() {
		return player != null;
	}

	/**
	 * Adds player to this square.
	 * @param player	the player who enters this square.
	 * @see				snakes.Player
	 */
	public void enter(Player player) {
		assert this.player == null;
		this.player = player;
		
	}

	/**
	 * Removes player from this square.
	 * @param player	the player who leaves this square.
	 * @see				snakes.Player
	 */
	public void leave(Player player) {
		assert this.player == player;
		this.player = null;
	}

	/**
	 * Checks if this is the first square.
	 * @return			boolean with true if it is the first square, otherwise false.
	 * @see				snakes.FirstSquare
	 */
	public boolean isFirstSquare() {
		return false;
	}

	/**
	 * Checks if this is the LastSquare.
	 * @return			boolean with true if it is the last square, otherwise false.
	 * @see				snakes.LastSquare
	 */
	public boolean isLastSquare() {
		return false;
	}

	/**
	 * Checks who is currently on this square and generates a string.
	 * @return 		Current player on this square as a string.
	 */
	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}
}
