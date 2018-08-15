package hw4.puzzle;

public class SearchNode implements Comparable<SearchNode>{
    private WorldState currentWorld;
    private int moves;
    private SearchNode previousSearchNode;

    public SearchNode(WorldState X, int mvs, SearchNode Y) {
        currentWorld = X;
        moves = mvs;
        previousSearchNode = Y;
    }

    public WorldState getCurrentWorld() {
        return currentWorld;
    }

    public SearchNode getPreviousSearchNode() {
        return previousSearchNode;
    }

    public int getMoves() {
        return moves;
    }

    @Override
    public int compareTo(SearchNode other) {
        return (this.getMoves() + this.getCurrentWorld().estimatedDistanceToGoal())
                - (other.getMoves() + other.getCurrentWorld().estimatedDistanceToGoal());
    }


}
