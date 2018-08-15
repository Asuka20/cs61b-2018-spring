package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class Solver {

    private SearchNode head;
    private SearchNode tail;
    int numOfEnqueue;


    public Solver(WorldState initial) {
        MinPQ<SearchNode> searchNodes = new MinPQ<>();
        head = new SearchNode(initial, 0, null);
        searchNodes.insert(head);
        numOfEnqueue += 1;
        while (!searchNodes.isEmpty()) {
            SearchNode X = searchNodes.delMin();
            if (X.getCurrentWorld().isGoal()) {
                tail = X;
                break;
            }
            for (WorldState y: X.getCurrentWorld().neighbors()) {
                if (!X.equals(head) && y.equals(X.getPreviousSearchNode().getCurrentWorld())) {
                    continue;
                }
                SearchNode Y = new SearchNode(y, X.getMoves() + 1, X);
                searchNodes.insert(Y);
                numOfEnqueue += 1;
            }
        }
    }

    public int moves() {
        return tail.getMoves();
    }

    public Iterable<WorldState> solution() {
        ArrayDeque<WorldState> res = new ArrayDeque<>();
        while (!tail.equals(head)) {
            res.push(tail.getCurrentWorld());
            tail = tail.getPreviousSearchNode();
        }
        res.push(tail.getCurrentWorld());
        return res;
    }

}
