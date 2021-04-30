package snakes;

public class MockDie implements IDie {

	/**
	 * Mock Die for testing which always returns 4
	 */
	@Override
	public int roll() {
		return 4;
	}

}
