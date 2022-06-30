package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import org.junit.Test;

public class PercolationStats {
    private Percolation sites;
    private int num;
    private double[] sample;

    public PercolationStats(int N,int T,PercolationFactory pf) {
        num = T;
        sample = new double[T];
        int total,row,col;

        for (int i = 0;i < T;i++) {
            sites = pf.make(N);

            while (!sites.percolates()) {
                total = StdRandom.uniform(N * N);
                row = total / N;
                col = total % N;
                if (sites.isOpen(row,col)) {
                    sites.open(row,col);
                }

            }
            sample[i] = (double) sites.numberOfOpenSites() / (N * N);
        }


    }

    public double mean() {
        double sum = 0.0;
        for (double s : sample) {
            sum += s;
        }
        return sum / num;
    }

    public double stddev() {
        double std = 0.0;
        double mean = mean();
        for (double s : sample) {
            std += (s - mean) * (s - mean);
        }
        std /= num - 1;
        return Math.sqrt(std);
    }

    public double confidenceLow() {
        double mean = mean();
        double std = stddev();
        return mean - (1.96 * std / Math.sqrt(num));
    }

    public double confidenceHigh() {
        double mean = mean();
        double std = stddev();
        return mean + (1.96 * std / Math.sqrt(num));
    }

     public static void main(String[] args) {
        PercolationStats p = new PercolationStats(20,10,new PercolationFactory());
        System.out.println(p.mean()+"    "+p.confidenceHigh()+"       "+p.confidenceLow());
    }

}
