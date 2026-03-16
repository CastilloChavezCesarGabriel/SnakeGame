package model;

import java.util.Random;
import utilities.IPositionVisitor;
import utilities.Position;
import model.visitor.IGameVisitor;

public final class Food implements IRenderable {
    private final int radius;
    private final Bound bound;
    private final Random random;
    private Position position;

    public Food(int radius, Bound bound) {
        this.radius = radius;
        this.bound = bound;
        this.random = new Random();
        relocate();
    }

    public boolean isEaten(Position snakeHead) {
        return position.matches(snakeHead);
    }

    public void relocate() {
        this.position = bound.randomize(random, radius);
    }

    public void accept(IGameVisitor visitor) {
        visitor.spotlight(this, radius);
    }

    @Override
    public void iterate(IPositionVisitor visitor) {
        position.accept(visitor);
    }
}