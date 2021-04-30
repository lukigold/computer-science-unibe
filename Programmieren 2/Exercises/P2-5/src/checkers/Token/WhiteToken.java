package checkers.Token;

/**
 * Represents White Token in game.
 * Keeps track if it is a King or not.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class WhiteToken implements Token {
    boolean isKing;

    /**
     * to String method.
     * @return String of the Token, "W" if Token is King and "w" if it is a normal Token.
     */
    @Override
    public String toString() {
        if(isKing){return "W";}
        else{return "w";}
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
