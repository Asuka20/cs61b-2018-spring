package byog.Core;

import org.junit.Test;
import static org.junit.Assert.*;

public class testRoom {
    @Test
    public void testRoom() {
        Position p1 = new Position(0, 0);
        Position q1 = new Position(10, 10);
        Room r1 = new Room(p1, q1);

        Position p2 = new Position(8, 8);
        Position q2 = new Position(15, 15);
        Room r2 = new Room(p2, q2);

        Position p3 = new Position(7, 7);
        Position q3 = new Position(-2, 5);
        Room r3 = new Room(p3, q3);

        assertTrue(r1.isIntersect(r2));
        assertFalse(r3.isIntersect(r2));
        assertTrue(r3.isIntersect(r1));

    }
}
