package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private int[][] open;
    private int len;
    private int numOpen;
    public Percolation(int N) {
        sites = new WeightedQuickUnionUF(N * N);
        open = new int[N][N];
        len = N;
        numOpen = 0;
    }

    private int inMatrix(int row,int col) {
        return len * row + col;
    }
    public void open(int row,int col) {
        open[row][col] = 1;
        numOpen++;
        if (row >= 1) {
            if (isOpen(row - 1,col))
                sites.union(inMatrix(row,col),inMatrix(row - 1,col));
        }
        if (row < len - 1) {
            if (isOpen(row + 1,col))
                sites.union(inMatrix(row,col),inMatrix(row + 1,col));
        }
        if (col >= 1) {
            if (isOpen(row,col - 1))
                sites.union(inMatrix(row,col),inMatrix(row,col - 1));
        }
        if (col < len - 1) {
            if (isOpen(row,col + 1))
                sites.union(inMatrix(row,col),inMatrix(row,col + 1));
        }
    }

    public boolean isOpen(int row,int col) {
        return open[row][col] == 1;
    }

    public boolean isFull(int row,int col) {
        if (isOpen(row,col)) {
            for (int i = 0;i < len;i++) {
                if (isOpen(0,i) && sites.connected(inMatrix(row,col),inMatrix(0,i)))
                    return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean  percolates() {
        for (int i = 0;i < len;i++) {
            if (isFull(len - 1,i))
                return true;
        }
        return false;
    }



}
