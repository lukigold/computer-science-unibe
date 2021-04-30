package checkers.Token;

/**
 * Represents a colored Token of a Game.
 * Keeps track if it is a King or not.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public interface Token {

    /**
     * Generic toString method.
     * @return the Token as a String.
     */
    public String toString();

    /**
     * Checks to see if a Token is a King.
     * @return true if a Token is a King.
     */
    public boolean isKing();


    /**
     * makes a token to a King.
     */
    public void changeToKing();
}
