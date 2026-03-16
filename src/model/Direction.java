package model;

import utilities.Position;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int columnFactor;
    private final int rowFactor;

    Direction(int columnFactor, int rowFactor) {
        this.columnFactor = columnFactor;
        this.rowFactor = rowFactor;
    }

    public Position translate(Position current, int magnitude) {
        return current.translate(columnFactor * magnitude, rowFactor * magnitude);
    }

    public boolean isOpposite(Direction other) {
        return columnFactor + other.columnFactor == 0
            && rowFactor + other.rowFactor == 0;
    }
}