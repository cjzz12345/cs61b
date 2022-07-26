package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private Maze maze;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1,1);
        distTo[s] = 0;
    }

    @Override
    public void solve() {
        iscycle(s,s);
    }

    private int iscycle(int v,int parent) {
        marked[v] = true;
        announce();
        for (int a : maze.adj(v)) {
            if (marked[a] == true && a != parent) {
                edgeTo[v] = a;
                announce();
                edgeTo[parent] = v;
                announce();
                return a;
            } else if (marked[a] != true) {
                distTo[a] = distTo[v] + 1;
                int c = iscycle(a,v);
                if (c != -1) {
                    if (c == parent) {
                        edgeTo[parent] = v;
                        announce();
                        return -2;

                    }else if (c == -2){
                        return -2;
                    }else {
                        edgeTo[parent] = v;
                        announce();
                        return c;
                    }
                }
            }
        }
        return -1;
    }
    // Helper methods go here
}

