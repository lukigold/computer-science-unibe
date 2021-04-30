package checkers.Fields;

import checkers.Piece;
import checkers.Token.BlackToken;
import checkers.Token.Token;

/**
 * Represents field. tracks current piece and position in board
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public interface Field {

    Piece piece = null;

    String toString();

    /**
     * set piece to desired one
     * @param piece		desired piece
     */
    void setPiece(Piece piece);
    
    /**
     * Gets piece of field
     * @return piece	piece of field
     */
    Piece getPiece();
    
    /**
     * get X cooridate of field
     * @return x		int which represent x coordinate
     */
    int getX();
    
    /**
     * get Y cooridate of field
     * @return x		int which represent y coordinate
     */
    int getY();
    
    /**
     * current piece leaves field
     */
    void leave();
    
    /**
     * desired piece enters field
     * @param piece		piece which enters
     */
    void enter(Piece piece);

    /**
     * Checks to see if a Field is Free.
     * @return true if the field is free else false.
     */
    boolean isVacant();
    

}
