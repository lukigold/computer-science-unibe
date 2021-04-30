package covidgame1;

import java.util.*;

/**
 * Creates field of areas.
 * Keeps track of where player is and which areas are infected.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Environment {
    public final static int SIZE = 48;
    private static boolean alreadyExecuted = false;
    public final Area[][] areas;
    protected static Hooman player = new Hooman(0, 0, false);
	private static ArrayList<Integer> spotsX = new ArrayList<Integer>();
	private static ArrayList<Integer> spotsY = new ArrayList<Integer>();

    /**
     * Creates a new environment with the given areas.
     *
     * @param areas The area tiles. All dimensions should be equal to {@link #SIZE}.
     */
    private Environment(Area[][] areas) {
        assert areas.length == SIZE;
        assert Arrays.stream(areas).allMatch(t -> t.length == SIZE);
        this.areas = areas;
    }

    /**
     * Creates a new empty environment.
     *
     * @return An empty environment
     * @throws UnsupportedOperationException if Environment is not empty
     */
    public static Environment empty(){
    	
        Area[][] empty = new Area[SIZE][SIZE];
        Arrays.stream(empty).forEach(a -> Arrays.fill(a, Area.SAFE));
        //adds player
        empty[player.getX()][player.getY()] = Area.CURRENT;
        //adds Covid
		CovidFields(empty, 20);
        return new Environment(empty);
    }

	/**
	 * Generates random covid infected fields.
	 * @param areas the areas where random fields should appear.
	 * @param amount of covid infected fields.
	 */
	private static void CovidFields(Area[][] areas, int amount){
		assert(amount >= 0);
		int CovidCases = amount;

		for(int k = 1; k<=CovidCases; k++){

			Random rnd = new Random();
			int p = 1 + rnd.nextInt(SIZE-1);
			int t = 1 + rnd.nextInt(SIZE-1);
			areas[p][t] = Area.COVID;
			spotsX.add(p);
			spotsY.add(t);
		}
	}

	private static Environment updater(){

		Area[][] empty = new Area[SIZE][SIZE];
		Arrays.stream(empty).forEach(a -> Arrays.fill(a, Area.SAFE));

		empty[player.getX()][player.getY()] = Area.CURRENT;

		for (int m = 0; m <= spotsX.size()-1; m++) {
			empty[spotsX.get(m)][spotsY.get(m)] = Area.COVID;
		}
		return new Environment(empty);

	}

	/**
	 *	Checks if given Position is covid infected.
	 * @param positionX to check.
	 * @param positionY to check.
	 * @return true if it is infected false if not
	 */
	public boolean isCovidSpot(int positionX, int positionY) {
		assert(positionX<=SIZE && SIZE>=positionY);
		assert(positionX>=0&&0<=positionY);
		return areas[positionX][positionY] == Area.COVID;
	}

	/**
     * Parse the given program and evaluate it. Render the trail as description the problem description and return
     * an environment corresponding to the evaluated path. If a command exceeds boundaries of Environment, the command will be ignored.
     * Command must be given in following pattern: "direction value" (the space between direction and value is essential). The following program would be valid:
     * 
     * right 10
     * left 5
     * down 7
     * up 3
     * 
     * Between each command, either a space or an enter is necessary.
     *
     * @param program An input program according to the specification. May contain illegal commands. Should not be
     *                {@code null}.
     * @return A parsed environment
     */
    public static Environment createFrom(String program) {
        assert program != null;
        player.setX(0);
        player.setY(0);
        
        Environment env = Environment.updater();
        CommandParser parser = new CommandParser(program);

        Queue<Command> commands = parser.parse(parser.getWords());
        if(!player.isInfected()) {
			for (Command command : commands) {
				int value = command.getLength();
				switch (command.getDirection()) {
					case "left":
						if (player.getX() - value >= 0) {
							env.areas[player.getX()][player.getY()] = Area.TRAIL;
							for (int j = 1; j <= value; j++) {
								player.moveLeft(1);
								if(env.isCovidSpot(player.getX(), player.getY())){
									player.setInfected(true);
									break;
								}
								else{
									player.setInfected(false);
									env.areas[player.getX()][player.getY()] = Area.TRAIL;
								}
							}
							env.areas[player.getX()][player.getY()] = Area.CURRENT;
						}
						break;
					case "right":
						if (player.getX() + value < SIZE) {
							env.areas[player.getX()][player.getY()] = Area.TRAIL;
							for (int j = 1; j <= value; j++) {
								player.moveRight(1);
								if(env.isCovidSpot(player.getX(), player.getY())){
									player.setInfected(true);
									break;
								}
								else{
									player.setInfected(false);
									env.areas[player.getX()][player.getY()] = Area.TRAIL;
								}
							}
							env.areas[player.getX()][player.getY()] = Area.CURRENT;
						}
						break;
					case "up":
						if (player.getY() - value >= 0) {
							env.areas[player.getX()][player.getY()] = Area.TRAIL;
							for (int j = 1; j <= value; j++) {
								player.moveUp(1);
								if(env.isCovidSpot(player.getX(), player.getY())){
									player.setInfected(true);
									break;
								}
								else{
									player.setInfected(false);
									env.areas[player.getX()][player.getY()] = Area.TRAIL;
								}
							}
							env.areas[player.getX()][player.getY()] = Area.CURRENT;
						}
						break;
					case "down":
						if (player.getY() + value < SIZE) {
							env.areas[player.getX()][player.getY()] = Area.TRAIL;
							for (int j = 1; j <= value; j++) {
								player.moveDown(1);
								if(env.isCovidSpot(player.getX(), player.getY())){
									player.setInfected(true);
									
									break;
								}
								else{
									player.setInfected(false);
									env.areas[player.getX()][player.getY()] = Area.TRAIL;
								}
							}
							env.areas[player.getX()][player.getY()] = Area.CURRENT;
						}
						break;
					default:
						break;
				}
			}
		}
        else {
        	if(!alreadyExecuted) {
        		env.areas[0][0] = Area.COVID;
        		System.out.println("Player Quarantined and sent Home!");
        		alreadyExecuted = true;
        	}
        	else {
        		env.areas[0][0] = Area.COVID;
        		System.out.println("You cannot move, you are in Quarantine!");
        	}
			
		}
        return env;
    }
}
