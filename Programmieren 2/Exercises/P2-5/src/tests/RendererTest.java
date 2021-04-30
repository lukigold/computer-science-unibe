package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import checkers.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class RendererTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testRenderer() {
        BoardSpecification boardSpecs = new BoardSpecification(8, 8, 3);
        Board board = new Board(boardSpecs);
        Game game = new Game(board, board.getFields());
        Renderer ps = new Renderer(board);
        ps.render();
        assertEquals(   "  x 1 2 3 4 5 6 7 8 \n" +
                "y # # # # # # # # # # \n" +
                "1 # _ b _ b _ b _ b # \n" +
                "2 # b _ b _ b _ b _ # \n" +
                "3 # _ b _ b _ b _ b # \n" +
                "4 # O _ O _ O _ O _ # \n" +
                "5 # _ O _ O _ O _ O # \n" +
                "6 # w _ w _ w _ w _ # \n" +
                "7 # _ w _ w _ w _ w # \n" +
                "8 # w _ w _ w _ w _ # \n" +
                "  # # # # # # # # # # \n\n" , outContent.toString());
    }
    @Test
    public void testRendererBigger(){
        BoardSpecification boardSpecs = new BoardSpecification(40, 40, 10);
        Board board = new Board(boardSpecs);
        Game game = new Game(board, board.getFields());
        Renderer ps = new Renderer(board);
        ps.render();
        assertEquals(   "  x 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \n" +
                "y # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # \n" +
                "1 # _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b # \n" +
                "2 # b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ # \n" +
                "3 # _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b # \n" +
                "4 # b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ # \n" +
                "5 # _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b # \n" +
                "6 # b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ # \n" +
                "7 # _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b # \n" +
                "8 # b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ # \n" +
                "9 # _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b # \n" +
                "10 # b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ b _ # \n" +
                "11 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "12 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "13 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "14 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "15 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "16 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "17 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "18 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "19 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "20 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "21 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "22 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "23 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "24 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "25 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "26 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "27 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "28 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "29 # _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O # \n" +
                "30 # O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ O _ # \n" +
                "31 # _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w # \n" +
                "32 # w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ # \n" +
                "33 # _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w # \n" +
                "34 # w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ # \n" +
                "35 # _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w # \n" +
                "36 # w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ # \n" +
                "37 # _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w # \n" +
                "38 # w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ # \n" +
                "39 # _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w # \n" +
                "40 # w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ w _ # \n" +
                "  # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # \n\n" , outContent.toString());
    }
    @Test
    public void testRendererMIN() {
        BoardSpecification boardSpecs = new BoardSpecification(6, 6, 2);
        Board board = new Board(boardSpecs);
        Game game = new Game(board, board.getFields());
        Renderer ps = new Renderer(board);
        ps.render();
        assertEquals(   "  x 1 2 3 4 5 6 \n" +
                "y # # # # # # # # \n" +
                "1 # _ b _ b _ b # \n" +
                "2 # b _ b _ b _ # \n" +
                "3 # _ O _ O _ O # \n" +
                "4 # O _ O _ O _ # \n" +
                "5 # _ w _ w _ w # \n" +
                "6 # w _ w _ w _ # \n" +
                "  # # # # # # # # \n\n" , outContent.toString());
    }

}