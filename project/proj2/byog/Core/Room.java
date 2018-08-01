package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import byog.Core.WorldGenerator.*;
import java.util.ArrayList;
import java.util.Random;

import static byog.Core.WorldGenerator.*;


public class Room {
    Position p;
    Position q;

    public Room(Position p, Position q) {
        Position.regularization(p, q);
        this.p = p;
        this.q = q;
    }


    public Room() {
        Position p = Position.randomPosition();
        Position q = Position.randomPosition();
        while (q.isSame(p)) {
                q = Position.randomPosition();
        }
        Position.regularization(p, q);
        this.p = p;
        this.q = q;
    }

    public int roomWidth() {
      return q.x - p.x;
    }

    public int roomHeight() {
        return q.y - p.y;
    }



    public Room emerge() {
        Position p0 = Position.randomPerimeterPosition(this);
        if (this.isHallway()) {
            Position q0 = Position.randomPosition();
            while (q0.isInside(this)) {
                q0 = Position.randomPosition();
            }
            return new Room(p0, q0);
        } else {
            return emergeHallway(p0);
        }
    }

    public Room emergeHallway(Position p0) {
        Position q0 = p0;

        while (q0.isInside(this)) {
            int diff = 2 * RANDOM.nextInt(2) - 1;
            int t = RANDOM.nextInt(2);

            if (t == 0) {
                int xHallwayEnd = RANDOM.nextInt(WIDTH);
                q0 = new Position(xHallwayEnd, p0.y + diff);
            } else {
                int yHallwayEnd = RANDOM.nextInt(HEIGHT);
                q0 = new Position(p0.x + diff, yHallwayEnd);
            }
        }
        return new Room(p0, q0);

    }

    public boolean isHallway() {
        if (Math.abs(p.x - q.x) == 1 || Math.abs(p.y - q.y) == 1){
            return true;
        }
        return false;
    }



    public void drawOn(TETile[][] world) {
        for (int i = p.x; i < q.x; i++) {
            for (int j =p.y; j< q.y; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }

    public boolean isValid() {
        if (!this.p.isInside(BACKGROUND) || !this.q.isInside(BACKGROUND) || p.x == q.x || p.y == q.y){
            return false;
        }
        return true;
    }


    public boolean isIntersect(Room other) {
        Position p0 = p;
        Position q0 = q;

        Position p1 = other.p;
        Position q1 = other.q;

        if (q0.x < p1.x || q1.x < p0.x || q0.y < p1.y || q1.y < p0.y) {
            return false;
        }
        return true;
    }

}

