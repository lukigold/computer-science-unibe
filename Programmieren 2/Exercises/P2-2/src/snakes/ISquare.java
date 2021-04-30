package snakes;
/**
 * Interface which provides methods for all implemented squares.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 * 
 */
public interface ISquare {
	/**
	 * Returns position of this square.
	 * @return 			the position of this square.
	 */
	public int position();

	/**
	 * Finds square which is given moves away and lands there.
	 * @param moves 	the amount of moves.
	 * @return square	the ISquare where it lands.
	 * @see				snakes.ISquare				 		 
	 */
	public ISquare moveAndLand(int moves);

	/**
	 * Checks if this is the first square.
	 * @return			boolean with true if it is the first square, otherwise false.
	 * @see				snakes.FirstSquare			
	 */
	public boolean isFirstSquare();
	
	/**
	 * Checks if this is the LastSquare.
	 * @return			boolean with true if it is the last square, otherwise false.
	 * @see				snakes.LastSquare
	 */
	public boolean isLastSquare();

	/**
	 * Adds player to this square.
	 * @param player	the player who enters this square.
	 * @see				snakes.Player
	 */
	public void enter(Player player);
	
	/**
	 * Removes player from this square.
	 * @param player	the player who leaves this square.
	 * @see				snakes.Player
	 */
	public void leave(Player player);

	/**
	 * Checks if this square is occupied.
	 * @return			boolean with true if it square is occupied, otherwise false.
	 */
	public boolean isOccupied();

	/**
	 * Checks if square is already occupied by another player.
	 * If it is, go to the first square, else enter this square.
	 * @return square	the ISquare which was entered
	 */
	public ISquare landHereOrGoHome();

	/**
	 * Checks if this square is a wormhole exit.
	 * @return			boolean with true if it square is a wormhole exit, otherwise false. 
	 * @see				snakes.WormholeExit
	 */
	boolean isWormholeExit();
}
