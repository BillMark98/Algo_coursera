import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // array save the threshold for each experiment
    private static final double CONFIDENCE_95 = 1.96;

    private final double meanThres;
    private double stdDev;
    private final int trialTimes;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("illegal size or trials argument");
        }
        double[] thresArr = new double[trials];
        trialTimes = trials;
        int opCount;
        for (int term = 0; term < trials; term++) {
            opCount = 0;
            Percolation percolate = new Percolation(n);
            while (!percolate.percolates()) {
                // generate a random from 0 to n^2 - 1
                // int index = StdRandom.uniform(0, n * n);  // uniform deprecated
                int index = StdRandom.uniformInt(0, n * n);
                // convert it to (row, col) coordinate
                int col = index % n + 1;
                int row = index / n + 1;
                // generate a new random point until it is closed
                while (percolate.isOpen(row, col)) {
                    index = StdRandom.uniformInt(0, n * n);
                    col = index % n + 1;
                    row = index / n + 1;
                }
                percolate.open(row, col);
                opCount++;
            }
            thresArr[term] = (double) opCount / (n * n);
            // meanThres += thresArr[term];
        }
        // calculate mean
        // meanThres = meanThres / (trials);
        meanThres = StdStats.mean(thresArr);
        // calculate stderr
        // double sum = 0.0;
        // for (double opC : thresArr) {
        //     sum += Math.pow((opC - meanThres), 2);
        // }
        if (trials == 1) {
            stdDev = Double.NaN;
        }
        else {
            // stdDev = Math.sqrt(sum / (trials - 1.0));
            stdDev = StdStats.stddev(thresArr);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanThres;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stdDev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return meanThres - CONFIDENCE_95 * stdDev / (Math.sqrt(trialTimes));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return meanThres + CONFIDENCE_95 * stdDev / (Math.sqrt(trialTimes));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Usage: java PercolationStats n T");
        }
        int n = Integer.parseInt(args[0]);
        int trial = Integer.parseInt((args[1]));
        PercolationStats percoStat = new PercolationStats(n, trial);
        StdOut.println("mean                    = " + percoStat.mean());
        StdOut.println("stddev                  = " + percoStat.stddev());
        StdOut.println("95% confidence interval = " + "[" + percoStat.confidenceLo() + ", " +
                               percoStat.confidenceHi() + "]");
    }

}
