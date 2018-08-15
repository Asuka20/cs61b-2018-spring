package lab11.graphs;

import java.util.ArrayDeque;

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
    private boolean cycleFound = false;
    private Maze maze;

    private int cycle;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        dfsCycleFind(s);
        showCycle(cycle);
    }

    private void dfsCycleFind(int v) {
        if (cycleFound) {
            return;
        }

        marked[v] = true;

        for (int w : maze.adj(v)) {
            if (marked[w] && w != edgeTo[v]) {
                edgeTo[w] = v;
                cycle = w;
                cycleFound = true;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfsCycleFind(w);
                if (cycleFound) {
                    return;
                }
            }
        }
    }

    private void showCycle(int c) {
        if (!cycleFound) {
            for (int i = 0; i < maze.V(); i++) {
                if (marked[i]) {
                    edgeTo[i] = i;
                }
            }
            announce();
            return;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.add(c);
        int v = edgeTo[c];
        while (v != c) {
            stack.push(v);
            v = edgeTo[v];
        }

        for (int i = 0; i < maze.V(); i++) {
            edgeTo[i] = i;
        }

        while (!stack.isEmpty()) {
            v = stack.pop();
            edgeTo[v] = c;
            c = v;
        }
        announce();
    }
    // Helper methods go here
}

