package byog.lab5;

public class Position {
    int x;
    int y;

    Position(int x0, int y0) {
        x = x0;
        y = y0;
    }

    public void step(Position p) {
        x = x + p.x;
        y = y + p.y;
    }


    public void stepNorth(int size) {
        Position north = new Position(0, 2 * size);
        step(north);
    }

    public void stepNortheast(int size) {
        Position northeast = new Position(2 * size - 1, size);
        step(northeast);
    }

    public void stepSoutheast(int size) {
        Position southeast = new Position(2 * size - 1, -size);
        step(southeast);
    }

    public void stepSouth(int size) {
        Position south = new Position(0, -2 * size);
        step(south);
    }
}

