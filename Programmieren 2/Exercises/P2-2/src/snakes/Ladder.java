package snakes;

/**
 * Special square which moves player to a square which is situated after current square.
 * Most responsibilities and functionalities are similar to its parent class <code>Square.java</code>.
 * Additionally, this square has to check if the destination is valid and empty to enter the player.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Ladder extends Square {
	
	
	private int transport;

	/**
	 * Invariant to see if transport is valid
	 * @return			boolean true if valid, else false
	 */
	private boolean invariant() {
		return isValidTransport(transport);
	}

	/**
	 * Checks if transport is valid.
	 * @param transport	int amount of moves to travel.
	 * @return			boolean true if valid transport, else false.
	 */
	private boolean isValidTransport(int transport) {
		return transport != 0 && game.isValidPosition(position + transport);
	}

	/**
	 * Creates instance of ladder square.
	 * @param transport	amount of moves to travel.
	 * @param game		current game.
	 * @param position	position where this square should be situated.
	 */
	public Ladder(int transport, Game game, int position) {
		super(game, position);
		assert isValidTransport(transport);
		this.transport = transport;
		assert invariant();
	}

	/**
	 * Generates String with current position and destination.
	 * @return label	string with current position and destination.
	 */
	@Override
	protected String squareLabel() {
		return position + "->" + this.destination().position();
	}

	/**
	 * Checks if destination is empty.
	 * @return ISquare	the destination square or the first square.
	 */
	@Override
	public ISquare landHereOrGoHome() {
		return this.destination().landHereOrGoHome();
	}

	/**
	 * Calculates destination square.
	 * @return ISquare	destination square.
	 */
	protected ISquare destination() {
		return game.getSquare(position + transport);
	}
}
