package checkers.Fields;

import static org.junit.jupiter.api.Assertions.assertAll;

import checkers.Piece;
import checkers.Token.Token;

/**
 * Represents White field on board.
 * Keeps track of his position and which piece is currently on it, if any.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class WhiteField implements Field{
    Piece piece;
    int posX;
    int posY;

    /**
     *Constructor for a WhiteFields.
     * @param posY      Coordinate Y where the white field should be at.
     * @param posX      Coordinate X  where the white field should be at.
     */
    public WhiteField(int posX, int posY) {
    	this.posX = posX;
    	this.posY = posY;
    }

    /**
     * Generic toString method
     * @return the white field as a String if it contains a piece it will return the piece as a string.
     */
    public String toString() {
        if(this.piece == null){return "_";}
        else{return this.piece.getToken().toString();}
    }

    /**
     * Sets the Piece of a White field.
     * @param piece desired piece.
     */
    @Override
    public void setPiece(Piece piece) {
    	this.piece = piece;
    }

    /**
     * gets the Piece of a White Field.
     * @return returns piece of White Field.
     */
    public Piece getPiece() {

        if(this.piece!=null){
            return this.piece;
        }
        else{
            return null;
        }

    }

    /**
     * Gets the X Coordinate position of a White Field.
     * @return int of x coordinate
     */
    public int getX() {
    	return this.posX;
    }

    /**
     * Gets the Y Coordinate position of a White Field.
     * @return int of y coordinate
     */
    public int getY() {
    	return this.posY;
    }

    /**
     * tells a Piece to leave the Field
     */
    public void leave() {
        assert this.piece != null;
    	this.piece = null;
    }

    /**
     * tells a Piece to enter the Field.
     * @param piece piece which should enter the Field.
     */
    public void enter(Piece piece) {
    	assert this.piece == null;
    	this.piece = piece;
    }

    /**
     * Checks to see if the field is Free.
     * @return True if the field is free false if the field contains a piece.
     */
	public boolean isVacant() {
		return this.piece == null;
	}

}
