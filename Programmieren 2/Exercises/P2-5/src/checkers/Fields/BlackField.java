package checkers.Fields;

import checkers.Piece;
import checkers.Fields.Field;
import checkers.Token.Token;

/**
 * Represents Black field on board.
 * Keeps track of his position and which piece is currently on it, if any.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class BlackField implements Field {
    Piece piece;
    int posX;
    int posY;

    /**
     * Constructor for a BlackField.
     * @param posY      Coordinate Y where the black field should be at.
     * @param posX      Coordinate X  where the black field should be at.
     */
    public BlackField(int posY, int posX) {
    	this.posX = posX;
    	this.posY = posY;
    }

    /**
     * Generic toString method
     * @return the black field as a String if it contains a piece it will return the piece as a string.
     */
    public String toString() {
        if(this.piece == null){return "O";}
        else{return this.piece.getToken().toString();}
    }

    /**
     * Sets the Piece of a Black field.
     * @param piece desired piece.
     */
    @Override
    public void setPiece(Piece piece) {
    	this.piece = piece;
    }

    /**
     * gets the Piece of a Black Field.
     * @return returns piece of Black Field.
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
     * Gets the X Coordinate position of a Black Field.
     * @return int of x coordinate
     */
    public int getX() {
    	return this.posX;
    }

    /**
     * Gets the Y Coordinate position of a Black Field.
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
