package snakes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import snakes.squares.FirstSquare;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FirstSquareTest {

    @Test
    public void TestEmptyIsOccupied() {
        Game game = mock(Game.class);
        Square testSquare;
        when(game.isValidPosition(anyInt())).thenReturn(true);
        testSquare = new FirstSquare(game, 1);

        assertFalse(testSquare.isOccupied(), "The Square should be empty.");

    }

    @Test
    public void TestFilledIsOccupied() {
        Game game = mock(Game.class);
        Square testSquare;
        when(game.isValidPosition(anyInt())).thenReturn(true);
        testSquare = new FirstSquare(game, 1);

        Player tom = new Player("tom");
        testSquare.enter(tom);

        assertTrue(testSquare.isOccupied(), "The Square is occupied.");
    }

    @Test
    public void TestEnterAndLeave() {
        Game game = mock(Game.class);
        Square testSquare;
        when(game.isValidPosition(anyInt())).thenReturn(true);
        testSquare = new FirstSquare(game, 1);

        Player pla = mock(Player.class);
        testSquare.enter(pla);
        assertTrue(testSquare.isOccupied(), "The Square should not be empty.");
        testSquare.leave(pla);
        assertFalse(testSquare.isOccupied(), "The Square should not contain any Player.");
    }

    @Test
    public void TestPlayerWithNoPlayers() {
        Game game = mock(Game.class);
        Square testSquare;
        when(game.isValidPosition(anyInt())).thenReturn(true);
        testSquare = new FirstSquare(game, 1);

        String test = testSquare.toString();

        assertEquals("[1]", test, "There should not be any Player on this Square.");
    }

    @Test
    public void TestPlayerWithPlayers() {
        Game game = mock(Game.class);
        Square testSquare;
        when(game.isValidPosition(anyInt())).thenReturn(true);
        testSquare = new FirstSquare(game, 1);

        Player tom = new Player("tom");
        Player sam = new Player("sam");
        Player kim = new Player("kim");
        Player leo = new Player("leo");
        Player jim = new Player("jim");

        testSquare.enter(tom);
        testSquare.enter(sam);
        testSquare.enter(kim);
        testSquare.enter(leo);
        testSquare.enter(jim);

        String test = testSquare.toString();

        assertEquals("[1<tom><sam><kim><leo><jim>]", test, "A few Players should be on this Square.");
    }

    @Test
    public void TestIsFirstSquare() {
        Game game = mock(Game.class);
        Square testSquare, test2;
        when(game.isValidPosition(anyInt())).thenReturn(true);
        testSquare = new FirstSquare(game, 1);

        test2 = new StandardSquare(game, 2);

        boolean test = testSquare.isFirstSquare();
        boolean test_2 = test2.isFirstSquare();

        assertEquals(true, test, "This is a FirstSquare so we expect true.");
        assertEquals(false, test_2, "This is not a FirstSquare so we expect false.");
    }

    @Test
    public void TestLandHereNOTGoHome() {
        Game game = mock(Game.class);
        when(game.isValidPosition(anyInt())).thenReturn(true);
        Square testSquare, test;

        testSquare = new FirstSquare(game, 1);
        when(testSquare.isOccupied()).thenReturn(false);

        test = testSquare.landHereOrGoHome();

        assertEquals(testSquare, test, "The Square is not occupied should Land here.");
    }
}
