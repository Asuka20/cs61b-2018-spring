package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    private static final long SEED = 114514;
    private static final Random RANDOM = new Random(SEED);


    /** Adds a hexagon of tiles.
     * @param world the world to draw on
     * @param size the size of the hexagon
     * @param p bottom left position of the hexagon
     * @param t the tile to draw
     */
    private static void addHexagon(TETile[][] world, int size, Position p, TETile t) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        for (int i = 0; i < 2 * size; i++){
            Position pRowStart = new Position(p.x + xRowOffset(size, i), p.y + i);
            addRow(world, pRowStart, xRowWidth(size, i), t);
        }
    }

    /** Adds a row of tiles.
     * @param world the world to draw on
     * @param width the length of the row
     * @param p the leftmost position of the row
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int i = 0; i < width; i++) {
            world[p.x + i][p.y] = t;
        }
    }

    private static int xRowOffset(int size, int i) {
        if (i < size) {
            return size - 1 - i;
        }
        return i - size;
    }

    private static int xRowWidth(int size, int i) {
        if (i < size) {
            return size + 2 * i;
        }
        return 5 * size - 2 - 2 * i;
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            default: return Tileset.NOTHING;
        }
    }

    public static void addVerticalHexagons(TETile[][] world, int num, int size, Position p) {
        for (int i = 0; i < num; i++) {
            addHexagon(world, size, p, randomTile());
            p.stepNorth(size);
        }
    }

    private static void stepBetweenColumns(Position p, int num, int size, int step) {
        if (step < num - 1) {
            p.stepSouth((step + num) * size);
            p.stepSoutheast(size);
        } else if (step == num - 1) {
            p.stepSouth((step + num) * size);
            p.stepNortheast(size);
        } else {
            p.stepSouth((2 * num - step + 1) * size);
            p.stepNortheast(size);
        }
    }

    /**
     * Add a meta-hexagon of hexagons.
     * @param world the world to draw on
     * @param num the size of the meta-hexagon
     * @param size the size of the hexagon
     * @param p the bottom left position of the hexagon located at the bottom of leftmost column of the meta-hexagon
     */
    public static void addMultiHexagons(TETile[][] world, int num, int size, Position p) {
        for (int i = 0; i < 2 * num - 1; i++) {
            if (i < num) {
                addVerticalHexagons(world, i + num, size, p);
            } else {
                addVerticalHexagons(world, 2 * num - i + 1, size, p);
            }
            stepBetweenColumns(p, num, size, i);
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        // Position pHexagon = new Position(30, 15);
        // addHexagon(world, 5, pHexagon, Tileset.GRASS);

        Position pMultiHexagon = new Position(20, 20);
        //addVerticalHexagons(world, 3, 3, pMultiHexagon);
        addMultiHexagons(world, 3, 3, pMultiHexagon);



        // draws the world to the screen
        ter.renderFrame(world);
    }
}
