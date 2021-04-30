package checkers;

import checkers.Token.*;
import checkers.Fields.*;

/**
 * Represents Player who controls pieces in game.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Player {
	private String name;
	private Token token;
	private Game game;

	/**
	 * Creates a Player.
	 * @param name the players name.
	 * @param token the players token.
	 * @param game the game in which the player should be.
	 */
	public Player(String name, Token token, Game game) {
		assert name != null;
		this.name = name;
		this.token = token;
		this.game = game;
	}

	/**
	 * @return String of a Players Name surrounded by brackets.
	 */
	public String toString() {
		return name + " (" + this.token.getClass().getSimpleName() + ")";
	}

	/**
	 * @return the token of the player.
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @return true if the Player can make a move false if there is no possible move.
	 */
	public boolean canMakeMove() {
		return isThereAnyPossibleMove();
	}

	/**
	 * Checks if this player can make any move at all across all his pieces
	 * @return boolean	true if there is any move, false otherwise
	 */
	private boolean isThereAnyPossibleMove() {
		int i = 0;
		Field[][] fields = game.getFields();
		for(int u = 0; i < fields.length; i++) {
			for(int j = 0; j < fields[i].length; j++) {
				if(fields[i][j] == null){u++;}
				else if(u==(fields.length*fields[i].length)){return false;}
				else{
					if(fields[i][j].getPiece() != null && fields[i][j].getPiece().getToken().getClass().equals(this.token.getClass()) && fields[i][j].getPiece().canMakeValidMove()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks if token on one field is the same as this players
	 * @param x		int x-coordinate of field to check
	 * @param y		int y-coordinate of field to check
	 * @return		boolean true if it is the same, false otherwise
	 */
	public boolean isCorrectToken(int x, int y) {
		Field[][] fields = game.getFields();
		Piece pc = fields[y][x].getPiece();
		if(pc==null){
			return false;
		}
		else return fields[y][x].getPiece().getToken().getClass().equals(token.getClass());
	}
}
