package checkers.Token;

import checkers.Token.Token;

/**
 * Represents Black Token in game.
 * Keeps track if it is a King or not.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class BlackToken implements Token {
    boolean isKing = false;

    /**
     * to String method.
     * @return String of the Token, B if Token is King and b if it is a normal Token.
     */
    @Override
    public String toString() {
        if(isKing){return "B";}
        else{return "b";}
    }

    /**
     * Checks if a Token is a King.
     * @return true if the Token is a King else false.
     */
    public boolean isKing() {
        return isKing;
    }

    /**
     * Makes a Token to a King.
     */
    public void changeToKing(){
        this.isKing = true;
    }

}
