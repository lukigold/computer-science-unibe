package snakes;

/**
 * Special square that moves Player to a square that is situated before the current square.
 * Similar to <code>Ladder.java</code>.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Snake extends Ladder {

	/**
	 * Creates instance of snake square.
	 * @param transport	amount of moves to travel.
	 * @param game		current game.
	 * @param position	position where this square should be situated.
	 */
	public Snake(int transport, Game game, int position) {
		super(transport, game, position);
	}
	
	/**
	 * Generates String with current position and destination.
	 * @return label	string with current position and destination.
	 */
	@Override
	protected String squareLabel() {
		return this.destination().position() + "<-" + position;
	}

}
