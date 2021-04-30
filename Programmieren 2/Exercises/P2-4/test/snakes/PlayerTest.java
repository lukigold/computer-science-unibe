package snakes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {
    Player Kevin;
    Player Lukas;
    Game game;

    Queue<Player> players;

    int SIZE = 10;
    int DIESIDES = 6;

    @BeforeEach
    void Setup(){
        Kevin = new Player("Kevin");
        Lukas = new Player("Lukas");

        Queue<Player> players = new LinkedList<Player>();
        players.add(Kevin);
        players.add(Lukas);
        game = new Game(SIZE, players, DIESIDES);
    }
    @Test
    public void TestPosition(){

        IDie die = mock(IDie.class);
        when(die.roll()).thenReturn(1);

        assertEquals(1, Kevin.position());
        assertEquals(1, Lukas.position());

        game.play(die);

        assertEquals(10, Kevin.position());
        assertEquals(8, Lukas.position());
    }
    @Test
    public void TestJoinGame(){
        assertEquals("[1<Kevin><Lukas>][2][3][4][5][6][7][8][9][10]", game.toString());
        Player legend = new Player("TheLegend27");
        legend.joinGame(game);
        assertEquals("[1<Kevin><Lukas><TheLegend27>][2][3][4][5][6][7][8][9][10]", game.toString());
    }
    @Test
    public void TestMoveForward(){
        assertEquals(1, Kevin.position());
        assertEquals(1, Lukas.position());
        Lukas.moveForward(3);
        assertEquals(1, Kevin.position());
        assertEquals(4, Lukas.position());
    }
    @Test
    public void TestToString(){
        assertEquals("Kevin", Kevin.toString());
        assertEquals("Lukas", Lukas.toString());
    }
    @Test
    public void TestWins(){
        assertFalse(Kevin.wins());
        assertFalse(Lukas.wins());
        IDie die = mock(IDie.class);
        when(die.roll()).thenReturn(1);
        game.play(die);
        assertTrue(Kevin.wins());
        assertFalse(Lukas.wins());
    }
    @Test
    public void TestSwap(){
        assertEquals(1, Kevin.position());
        Kevin.swap(game.getSquare(9));
        assertEquals(game.getSquare(9), game.getSquare(Kevin.position()));
        assertEquals(9, Kevin.position());
    }
    @Test
    public void TestSetSquare(){

        Kevin.setSquare(game.getSquare(8));
        assertEquals("[8]",Kevin.getSquare().toString());
        Lukas.setSquare(game.getSquare(4));
        assertEquals("[4]", Lukas.getSquare().toString());

    }
}
