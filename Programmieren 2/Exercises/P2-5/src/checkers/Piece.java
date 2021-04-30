package checkers;
import java.util.ArrayList;
import java.util.Scanner;

import checkers.Fields.Field;
import checkers.Token.*;

/**
 * Represents Piece which tracks current field, token and board.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Piece {
	private Field field;
	private Token token;
	private Board board;

	public boolean invariant(){return token!=null&&field!=null&&board!=null;}

	/**
	 * Creates a Piece.
	 * @param field in which the Piece is in.
	 * @param token that shows which players token it is and if it is a king or not.
	 * @param board the board on which the piece is.
	 */
	public Piece(Field field, Token token, Board board) {
		this.field = field;
		this.token = token;
		this.board = board;
		assert invariant();
	}

	/**
	 * @return the token of the piece.
	 */
	public Token getToken() {
		return this.token;
	}

	/**
	 * @return the field of the piece.
	 */
	public Field getField() {
		return field;
	}

	/**
	 * moves piece diagonally up right.
	 * checks if destination is in bounds of board and piece is allowed to move this way.
	 * @throws InvalidMoveException		if move will be out of bounds or is not allowed, exception will be thrown
	 */
	public void moveUpRight() throws InvalidMoveException {
		int x = field.getX();
		int y = field.getY();
		boolean moveMade = false;
		Field[][] fields = board.getFields();
		if(isValidMove(x, y, "upRight")) { //checks if it is a valid move
			if(fields[y-1][x+1].isVacant()) {
				normalMove(x, y, "upRight", fields);
				moveMade = true;
			}
			else {	
				if(canJumpOver(this.field.getX(), this.field.getY(), "upRight", fields)) {
					jumpOver(field.getX(), field.getY(), "upRight", fields);
					moveMade = true;
					ArrayList<String> directions = possibleJumpDirections();
					while(directions.size() > 0) {
						executeMultipleJumping(directions);
						directions = possibleJumpDirections();
					}
				}
			}
		}
		if(!moveMade)
			throw new InvalidMoveException("Your move is invalid.");
		if(this.isAtEndOfBoard() && !this.isKing()) //if piece is not yet a king, checks if piece is now at a end of the board
			this.token.changeToKing();
	}
	
	/**
	 * moves piece diagonally up left.
	 * checks if destination is in bounds of board and piece is allowed to move this way.
	 * @throws InvalidMoveException		if move will be out of bounds or is not allowed, exception will be thrown
	 */
	public void moveUpLeft() throws InvalidMoveException {
		int x = field.getX();
		int y = field.getY();
		boolean moveMade = false;
		Field[][] fields = board.getFields();
		if(isValidMove(x, y, "upLeft")) {
			if(fields[y-1][x-1].isVacant()) {	
				normalMove(x, y, "upLeft", fields);
				moveMade = true;
			}
			else {	
				if(canJumpOver(this.field.getX(), this.field.getY(), "upLeft", fields)) {
					jumpOver(field.getX(), field.getY(), "upLeft", fields);
					moveMade = true;
					ArrayList<String> directions = possibleJumpDirections();
					while(directions.size() > 0) {
						executeMultipleJumping(directions);
						directions = possibleJumpDirections();
					}
				}
			}
		}
		if(!moveMade)
			throw new InvalidMoveException("Your move is invalid.");
		if(this.isAtEndOfBoard() && !this.isKing()) //if piece is not yet a king, checks if piece is now at a end of the board
			this.token.changeToKing();
	}
	
	/**
	 * moves piece diagonally down left.
	 * checks if destination is in bounds of board and piece is allowed to move this way.
	 * @throws InvalidMoveException		if move will be out of bounds or is not allowed, exception will be thrown
	 */
	public void moveDownLeft() throws InvalidMoveException {
		int x = field.getX();
		int y = field.getY();
		boolean moveMade = false;
		Field[][] fields = board.getFields();
		if(isValidMove(x, y, "downLeft")) {
			if(fields[y+1][x-1].isVacant()) {	
				normalMove(x, y, "downLeft", fields);
				moveMade = true;
			}
			else {	
				if(canJumpOver(this.field.getX(), this.field.getY(), "downLeft", fields)) {
					jumpOver(field.getX(), field.getY(), "downLeft", fields);
					moveMade = true;
					ArrayList<String> directions = possibleJumpDirections();
					while(directions.size() > 0) {
						executeMultipleJumping(directions);
						directions = possibleJumpDirections();
					}
				}
			}
		}
		if(!moveMade)
			throw new InvalidMoveException("Your move is invalid.");
		if(this.isAtEndOfBoard() && !this.isKing()) //if piece is not yet a king, checks if piece is now at a end of the board
			this.token.changeToKing();
	}
	
	/**
	 * moves piece diagonally down right.
	 * checks if destination is in bounds of board and piece is allowed to move this way.
	 * @throws InvalidMoveException		if move will be out of bounds or is not allowed, exception will be thrown
	 */
	public void moveDownRight() throws InvalidMoveException {
		int x = field.getX();
		int y = field.getY();
		boolean moveMade = false;
		Field[][] fields = board.getFields();
		if(isValidMove(x, y, "downRight")) {
			if(fields[y+1][x+1].isVacant()) {
				normalMove(x, y, "downRight", fields);
				moveMade = true;
			}
			else {
				if(canJumpOver(this.field.getX(), this.field.getY(), "downRight", fields)) {
					jumpOver(field.getX(), field.getY(), "downRight", fields);
					moveMade = true;
					ArrayList<String> directions = possibleJumpDirections();
					while(directions.size() > 0) {
						executeMultipleJumping(directions);
						directions = possibleJumpDirections();
					}
				}
			}
		}
		if(!moveMade)
			throw new InvalidMoveException("Your move is invalid.");
		if(this.isAtEndOfBoard() && !this.isKing()) //if piece is not yet a king, checks if piece is now at a end of the board
			this.token.changeToKing();
	}
	
	/**
	 * checks if current piece is white
	 * @return boolean 		true if it is white, false otherwise
	 */
	public boolean isWhiteToken() {
		return this.token.getClass().getSimpleName().equals("WhiteToken");
	}
	
	/**
	 * checks if token of piece is a king
	 * @return boolean		true if it is a king, false otherwise
	 */
	public boolean isKing() {
		return this.token.isKing();
	}
	
	
	/**
	 * checks if piece is at one end of the board
	 * @return boolean		true if it is at end of the board, false otherwise
	 */
	public boolean isAtEndOfBoard() {
		return (this.field.getY() == 1 || this.field.getY() == board.getHeight());
	}
	
	/**
	 * checks if move is in bounds of board
	 * @param x				int which represents current x value
	 * @param y				int which represents current y value
	 * @param direction		String which direction the move is
	 * @return boolean		true if move is in bounds, false otherwise
	 */
	public boolean isMoveInBounds(int x, int y, String direction) {
		switch(direction) {
			case "upRight":
				return x+1 < board.getWidth()+2 && y-1 > 0;
			case "upLeft":
				return x-1 > 0 && y-1 > 0 ;
			case "downLeft":
				return x-1 > 0 && y+1 < board.getHeight()+2;
			case "downRight":
				return x+1 < board.getWidth()+2 && y+1 < board.getHeight()+2;
			default:
				return false;
		}
	}
	
	/**
	 * checks if piece can jump over another piece
	 * @param x				int which represents current x value
	 * @param y				int which represents current y value
	 * @param direction		String which direction the move is
	 * @param fields		2d ArrayList which represents the board
	 * @return boolean		true if move is in bounds, false otherwise
	 */
	public boolean canJumpOver(int x, int y, String direction, Field[][] fields) {
		switch (direction) {
			case "upRight":
				return !fields[y-1][x+1].isVacant() && isMoveInBounds(x+1, y-1, "upRight") && fields[y-2][x+2].isVacant() && !fields[y-1][x+1].getPiece().getToken().getClass().equals(this.token.getClass());
			case "upLeft":
				return !fields[y-1][x-1].isVacant() && isMoveInBounds(x-1, y-1, "upLeft") && fields[y-2][x-2].isVacant() && !fields[y-1][x-1].getPiece().getToken().getClass().equals(this.token.getClass());
			case "downLeft":
				return !fields[y+1][x-1].isVacant() && isMoveInBounds(x-1, y+1, "downLeft") && fields[y+2][x-2].isVacant() && !fields[y+1][x-1].getPiece().getToken().getClass().equals(this.token.getClass());
			case "downRight":
				return !fields[y+1][x+1].isVacant() && isMoveInBounds(x+1, y+1, "downRight") && fields[y+2][x+2].isVacant() && !fields[y+1][x+1].getPiece().getToken().getClass().equals(this.token.getClass());
			default:
				return false;
		}
	}
	
	/**
	 * makes normal Move depending on direction
	 * @param x				int which represents current x value
	 * @param y				int which represents current y value
	 * @param direction		String which direction the move is
	 * @param fields		2d ArrayList which represents the board
	 */
	private void normalMove(int x, int y, String direction, Field[][] fields) {
		field.leave();
		switch (direction) {
			case "upRight":
				fields[y-1][x+1].enter(this);
				this.field = fields[y-1][x+1];
				break;
			case "upLeft":
				fields[y-1][x-1].enter(this);
				this.field = fields[y-1][x-1];
				break;
			case "downLeft":
				fields[y+1][x-1].enter(this);
				this.field = fields[y+1][x-1];
				break;
			case "downRight":
				fields[y+1][x+1].enter(this);
				this.field = fields[y+1][x+1];
		}
	}
	
	/**
	 * jumps over other piece and removes it from game depending on direction
	 * @param x				int which represents current x value
	 * @param y				int which represents current y value
	 * @param direction		String which direction the move is
	 * @param fields		2d ArrayList which represents the board
	 */
	private void jumpOver(int x, int y, String direction, Field[][] fields) {
		field.leave();
		switch (direction) {
			case "upRight":
				fields[y-2][x+2].enter(this);
				this.field = fields[y-2][x+2];
				fields[y-1][x+1].setPiece(null);
				break;
			case "upLeft":
				fields[y-2][x-2].enter(this);
				this.field = fields[y-2][x-2];
				fields[y-1][x-1].setPiece(null);
				break;
			case "downLeft":
				fields[y+2][x-2].enter(this);
				this.field = fields[y+2][x-2];
				fields[y+1][x-1].setPiece(null);
				break;
			case "downRight":
				fields[y+2][x+2].enter(this);
				this.field = fields[y+2][x+2];
				fields[y+1][x+1].setPiece(null);
		}
	}

	/**
	 * @param x              	int which represents current x value
	 * @param y					int which represents current y value
	 * @param direction			String which direction the move is
	 * @return boolean			True if the given move is valid else false
	 */
	public boolean isValidMove(int x, int y, String direction) {
		switch (direction) {
			case "upRight":
				return isMoveInBounds(x, y, "upRight") && (isWhiteToken() || isKing()); 
			case "upLeft":
				return isMoveInBounds(x, y, "upLeft") && (isWhiteToken() || isKing());
			case "downLeft":
				return isMoveInBounds(x, y, "downLeft")  && (!isWhiteToken() || isKing());
			case "downRight":
				return isMoveInBounds(x, y, "downRight") && (!isWhiteToken() || isKing());
		}
		return false;
	}

	/**
	 * checks for any possible move.
	 * @return boolean 		true if any Move is possible else false.
	 */
	public boolean canMakeValidMove() {
		int x = field.getX();
		int y = field.getY();
		Field[][] fields = board.getFields();
		return isValidMove(x, y, "downRight") && (canJumpOver(x, y, "downRight", fields) || fields[y+1][x+1].isVacant()) || 
			   isValidMove(x, y, "upRight") && (canJumpOver(x, y, "upRight", fields) || fields[y-1][x+1].isVacant()) || 
			   isValidMove(x, y, "downLeft") && (canJumpOver(x, y, "downLeft", fields) || fields[y+1][x-1].isVacant()) || 
			   isValidMove(x, y, "upLeft") && (canJumpOver(x, y, "upLeft", fields) || fields[y-1][x-1].isVacant());
	}

	/**
	 * checks all the possible Jumping directions if there are none an empty list is returned.
	 * @return ArrayList containing all the possible move directions.
	 */
	public ArrayList<String> possibleJumpDirections() {
		int x = field.getX();
		int y = field.getY();
		ArrayList<String> possibleDirections = new ArrayList<String>();
		Field[][] fields = board.getFields();
		if(canJumpOver(x, y, "upRight", fields) && isValidMove(x, y, "upRight"))
			possibleDirections.add("upRight");
		if(canJumpOver(x, y, "upLeft", fields) && isValidMove(x, y, "upLeft"))
			possibleDirections.add("upLeft");
		if(canJumpOver(x, y, "downRight", fields) && isValidMove(x, y, "downRight"))
			possibleDirections.add("downRight");
		if(canJumpOver(x, y, "downLeft", fields) && isValidMove(x, y, "downLeft"))
			possibleDirections.add("downLeft");
		
		return possibleDirections;
		
	}

	/**
	 * prints out a given ArrayList.
	 * @param arr 	ArrayList to be printed.
	 */
	public void printArrayList(ArrayList<String> arr) {
		for(String element:arr)
			System.out.println(element);
	}

	/**
	 * Checks a String for direction
	 * @param direction String with direction
	 * @return boolean 		returns true if String equals one of the directions.
	 */
	public boolean isCorrectDirection(String direction) {
		return direction.equals("upRight") || direction.equals("upLeft") || direction.equals("downRight") || direction.equals("downLeft");
	}

	/**
	 * handels the execution of multiple possible JumpOvers.
	 * @param directions 	ArrayList containing every possible direction.
	 */
	private void executeMultipleJumping(ArrayList<String> directions) {
		boolean correctInput = false;
		Field[][] fields = board.getFields();
		if(directions.size() == 1)
			jumpOver(field.getX(), field.getY(), directions.get(0), fields);
		if(directions.size() > 1) {
			Scanner scan = new Scanner(System.in); //scanner shall not be closed
			System.out.println("You can jump into the following directions. Choose one:");
			printArrayList(directions);
			while(!correctInput) {
				String direction = scan.nextLine();
				if(isCorrectDirection(direction)) {
					jumpOver(field.getX(), field.getY(), direction, fields);
					correctInput = true;
				}
				else {
					System.out.println("Wrong direction. Try again.");
				}
			}
		}
	}
}
