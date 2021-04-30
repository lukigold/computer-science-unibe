package checkers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import checkers.Token.*;

import checkers.Fields.Field;

/**
 * Represents a Game which can be played.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 */
public class Game {
	private Board board;
	private static Field[][] fields;
	private static Queue<Player> players = new LinkedList<Player>();
	private static Player winner;

	/**
	 * Creates a new Game with two players.
	 * @param board  a board on which the game takes place.
	 * @param fields 2d array containing the Pieces of the game.
	 */
	public Game(Board board, Field[][] fields) {
		this.board = board;
		Game.fields = fields;
		players.add(new Player("player 1", new BlackToken(), this));
		players.add(new Player("player 2", new WhiteToken(), this));
	}

	public static void main(String[] args) throws InvalidInputException, InvalidMoveException {
		int x=0;
		int y=0;
		MoveCommand move;
		Game game = Parser.createGame("default.txt");
		Scanner scan = new Scanner(System.in);
		Renderer render = new Renderer(game.board);
		render.render();
		while(!isOver()) {
			boolean correctInput = false;
			boolean correctCoordinates = false;
			System.out.println(currentPlayerOut());
			do {
				try {	
					System.out.println("Enter x-Coordinate of desired piece to move: ");
					x = Integer.parseInt(scan.nextLine());
					System.out.println("Enter y-Coordinate of desired piece to move: ");
					y = Integer.parseInt(scan.nextLine());
					if(players.peek().isCorrectToken(x, y) && fields[y][x].getPiece().canMakeValidMove()) {
						correctInput = true;
					}
					correctCoordinates = true;
				} catch (Exception e) {
					correctCoordinates = false;
				}
				if(!correctInput || !correctCoordinates)
					System.out.println("Wrong piece chosen. Try again.");
			}
			while (!correctInput || !correctCoordinates);
			boolean correctDirection = false;
			do {
				System.out.println("Enter direction (upRight, upLeft, downRight, downLeft): ");
				String direction = scan.nextLine();
				try {
					move = new MoveCommand(fields[y][x].getPiece(), direction);
					move.execute();
					correctDirection = true;
				} catch (Exception e) {
					correctDirection = false;
					System.out.println("Invalid Direction. Try Again.");
				}
			} while(!correctDirection);
			
			Player player = players.remove();
			players.add(player);
			checkIfPlayerWon(player);
			render.render();
		}
		scan.close();
		System.out.println("The winner is: " + winner);
	}

	/**
	 * Checks to see if the given Player has won the game.
	 * @param player player to be checked.
	 */
	public static void checkIfPlayerWon(Player player) {
		if(!players.peek().canMakeMove())
			winner = player;
	}

	/**
	 * Creates a String containing the current player.
	 * @return String 		Player : the current player.
	 */
	private static String currentPlayerOut() {
		String player = players.peek().toString();
		return "Player: "+ player;
	}

	/**
	 * checks if the game has a winner.
	 * @return retruns true if game has a winer.
	 */
	private static boolean isOver() {
		return winner != null;
	}

	/**
	 * gets the Fields of a game.
	 * @return fields of a game.
	 */
	public Field[][] getFields() {
		return fields;
	}
	
}
