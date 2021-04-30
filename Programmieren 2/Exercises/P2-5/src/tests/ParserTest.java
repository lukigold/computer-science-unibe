package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import checkers.*;

class ParserTest {

	@Test
	public void testInputFromFile() {
		String filename = "default.txt";
		String[] data = Parser.inputFromFile(filename);
		assertTrue(data[0].equals("8"));
		assertTrue(data[1].equals("8"));
		assertTrue(data[2].equals("3"));
	}
	
	@Test
	public void testInputFromFile2() {
		String filename = "logic_error.txt";
		String[] data = Parser.inputFromFile(filename);
		assertTrue(data[0].equals("8"));
		assertTrue(data[1].equals("8"));
		assertTrue(data[2].equals("4"));
	}
	
	@Test
	public void ValidInput() {
		String filename = "default.txt";
		String[] data = Parser.inputFromFile(filename);
		boolean res = Parser.isValidInput(data);
		assertTrue(res);
	}
	
	@Test
	public void InvalidInputBadlyFormatted() {
		String filename = "badly_formatted.txt";
		String[] data = Parser.inputFromFile(filename);
		boolean res = Parser.isValidInput(data);
		assertFalse(res);
	}
	
	@Test
	public void InvalidInputMissingParameter() {
		String filename = "missing_parameter.txt";
		String[] data = Parser.inputFromFile(filename);
		boolean res = Parser.isValidInput(data);
		assertFalse(res);
	}
	
	@Test
	public void InvalidInputLogicError() {
		String filename = "logic_error.txt";
		String[] data = Parser.inputFromFile(filename);
		boolean res = Parser.isValidInput(data);
		assertFalse(res);
	}
	
	@Test
	public void InvalidInputThrowsException() {
		String filename = "logic_error.txt";
		String[] data = Parser.inputFromFile(filename);
		Exception exception = assertThrows(InvalidInputException.class, () -> {
			Parser.convertToSpecs(data);
		});
		String expectedMessage = "Your input is invalid.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
}
