package checkers;

/**
 * Specification which holds width, height and columns with pieces to create board.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 */
public class BoardSpecification {
	private int width;
	private int height;
	private int columnsWithPieces;

	/**
	 * Creates a BoardSpecification with the given parameter.
	 * @param width 					Board Width.
	 * @param height					Board Height.
	 * @param columnsWithPieces			amount of the columns containing the pieces.
	 */
	public BoardSpecification(int width, int height, int columnsWithPieces) {
		this.width = width;
		this.height = height;
		this.columnsWithPieces = columnsWithPieces;
	}

	/**
	 * @return the width of a Board.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height of a Board.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return Amount of columns with Pieces.
	 */
	public int getColumnsWithPieces() {
		return columnsWithPieces;
	}
}
