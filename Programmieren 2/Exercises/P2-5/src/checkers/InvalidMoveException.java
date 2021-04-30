package checkers;

/**
 * Exception thrown when an invalid move is caught.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 */
public class InvalidMoveException extends Exception {
	public InvalidMoveException(String message) {
		super(message);
	}
}
