package checkers.Fields;

import checkers.Piece;
import checkers.Token.Token;

/**
 * Represents Border on board.
 * Can't keep any players.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Border implements Field{

	/**
	 * Converts the Boarder to its representing String value (#).
	 * @return a String of the boarder field.
	 */
    public String toString() {
        return "#";
    }

	/**
	 * Sets the Piece of a boarder field since a boarder field cannot contain a piece it does not do anything.
	 * @param piece desired piece.
	 */
    @Override
    public void setPiece(Piece piece) {
    	return;
    }

	/**
	 * does not return anything with value since Boarder field does not have a X coordinate.
	 * @return 0
	 */
	@Override
	public int getX() {
		return 0;
	}

	/**
	 * does not return anything with value since Boarder field does not have a Y coordinate.
	 * @return 0
	 */
	@Override
	public int getY() {
		return 0;
	}

	/**
	 * does not do anything since boarder cannot contain a piece.
	 */
	@Override
	public void leave() {
	}

	/**
	 * does not do anything since boarder cannot contain a piece.
	 */
	@Override
	public void enter(Piece piece) {
	}

	/**
	 * gets Piece but boarder field cannot contain a Piece.
	 * @return null
	 */
	@Override
	public Piece getPiece() {
		return null;
	}

	/**
	 * checks to see if a Boarder field is Free .
	 * @return false because if should not contain any Piece.
	 */
	@Override
	public boolean isVacant() {
		return false;
	}
}
