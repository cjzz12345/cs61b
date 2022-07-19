package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private int[][] tiles;
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0;i < this.tiles.length;i++)
            for (int j = 0;j < this.tiles.length;j++) {
                this.tiles[i][j] = tiles[i][j];
            }
    }

    public int tileAt(int i ,int j) {
        return tiles[i][j];
    }

    public int size() {
        return tiles.length;
    }

    public int hamming() {
        int sum = 0;
        for (int i = 0;i < this.tiles.length;i++)
            for (int j = 0;j < this.tiles.length;j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * size() + j + 1 )
                    sum++;
            }
        return sum;
    }

    public int manhattan() {
        return 0;
    }

    @Override
    public boolean equals(Object y) {
        Board o = (Board) y;
        for (int i = 0;i < this.tiles.length;i++)
            for (int j = 0;j < this.tiles.length;j++) {
                if (this.tiles[i][j] != o.tileAt(i,j))
                    return false;
            }
        return true;
    }
    @Override
    public int estimatedDistanceToGoal() {
        return hamming();
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
