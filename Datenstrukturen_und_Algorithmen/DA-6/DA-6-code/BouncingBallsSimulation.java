import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

/**
 * Implements a bouncing ball simulation.
 */
public class BouncingBallsSimulation extends Component implements Runnable {

    LinkedList<Ball> balls;   // List of balls.
    Image img;                // Image to display balls.
    int w, h;                 // Width and height of image.
    Graphics2D gi;            // Graphics object to draw balls.
    float r;                  // Radius of balls.
    int n;                    // Number of balls.
    Thread thread;            // Thread that runs simulation loop.
    twoDimHashTable tdh;

    class twoDimHashTable {

        int size; // m
        int width, height; // w
        ArrayList<ArrayList<LinkedList<Ball>>> data = new ArrayList<ArrayList<LinkedList<Ball>>>();
        int numOfBalls = 0;

        public twoDimHashTable(int size, int width, int height) {
            this.size = size;
            this.width = width;
            this.height = height;

            // initialize the hash table
            for (int i = 0; i < width; i++) {

                ArrayList<LinkedList<Ball>> tmp = new ArrayList<LinkedList<Ball>>();
                for (int j = 0; j < height; j++) {
                    tmp.add(j, new LinkedList<Ball>());
                }

                this.data.add(i, tmp);
            }
        }

        public void add(Ball ball) {
            this.numOfBalls++;
//            int idx = (int) Math.floor(ball.x * this.size / this.width);
            int idx = (int) ball.x * this.size / this.width;
//            int idy = (int) Math.floor(ball.y * this.size / this.height);
            int idy = (int) ball.y * this.size / this.height;

            this.data.get(idx).get(idy).addFirst(ball);
        }

        public void remove(Ball ball) {

//            int idx = (int) Math.floor(ball.x * this.size / this.width);
            int idx = (int) ball.x * this.size / this.width;
//            int idy = (int) Math.floor(ball.y * this.size / this.height);
            int idy = (int) ball.y * this.size / this.height;

            LinkedList<Ball> listInTable = this.data.get(idx).get(idy);

            ListIterator<Ball> it = listInTable.listIterator();
            while (it.hasNext()) {
                Ball current = it.next();
                if (current.equals(ball)) {
                    this.numOfBalls--;
                    it.remove();
                    return;
                }
            }
        }

        public Ball removeLast(int idx, int idy) {

            LinkedList<Ball> listInTable = this.data.get(idx).get(idy);
            if (!listInTable.isEmpty()) {
                this.numOfBalls--;
                Ball b = listInTable.removeLast();
                return b;
            }

            return null;
        }

        int[] getNeighbours(int idx, int idy) {
            int[] neighbours = new int[18];
            neighbours[0] = idx - 1;
            neighbours[1] = idy - 1;

            neighbours[2] = idx - 1;
            neighbours[3] = idy;

            neighbours[4] = idx - 1;
            neighbours[5] = idy + 1;

            //
            neighbours[6] = idx;
            neighbours[7] = idy - 1;

            neighbours[8] = idx;
            neighbours[9] = idy;

            neighbours[10] = idx;
            neighbours[11] = idy + 1;

            //
            neighbours[12] = idx + 1;
            neighbours[13] = idy - 1;

            neighbours[14] = idx + 1;
            neighbours[15] = idy;

            neighbours[16] = idx + 1;
            neighbours[17] = idy + 1;

            return neighbours;
        }
    }

    public static void main(String[] args) {
        BouncingBallsSimulation simulation = new BouncingBallsSimulation(1000,1000, 1000, 4.f, 0.2f);
    }

    /**
     * Initializes the simulation.
     *
     * @param w width of simulation window.
     * @param h height of simulation window.
     * @param n number of balls.
     * @param r radius of balls.
     * @param v initial velocity of balls.
     */
    public BouncingBallsSimulation(int w, int h, int n, float r, float v)
    {
        this.r = r;
        this.n = n;
        this.w = w;
        this.h = h;
        this.tdh = new twoDimHashTable(n, w, h);

        // Initialize balls by distributing them randomly.
        balls = new LinkedList<Ball>();
        for(int i=0; i<n; i++)
        {
            float vx = 2*(float)Math.random()-1;
            float vy = 2*(float)Math.random()-1;
            float tmp = (float)Math.sqrt((double)vx*vx+vy*vy);
            vx = vx/tmp*v;
            vy = vy/tmp*v;
            int color = (i < n / 100 ) ? 1 : 0;
            Ball b = new Ball(r+(float)Math.random()*(w-2*r), r+(float)Math.random()*(h-2*r), vx, vy, r, color);
            balls.add(b);
            tdh.add(b);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    /**
     * Paint the window that displays the simulation. This method is called
     * automatically by the Java window system as a response to the call to
     * repaint() in the run() method below.
     */
    public void paint(Graphics g)
    {
        gi.clearRect(0, 0, w, h);

        Iterator<Ball> it = balls.iterator();
        while(it.hasNext())
        {
            Ball ball = it.next();
            gi.setColor(ball.color == 1 ? Color.red : Color.black);
            gi.fill(new Ellipse2D.Float(ball.x-r, ball.y-r, 2*r, 2*r));
        }

//        for (int i = 0; i < this.tdh.width; i++) {
//            for (int j = 0; j < this.tdh.height; j++) {
//
//                if (!this.tdh.data.get(i).get(j).isEmpty()) {
////                    ListIterator<Ball> iitt = this.tdh.data.get(i).get(j).listIterator();
//
////                    while (iitt.hasNext()) {
////                        Ball ball = iitt.next();
//                        Ball ball = this.tdh.data.get(i).get(j).getFirst();
//                        gi.setColor(ball.color == 1 ? Color.red : Color.black);
//                        gi.fill(new Ellipse2D.Float(ball.x - r, ball.y - r, 2 * r, 2 * r));
////                    }
//                }
//            }
//        }
        g.drawImage(img, 0, 0, null);
    }

    /**
     * Starts the simulation loop.
     */
    public void start()
    {
        img = createImage(w, h);
        gi = (Graphics2D)img.getGraphics();
        gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        thread = new Thread(this);
        thread.run();
    }

    /**
     * The simulation loop.
     */
    public void run()
    {
        // Set up timer.
        int c = 0;
        Timer timer = new Timer();
        timer.reset();

        // Loop forever (or until the user closes the main window).
        while(true)
        {
            // Run one simulation step.
//            Iterator<Ball> it = balls.iterator();

            for (int i = 0; i < this.tdh.width; i++) {
                for (int j = 0; j < this.tdh.height; j++) {

                        Ball ball = this.tdh.removeLast(i, j);

                        if (ball != null) {
                            ball.move();

                            // Handle collisions with boundaries.
                            if(ball.doesCollide((float)w,0.f,-1.f,0.f))
                                ball.resolveCollision((float)w,0.f,-1.f,0.f);
                            if(ball.doesCollide(0.f,0.f,1.f,0.f))
                                ball.resolveCollision(0.f,0.f,1.f,0.f);
                            if(ball.doesCollide(0.f,(float)h,0.f,-1.f))
                                ball.resolveCollision(0.f,(float)h,0.f,-1.f);
                            if(ball.doesCollide(0.f,0.f,0.f,1.f))
                                ball.resolveCollision(0.f,0.f,0.f,1.f);

                            this.tdh.add(ball);

                            int[] neighbours = this.tdh.getNeighbours(i, j);
//                            for (int k = 0; k < 1; k += 2) {
//                                System.out.printf("x: %d, y: %d\n", neighbours[k], neighbours[k+1]);
//                                System.out.printf("# balls: %d\n", this.tdh.numOfBalls);
//
//                                if (neighbours[k] >= 0 &&
//                                        neighbours[k] <= this.w &&
//                                        neighbours[k+1] >= 0 &&
//                                        neighbours[k+1] <= this.h) {
//
//                                    Ball ball2 = this.tdh.removeLast(neighbours[k], neighbours[k + 1]);
//                                    if (ball2 != null) {
//
//                                        if (ball.doesCollide(ball2)) {
//                                            System.out.println("collision");
//                                            ball.resolveCollision(ball2);
//                                        }
//
//                                        this.tdh.add(ball2);
//                                    }
//                                }
//                            }
                        }
                }
            }


            // Trigger update of display.
            repaint();

            // Print time per simulation step.
            c++;
            if(c==10)
            {
                System.out.printf("Timer per simulation step: %fms\n", (float)timer.timeElapsed()/(float)c);
                timer.reset();
                c = 0;
            }
        }
    }
}
