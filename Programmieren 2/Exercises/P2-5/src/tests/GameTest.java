package tests;

import checkers.*;
import checkers.Fields.Field;
import checkers.Token.BlackToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    BoardSpecification spec;
    Board board;
    Game game;
    Field[][] fields;

    @BeforeEach
    void setup(){
        spec = new BoardSpecification(8,8,3);
        board = new Board(spec);
        game = new Game(board, board.getFields());
        fields = board.getFields();
    }

    @Test
    public void SimpleMoveTest() throws InvalidMoveException{
        //Simple move
        Piece piece = fields[3][2].getPiece();
        MoveCommand move = new MoveCommand(piece , "downRight");

        assertEquals(2,piece.getField().getX());
        assertEquals(3,piece.getField().getY());

        move.execute();

        assertEquals(3,piece.getField().getX());
        assertEquals(4,piece.getField().getY());

        //Simple move whiteToken
        Piece piece2 = fields[6][1].getPiece();
        MoveCommand move2 = new MoveCommand(piece2, "upRight");

        assertEquals(1,piece2.getField().getX());
        assertEquals(6,piece2.getField().getY());

        move2.execute();

        assertEquals(2,piece2.getField().getX());
        assertEquals(5,piece2.getField().getY());


        //simpleJumpOver
        Piece piece3 = fields[4][3].getPiece();
        MoveCommand jump = new MoveCommand(piece3, "downLeft");

        assertEquals(3,piece3.getField().getX());
        assertEquals(4,piece3.getField().getY());

        jump.execute();

        assertEquals(1,piece3.getField().getX());
        assertEquals(6,piece3.getField().getY());
        assertEquals(null, fields[5][2].getPiece());

    }

    @Test
    void InvalidMovesTest() {
        Piece piece = fields[6][1].getPiece();
        MoveCommand move = new MoveCommand(piece , "upLeft");
        MoveCommand move1 = new MoveCommand(piece , "downRight");
        MoveCommand move2 = new MoveCommand(piece , "downLeft");
        MoveCommand move3 = new MoveCommand(piece , "heyyyyy");

        assertThrows(InvalidMoveException.class, () -> move.execute(), "test invalid move.");
        assertThrows(InvalidMoveException.class, () -> move1.execute(), "test invalid move.");
        assertThrows(InvalidMoveException.class, () -> move2.execute(), "test invalid move.");
        assertThrows(InvalidMoveException.class, () -> move3.execute(), "test invalid move direction.");
    }
    void setFieldsToNull(){

        for(int i = 0; i < fields.length; i++) {
            for(int j = 0; j < fields[i].length; j++) {
                fields[i][j] = null;
            }
        }
    }
    void GenerateNoMovePossible(){
       fields[1][2].leave();
       fields[1][4].leave();
       fields[1][6].leave();
       fields[1][8].leave();

       fields[2][1].leave();
       fields[2][3].leave();
       fields[2][5].leave();
       fields[2][7].leave();

       fields[3][2].leave();
       fields[3][4].leave();
       fields[3][6].leave();
       fields[3][8].leave();

       fields[5][4].setPiece(new Piece(fields[5][4], new BlackToken(), board));

    }
    @Test
    void WinTest(){
        Player player = new Player("Thomas", new BlackToken(),game);
        assertTrue(player.canMakeMove(), "board is full and player should be able to make move.");

        setFieldsToNull();

        assertFalse(player.canMakeMove(), "The Field contains nothing so there is no possible move and the player should win.");

    }
    @Test
    void OnlyBlockedPiecesTest() throws InvalidMoveException{
        Player player = new Player("Tim", new BlackToken(), game);
        assertTrue(player.canMakeMove(), "Player should be able to make a move, since the board is full.");

        GenerateNoMovePossible(); // makes all black pieces go away but one at x:5 y:4 stay it is blocked by the white pieces.

        assertFalse(player.canMakeMove(), "The Player cannot make any move, since his piece is blocked");

    }
}