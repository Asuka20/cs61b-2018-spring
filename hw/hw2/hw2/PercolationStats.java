package hw2;
import java.util.Random;

public class PercolationStats {
    private int[] res;
    private int T;
    private Random random = new Random();

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        res = new int[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation tmpTest = pf.make(N);
            while (!tmpTest.percolates()) {
                tmpTest.open(random.nextInt(N), random.nextInt(N));
            }
            res[i] = tmpTest.numberOfOpenSites();
        }
    }

    public double mean() {
        int sum = 0;
        for (int i: res) {
            sum += i;
        }
        return (double) sum / T;
    }

    public double stddev() {
        int sum = 0;
        for (int i: res) {
            sum += (i - mean()) * (i - mean());
        }
        return (double) sum / (T - 1);
    }
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev() / T);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev() / T);
    }
}
