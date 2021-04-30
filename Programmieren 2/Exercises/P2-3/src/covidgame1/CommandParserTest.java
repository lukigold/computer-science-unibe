package covidgame1;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.Test;

class CommandParserTest {

	@Test
	public void extractWords() {
		String program = "Hello my name is John.";
		CommandParser parser = new CommandParser(program);
		assertTrue(parser.getWords().get(0).equals("Hello"));
		assertTrue(parser.getWords().get(1).equals("my"));
		assertTrue(parser.getWords().get(2).equals("name"));
		assertTrue(parser.getWords().get(3).equals("is"));
		assertTrue(parser.getWords().get(4).equals("John."));
	}
	
	@Test
	public void parse() {
		String program = "left 10 right 100 down 13 up 22";
		CommandParser parser = new CommandParser(program);
		Queue<Command> commands = new LinkedList<Command>();
		commands = parser.parse(parser.getWords());
		assertTrue(commands.remove().getLength() == 10);
		assertTrue(commands.remove().getLength() == 100);
		assertTrue(commands.remove().getLength() == 13);
		assertTrue(commands.remove().getLength() == 22);
	}
	
	@Test
	public void parse2() {
		String program = "left 10 dasdsd dsad right 100 dsads dsadsa down 13 up 22";
		CommandParser parser = new CommandParser(program);
		Queue<Command> commands = new LinkedList<Command>();
		commands = parser.parse(parser.getWords());
		assertTrue(commands.remove().getLength() == 10);
		assertTrue(commands.remove().getLength() == 100);
		assertTrue(commands.remove().getLength() == 13);
		assertTrue(commands.remove().getLength() == 22);
	}
}
