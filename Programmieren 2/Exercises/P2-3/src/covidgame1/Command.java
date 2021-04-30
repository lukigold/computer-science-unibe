package covidgame1;

/**
 * Each command has length attribute and a direction.
 * length cannot be negative and direction must be valid (either left, right, down or up)
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Command {

    protected int length;
    protected String direction;
    
    private boolean invariant() { return length >= 0; }
    public Command (int length, String direction) {
        assert(direction.equals("left") || direction.equals("right") || direction.equals("up") || direction.equals("down"));
    	
    	this.length = length;
        this.direction = direction;
        
		assert invariant();
    }
    
    public int getLength() {
    	return this.length;
    }
    
    public String getDirection() {
    	return this.direction;
    }
}