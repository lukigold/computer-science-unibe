package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import checkers.*;
import checkers.Fields.*;

public class PieceTest {
	
	private BoardSpecification specs;
	private Board board;
	private Game game;
	private Field field;
	private Field dest;
	private Piece piece;
	
	@BeforeEach
	void init() {
		specs = new BoardSpecification(4, 4, 1);
		board = new Board(specs);
		game = new Game(board, board.getFields());
		
	}
	
	@Test
	public void testMoveUpRight() throws InvalidMoveException {
		field = board.getFields()[4][1];
		dest = board.getFields()[3][2];
		piece = field.getPiece();
		
		piece.moveUpRight();
		
		assertEquals(field.getPiece(), null);
		assertEquals(dest.getPiece(), piece);
	}
	
	@Test
	public void testMoveUpRightMoveBackwards() throws InvalidMoveException {
		field = board.getFields()[1][2];
		piece = field.getPiece();
		
		piece.moveDownLeft();
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveUpRight(); //piece cannot move backwards because it is not a king
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testMoveUpRightOutOfBounds() throws InvalidMoveException {
		field = board.getFields()[1][4];
		piece = field.getPiece();
		
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveUpRight(); //piece will move out of bounds
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	public void testMoveDownRight() throws InvalidMoveException {
		field = board.getFields()[1][2];
		dest = board.getFields()[2][3];
		piece = field.getPiece();
		
		piece.moveDownRight();
		
		assertEquals(field.getPiece(), null);
		assertEquals(dest.getPiece(), piece);
	}
	
	@Test
	public void testMoveDownRightOutOfBounds() throws InvalidMoveException {
		field = board.getFields()[1][4];
		piece = field.getPiece();
		
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveUpRight(); //piece will move out of bounds
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testMoveDownRightMoveBackwards() throws InvalidMoveException {
		field = board.getFields()[4][3];
		piece = field.getPiece();
		
		piece.moveUpLeft();
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveDownRight(); //piece cannot move backwards because it is not a king
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testMoveDownLeft() throws InvalidMoveException {
		field = board.getFields()[1][2];
		dest = board.getFields()[2][1];
		piece = field.getPiece();
		
		piece.moveDownLeft();
		
		assertEquals(field.getPiece(), null);
		assertEquals(dest.getPiece(), piece);
	}
	
	@Test
	public void testMoveDownLeftOutOfBounds() throws InvalidMoveException {
		field = board.getFields()[4][1];
		piece = field.getPiece();
		
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveDownLeft(); //piece will move out of bounds
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testMoveDownLeftMoveBackwards() throws InvalidMoveException {
		field = board.getFields()[4][1];
		piece = field.getPiece();
		
		piece.moveUpRight();
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveDownLeft(); //piece cannot move backwards because it is not a king
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testMoveUpLeft() throws InvalidMoveException {
		field = board.getFields()[4][3];
		dest = board.getFields()[3][2];
		piece = field.getPiece();
		
		piece.moveUpLeft();
		
		assertEquals(field.getPiece(), null);
		assertEquals(dest.getPiece(), piece);
	}
	
	@Test
	public void testMoveUpLeftOutOfBounds() throws InvalidMoveException {
		field = board.getFields()[4][1];
		piece = field.getPiece();
		
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveUpLeft(); //piece will move out of bounds
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	@Test
	public void testMoveUpLeftMoveBackwards() throws InvalidMoveException {
		field = board.getFields()[1][2];
		piece = field.getPiece();
		
		piece.moveDownRight();
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveUpLeft(); //piece cannot move backwards because it is not a king
		});
		
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testAtEndOfBoard() throws InvalidMoveException {
		field = board.getFields()[4][1];
		Field field1 = board.getFields()[1][2];
		piece = field.getPiece();
		Piece piece1 = field1.getPiece();
		
		//move two pieces to end of board
		piece.moveUpRight();
		piece.moveUpRight();
		piece1.moveDownLeft();
		piece1.moveDownRight();
		piece1.moveDownLeft();
		piece.moveUpLeft();
		
		assertTrue(piece.isAtEndOfBoard());
		assertTrue(piece1.isAtEndOfBoard());
		assertTrue(piece.isKing());
		assertTrue(piece1.isKing());
		
		piece.moveDownLeft();
		piece1.moveUpRight();
		
	}
	
	@Test 
	void testKingsMoveCorrectly() throws InvalidMoveException {
		field = board.getFields()[4][1];
		Field field1 = board.getFields()[1][2];
		piece = field.getPiece();
		Piece piece1 = field1.getPiece();
		
		//move two pieces to end of board
		piece.moveUpRight();
		piece.moveUpRight();
		piece1.moveDownLeft();
		piece1.moveDownRight();
		piece1.moveDownLeft();
		piece.moveUpLeft();
		//both pieces are now kings, so can now move backwards
		
		Field dest = board.getFields()[2][1];
		Field dest1 = board.getFields()[3][2];
		piece.moveDownLeft();
		piece1.moveUpRight();
		
		assertEquals(dest.getPiece(), piece);
		assertEquals(dest1.getPiece(), piece1);
		
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			piece.moveDownLeft(); //piece cannot move out of bounds
		});
		String expectedMessage = "Your move is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
		
	}
	
	@Test 
	void testJumpingOverCorrectly() throws InvalidMoveException {
		field = board.getFields()[1][2];
		Field field1 = board.getFields()[4][1];
		Field dest = board.getFields()[2][3];
		piece = field.getPiece();
		Piece piece1 = field1.getPiece();

		
		piece.moveDownLeft();
		piece.moveDownRight();
		//piece jumps over other piece
		piece1.moveUpRight();
		
		assertEquals(piece1, dest.getPiece());
	}
	
	@Test
	void testMultipleJumping() throws InvalidMoveException {
		specs = new BoardSpecification(8, 8, 1);
		board = new Board(specs);
		game = new Game(board, board.getFields());
		
		field = board.getFields()[8][1];
		Field dest = board.getFields()[7][8];
		Field field1 = board.getFields()[8][5];
		Field dest1 = board.getFields()[4][4];
		Field field2 = board.getFields()[1][2];
		Field dest2 = board.getFields()[6][6];
		
		piece = field.getPiece();
		Piece piece1 = field1.getPiece();
		Piece piece2 = field2.getPiece();
		
		for(int i = 0; i < 4; i++) 
			piece.moveUpRight();
		piece1.moveUpRight();
		piece1.moveUpRight();
		
		piece2.moveDownRight();
		piece2.moveDownRight();
		piece2.moveDownRight();
		
		
		assertEquals(piece2, dest.getPiece());
		assertEquals(null, dest1.getPiece());
		assertEquals(null, dest2.getPiece());
	}
	
	@Test
	void testJumpDownLeft() throws InvalidMoveException {
		specs = new BoardSpecification(8, 8, 1);
		board = new Board(specs);
		game = new Game(board, board.getFields());
		
		field = board.getFields()[8][1];
		Field dest = board.getFields()[3][6];
		Field field1 = board.getFields()[1][8];
		Field dest1 = board.getFields()[2][7];
		Renderer renderer = new Renderer(board);
		
		piece = field.getPiece();
		Piece piece1 = field1.getPiece();
		Piece piece2 = field.getPiece();
		
		piece2.moveUpRight();
		piece2.moveUpRight();
		piece2.moveUpRight();
		piece2.moveUpRight();
		piece2.moveUpRight();
		piece2.moveUpRight();
		piece1.moveDownLeft();
		
		assertEquals(piece1, dest.getPiece());
		assertEquals(null, dest1.getPiece());
		
	}
}
