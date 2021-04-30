package covidgame1;

/**
 * Represents player.
 * Keeps track of current position in environment.
 * Basic functionalities such as moving in certain directions in Environment.
 * X and Y coordinates cannot be negative or greater than Environment size.
 * 
 * @author Kevin St�ckli
 * @author Lukas Ingold
 *
 */
public class Hooman {
	int x;
	int y;
	boolean infected;
	
	private boolean invariant() {
		return x>=0 && y>= 0 && x<=Environment.SIZE && y<=Environment.SIZE;
	}
	public Hooman(int x, int y, boolean infected) {
		this.x = x;
		this.y = y;
		this.infected = infected;
		assert invariant();
	}
	
	public void setX(int x) {
		this.x = x;
		assert invariant();
	}
	
	public void setY(int y) {
		this.y = y;
		assert invariant();
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public boolean isInfected() { 
		return infected; 
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	public void moveLeft(int moves) { 
		assert moves >= 0;
		this.x = this.x - moves; 
		assert invariant();
	}
	
	public void moveRight(int moves) {
		assert moves >= 0;
		this.x = this.x + moves;
		assert invariant();
	}
	
	public void moveUp(int moves) {
		assert moves >= 0;
		this.y = this.y - moves;
		assert invariant();
	}
	
	public void moveDown(int moves) {
		assert moves >= 0;
		this.y = this.y + moves;
		assert invariant();
	}

}
