package model;

import java.util.Random;
import utilities.Position;

public final class Bound {
    private final int width;
    private final int height;

    public Bound(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean contains(Position position) {
        return position.isWithin(width, height);
    }

    public Position randomize(Random random, int cellSize) {
        int column = random.nextInt(width / cellSize) * cellSize;
        int row = random.nextInt(height / cellSize) * cellSize;
        return new Position(column, row);
    }
}