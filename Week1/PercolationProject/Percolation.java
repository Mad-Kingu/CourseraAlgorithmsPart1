/* *****************************************************************************
 *  Name:              Alper Ayna
 *  Coursera User ID:  None
 *  Last modified:     22/08/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int[][] grid;
    private final WeightedQuickUnionUF uf;
    private int numberOfOpenSites;
    private final int size;

    // creates n-by-n grid, with all sites initially blocked
    // 0 is closed, 1 is open, fill all grid with 0's
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("N is lower or equal than 0.");
        size = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        numberOfOpenSites = 0;
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
            uf.union(i, n * n); // connect top line to n*n node
            uf.union(n * n - i - 1, n * n + 1); // connect bottom line to n*n+1 node
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("row or col value is out of range.");
        if (!isOpen(row, col)) {
            numberOfOpenSites++;
            grid[row - 1][col - 1] = 1;
            if (row - 1 != 0) {
                if (isOpen(row - 1, col))
                    uf.union((row - 1) * size + (col - 1), ((row - 1) - 1) * size + (col - 1));
            }
            if (col - 1 != 0) {
                if (isOpen(row, col - 1))
                    uf.union((row - 1) * size + (col - 1), (row - 1) * size + (col - 1) - 1);
            }
            if (col + 1 < size + 1) {
                if (isOpen(row, col + 1))
                    uf.union((row - 1) * size + (col - 1), (row - 1) * size + (col - 1) + 1);
            }
            if (row + 1 < size + 1) {
                if (isOpen(row + 1, col))
                    uf.union((row - 1) * size + (col - 1), ((row - 1) + 1) * size + (col - 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("row or col value is out of range.");
        // 1 is open
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("row or col value is out of range.");
        if (!isOpen(row, col))
            return false;
        return uf.find((row - 1) * size + (col - 1)) == uf.find(size * size); // 2 is full
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(size * size) == uf.find(size * size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(Integer.parseInt(args[0]));

        while (!percolation.percolates()) {
            int random = StdRandom.uniform(0, percolation.size * percolation.size);
            percolation.open(random / percolation.size + 1, random % percolation.size + 1);
        }
        System.out.println(percolation.numberOfOpenSites());
    }
}
