package checkers;

/**
 * Represents a command of a move.
 * Execution of this move will be delegated to the piece class.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class MoveCommand {
	Piece piece;
	String direction;

	/**
	 * Creates a MoveCommand
	 * @param piece the piece which is to be moved.
	 * @param direction the direction which the piece should move in.
	 */
	public MoveCommand(Piece piece, String direction) {
		assert(piece != null && direction != null);
		this.piece = piece;
		this.direction = direction;
	}

	/**
	 * Executes a MoveCommand.
	 * @throws InvalidMoveException if the move is not valuable.
	 */
	public void execute() throws InvalidMoveException {
		switch(direction) {
			case("upRight"):
				piece.moveUpRight();
				break;
			case("upLeft"):
				piece.moveUpLeft();
				break;
			case("downRight"):
				piece.moveDownRight();
				break;
			case("downLeft"):
				piece.moveDownLeft();
				break;
			default:
				throw new InvalidMoveException("Your direction is invalid.");
		}	
	}
}
