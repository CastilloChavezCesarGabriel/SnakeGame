package model;

import java.util.Random;
import utilities.IPositionVisitor;
import utilities.Position;

public final class Bound implements IPositionVisitor {
    private final int width;
    private final int height;
    private boolean isInside;

    public Bound(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean contains(Position position) {
        position.accept(this);
        return isInside;
    }

    @Override
    public void visit(int column, int row) {
        isInside = column >= 0 && row >= 0
            && column < width && row < height;
    }

    public Position randomize(Random random, int cellSize) {
        int randomColumn = random.nextInt(width / cellSize) * cellSize;
        int randomRow = random.nextInt(height / cellSize) * cellSize;
        return new Position(randomColumn, randomRow);
    }
}