package byog.Core;

import static byog.Core.WorldGenerator.*;

public class Position {
    int x;
    int y;

    Position(int x0, int y0) {
        x = x0;
        y = y0;
    }

    public static Position randomPerimeterPosition(Room r) {
        int type = RANDOM.nextInt(4);
        switch (type) {
            case 0: return r.p.move(new Position(0, RANDOM.nextInt(r.roomHeight())));
            case 1: return r.q.move(new Position(0, -RANDOM.nextInt(r.roomHeight())));
            case 2: return r.p.move(new Position( RANDOM.nextInt(r.roomWidth()), 0));
            case 3: return r.q.move(new Position( -RANDOM.nextInt(r.roomWidth()), 0));
            default: return ORIGIN;
        }

    }

    public static Position randomPosition() {
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);
        return new Position(x, y);
    }

    public Position move(Position other) {
        return new Position(x + other.x, y + other.y);
    }


    public boolean isSame(Position other) {
        return (x == other.x && y == other.y);
    }

    public boolean isInside(Room room) {
        return (x >= room.p.x && x < room.q.y && y >= room.p.y && y < room.q.y);
    }


    public static void regularization(Position p, Position q) {
        if (p.x > q.x) {
            int tmp = q.x;
            q.x = p.x;
            p.x = tmp;
        }
        if (p.y > q.y) {
            int tmp = q.y;
            q.y = p.y;
            p.y = tmp;
        }
    }
}

