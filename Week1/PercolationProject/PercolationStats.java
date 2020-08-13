/* *****************************************************************************
 *  Name:              Alper Ayna
 *  Coursera User ID:  None
 *  Last modified:     22/08/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] trialArray;
    private final int trials;
    private final double mean;
    private final double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n or trials value must be bigger than 0.");

        trialArray = new double[trials];
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int random = StdRandom.uniform(0, n * n);
                percolation.open(random / n + 1, random % n + 1);
            }
            trialArray[i] = ((double) percolation.numberOfOpenSites() / (n * n)); // results
        }
        mean = mean();
        stddev = stddev();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialArray);
    }

    // sample standart deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialArray);
    }

    // low endpoint of %95 confidence interval
    public double confidenceLo() {
        return mean - ((CONFIDENCE_95 * stddev) / Math.sqrt(trials));
    }

    // high endpoint of %95 confidence interval
    public double confidenceHi() {
        return mean + ((CONFIDENCE_95 * stddev) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]),
                                                                 Integer.parseInt(args[1]));

        System.out.println("mean = " + percolationStats.mean);
        System.out.println("stddev = " + percolationStats.stddev);
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo()
                                   + ", " + percolationStats.confidenceHi() + "]");
    }
}
