package checkers;
import java.io.*;
import java.util.*;

public class Parser {
	
	/**
	 * Creates game from given filename.
	 * @param filename		String which holds Specification for game
	 * @return game			Game which was created
	 * @throws 				InvalidInputException
	 */
	public static Game createGame(String filename) throws InvalidInputException {
		String[] data = inputFromFile(filename);
		BoardSpecification specs = convertToSpecs(data);
		Board board = new Board(specs);
		Game game = new Game(board, board.getFields());
		return game;
	}
	
	
	/**
	 * Returns content of file as a Array which holds every word.
	 * @param filename 	String which represents the filename
	 * @return data		String array which holds the content of file
	 */
	public static String[] inputFromFile(String filename) {
		String data = "";
		try {
			File file = new File("games/" + filename);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				data += scanner.nextLine(); 
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occured.");
		}
		String[] splited = data.split(" "); //splits String by spaces
		return splited;
	}
	
	/**
	 * If input is valid converts Data into BoardSpecifiation
	 * @param data		String array which holds the specifications
	 * @return specs	BoardSpecification
	 * @throws 			InvalidInputException
	 */
	public static BoardSpecification convertToSpecs(String[] data) throws InvalidInputException {

		if(!isValidInput(data)) {
			throw new InvalidInputException("Your input is invalid.");
			
		}
		else {
			int width = Integer.parseInt(data[0]);
			int height = Integer.parseInt(data[1]);
			int numberOfPieces = Integer.parseInt(data[2]);
			BoardSpecification specs = new BoardSpecification(width, height, numberOfPieces);
			return specs;
		}
		
	}
	
	/**
	 * Checks if input is valid. Input is valid if it fulfills the following points:
	 *
	 * there are exactly 3 parameters
	 * all parameters are integers
	 * width and height are even and have a size of at least 6
	 * the board is not full
	 *
	 * @param data		String array which holds the specifications
	 * @return boolean	True if valid input, otherwise false
	 */
	public static boolean isValidInput(String[] data) {
		if(data.length != 3) //At least one parameter is missing or is too much
			return false;
		for(int i = 0; i < data.length; i++) { //Not all parameters are integers
			try {
				Integer.parseInt(data[i]);
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		int width = Integer.parseInt(data[0]);
		int height = Integer.parseInt(data[1]);
		int numberOfPieces = Integer.parseInt(data[2]);
		if(width < 6 || height < 6 || height%2 != 0 || width%2 != 0) //width or height is not even or are less than 6
			return false;
		if(numberOfPieces*2 >= height) //board is full with pieces
			return false;
		return true;
	} 
}
