package snakes;

public abstract class SquareTest {
	protected Game game;
	protected Player jack, jill, joe;

	void initializePlayers() {
		jack = new Player("Jack");
		jill = new Player("Jill");
		joe = new Player("Joe");
	}

	void initializeGame(int size) {
		initializePlayers();
		game = new Game(size, new Player[] { jack, jill, joe });
	}

	public abstract void newGame();
}
