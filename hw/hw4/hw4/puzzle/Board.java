package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

    private int[][] tiles;
    private int size;
    private int BLANK = 0;
    private int estimatedDistanceToGoal = -1;

    public Board(int[][] tiles) {
        size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        validIndex(i, j);
        if (tiles[i][j] == BLANK) { //dumb
            return 0;
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    /**
     * See <a href="http://joshh.ug/neighbors.html">http://joshh.ug/neighbors.html</a>
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
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
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int res = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != i * size + j + 1) {
                    res += 1;
                }
            }
        }
        return res;
    }

    public int manhattan() {
        int res = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                int i0 = (tileAt(i, j) - 1) / size;
                int j0 = (tileAt(i, j) - 1) % size;
                res += Math.abs(i - i0) + Math.abs(j - j0);
            }
        }
        return res;
    }

    public int estimatedDistanceToGoal() {
        if (estimatedDistanceToGoal < 0) {
            estimatedDistanceToGoal = manhattan();
        }
        return estimatedDistanceToGoal;
    }

    @Override
    public boolean equals(Object y) {
        if(y == this){
            return true;
        }

        if(y == null){
            return false;
        }

        if(y.getClass() != this.getClass()){
            return false;
        }

        Board other = (Board) y;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

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

    private void validIndex(int i, int j) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
}
