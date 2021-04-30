package checkers;

/**
 * Visualizes Board and prints it to console.
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class Renderer {

    Board board;

    public boolean invariant(){
        return board!=null;
    }

    /**
     * Creates a Renderer object.
     * @param board the board to be rendered.
     */
    public Renderer(Board board){
        this.board = board;
        assert invariant();
    }

    /**
     * Renders the Board to System.out.
     */
    public void render(){
        board.toConsole();
        System.out.println();
    }
}
