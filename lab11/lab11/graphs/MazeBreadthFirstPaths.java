package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.util.Deque;
import java.util.LinkedList;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX,sourceY);
        t = maze.xyTo1D(targetX,targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Deque<Integer> dq = new LinkedList<>();
        dq.addFirst(s);
        while (!dq.isEmpty()) {
            int cur = dq.removeFirst();

            marked[cur] = true;
            announce();
            if (cur == t)
                return;
            for (int a : maze.adj(cur)) {
                if (marked[a] != true) {
                    dq.addLast(a);
                    edgeTo[a] = cur;
                    announce();
                    distTo[a] = distTo[cur] + 1;
                }
            }

        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

