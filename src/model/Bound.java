package model;

public class Bound {
    private final int width;
    private final int height;

    public Bound(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isOutside(Snake snake) {
        return snake.isOutOfBounds(width, height);
    }

    public int calculateColumns(int radius) {
        return width / radius;
    }

    public int calculateRows(int radius) {
        return height / radius;
    }
}
