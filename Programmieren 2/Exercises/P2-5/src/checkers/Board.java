package checkers;


import checkers.Fields.*;
import checkers.Token.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates board and visualizes it.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Board {
	private int width;
	private int height;
	private int columnsWithPieces;
	private Field[][] fields;

	/**
	 * adds width, height and columns with pieces from specs to variables.
	 * @param specs		BoardSpecificiation which hold the Informations
	 */
	public Board(BoardSpecification specs){
		width = specs.getWidth();
		height = specs.getHeight();
		columnsWithPieces = specs.getColumnsWithPieces();
		fields = new Field[this.height+2][this.width+2];
		this.initialize();
    }
    
	/**
	 * initializes board with correct height and width
	 */
	private void initialize(){
		Arrays.stream(fields).forEach(a -> Arrays.fill(a, new Border()));

		for (int i = 1; i < height; i+=2) {
			for (int j = 1; j < width; j+=2) {
				fields[i+1][j] = new BlackField(i+1, j);
				fields[i+1][j+1]= new WhiteField(i+1, j+1);
				fields[i][j] = new WhiteField(i, j);
				fields[i][j+1] = new BlackField(i, j+1);
			}
		}
		this.initializeToken();
	}

	/**
	 * places pieces on dark squares at the beginning
	 */
	private void initializeToken(){
		for(int j = 1; j<= columnsWithPieces; j++){
			for(int i = 2; i < width+1; i+=2){
				if(j%2==0){
					fields[j][i-1].setPiece(new Piece(fields[j][i-1], new BlackToken(), this));
					fields[height-j+1][i].setPiece(new Piece(fields[height-j+1][i], new WhiteToken(), this));
				}
				else{
					fields[j][i].setPiece(new Piece(fields[j][i], new BlackToken(), this));
					fields[height-j+1][i-1].setPiece(new Piece(fields[height-j+1][i-1], new WhiteToken(), this));
				}
			}
		}

	}
	
	/**
	 * Visualizes board as a String.
	 */
	public void toConsole(){
		for (int i = 0; i < height+2; i++) {
			if(i==0){System.out.println(getCoordinatesInOneLine()); System.out.print("y ");}
			else if (i!=height+1){System.out.print(i+" ");}
			else{System.out.print("  ");}
			for (int j = 0; j < width+2; j++) {
				System.out.print(fields[i][j].toString() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * gets The fields of a board.
	 * @return fields[][] of a board.
	 */
	public Field[][] getFields() {
		return fields;
	}

	/**
	 * gets height of a board.
	 * @return int height of a board.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * gets width of a board.
	 * @return int width of a board.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * helper method to get the coordinates in one line.
	 * @return String containing the coordinates.
	 */
	private String getCoordinatesInOneLine(){
		int w = width;
		String line = "  x ";
		String current = "";
		for(int i = 1; i<= w; i++){
			current = String.format("%d", i);
			line += current + " ";
		}
		return line;
	}
}
