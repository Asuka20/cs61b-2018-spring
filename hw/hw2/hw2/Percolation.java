package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    /** Just using two boxes, one with a virtual drain and
     *  one not, to deal with the 'isFull' and 'percolates'.
     */
    private WeightedQuickUnionUF box, uddebox;
    boolean[] openStatus;
    int N, numOfOpen;
    private int source, drain;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        box = new WeightedQuickUnionUF(N * N + 2);
        uddebox = new WeightedQuickUnionUF(N * N + 1);
        openStatus = new boolean[N * N];
        this.N = N;
        numOfOpen = 0;
        source = N * N;
        drain = source + 1;
        setDefaultStatus();
    }

    private void setDefaultStatus() {
        for (int i = 0; i < N * N; i++) {
            openStatus[i] = false;
        }
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    public void open(int row, int col) {
        checkBounds(row, col);
        if (isOpen(row, col)) {
            return;
        }
        if (row == 0) {
            box.union(xyTo1D(row, col), source);
            uddebox.union(xyTo1D(row, col), source);
        }
        if (row == N - 1) {
            box.union(xyTo1D(row, col), drain);
        }
        openStatus[xyTo1D(row, col)] = true;
        numOfOpen += 1;

        int cur = xyTo1D(row, col);
        tryConnect(row - 1, col, cur);
        tryConnect(row + 1, col, cur);
        tryConnect(row, col - 1, cur);
        tryConnect(row, col + 1, cur);
    }


    private void tryConnect(int row, int col, int index) {
        try {
            if (isOpen(row, col)) {
                box.union(xyTo1D(row, col), index);
                uddebox.union(xyTo1D(row, col), index);
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return openStatus[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return uddebox.connected(xyTo1D(row, col), source);
    }

    public int numberOfOpenSites() {
        return numOfOpen;
    }
    public boolean percolates() {
        return box.connected(source, drain);
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

}
