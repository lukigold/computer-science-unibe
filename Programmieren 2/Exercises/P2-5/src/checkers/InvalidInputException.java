package checkers;

/**
 * Exception thrown when an invalid input is caught.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 */
public class InvalidInputException extends Exception {
	public InvalidInputException(String message) {
		super(message);
	}
}
