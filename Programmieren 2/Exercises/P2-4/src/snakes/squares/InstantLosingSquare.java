package snakes.squares;

import java.util.ArrayList;
import java.util.List;

import snakes.*;

/**
 * Upon entering this square, the player is removed from the game and stays on this square.
 * Because multiple people can lose, this square can hold multiple players (similar to first square).
 * 
 * @author Lukas Ingold
 * @author Kevin St�ckli
 *
 */
public class InstantLosingSquare extends StandardSquare {
	private List<Player> players;
	public InstantLosingSquare(Game game, int position) {
		super(game, position);
		players = new ArrayList<>();
	}
	
	@Override
	public void enter(Player player) {
		if(players.contains(player)){return;}
		assert !players.contains(player);
		players.add(player);
	}
	
	/**
	 * This square can never be full, so it doesn't to be verified if it is empty or not.
	 * If the last player landed on this square, the game ends in a tie.
	 * Otherwise, the current player is removed from the game.
	 */
	@Override
	public Square landHereOrGoHome() {
		if(game.nextPlayer() == null) {
			game.setTie();
			return this;
		}
		else {
			game.removePlayerFromGame(player);
			return this;
		}
	}
	/**
	 * Correctly puts all players into string.
	 */
	@Override
	protected String player() {
		StringBuffer buffer = new StringBuffer();
		for (Player player : players) {
			buffer.append("<" + player + ">");
		}
		return buffer.toString();
	}
	
	@Override
	protected String squareLabel() {
		return super.squareLabel() + "(Losing)";
	}
	
	public int howManyPlayers() {
		return players.size();
	}
	
	public List<Player> getPlayers() {
		return players;
	}

}
