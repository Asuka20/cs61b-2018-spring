package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenerator {
    static final int WIDTH = 20;
    static final int HEIGHT = 20;
    static final Position ORIGIN = new Position(0, 0);
    static final Position DIAGONAL = new Position(WIDTH, HEIGHT);
    static final Room BACKGROUND = new Room(ORIGIN, DIAGONAL);

    static final long SEED = 114514;
    public  static final Random RANDOM = new Random(SEED);


    public static void randomGenerator(TETile[][] world, int num) {
        ArrayList<Room> roomStack = new ArrayList<Room>();
        Room first = new Room();
        roomStack.add(first);
        first.drawOn(world);

        int roomType = 0;
        while (num > 0) {
            int index = RANDOM.nextInt(roomStack.size());
            Room curRoom = roomStack.get(index);
            Room newRoom= curRoom.emerge();
            if (newRoom.isValid()) {
                roomStack.add(newRoom);
                curRoom.drawOn(world);
                num -= 1;
            }
        }

    }



    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        randomGenerator(world, 4);


        // draws the world to the screen
        ter.renderFrame(world);
    }

}
