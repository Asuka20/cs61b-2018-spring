package lab11.graphs;

import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private ArrayDeque<Integer> queue = new ArrayDeque<>();

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(t) - maze.toX(v)) + Math.abs(maze.toY(t) - maze.toY(v));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        int min = Integer.MAX_VALUE;
        int res = queue.peek();
        for (int w: queue) {
            if (h(w) < min) {
                min = h(w);
                res = w;
            }
        }
        return res;
    }

    /** Performs an A star search from vertex s. */
    private void astar(int v) {
        queue.add(v);
        while (!queue.isEmpty()) {
            v = findMinimumUnmarked();
            queue.remove(v);
            marked[v] = true;
            announce();

            if (v == t) {
                targetFound = true;
            }

            if (targetFound) {
                return;
            }

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

