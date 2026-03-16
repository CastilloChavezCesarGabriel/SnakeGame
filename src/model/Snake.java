package model;

import java.util.LinkedList;
import utilities.IPositionVisitor;
import utilities.Position;
import model.visitor.IGameVisitor;

public final class Snake implements IRenderable {
    private final int radius;
    private final LinkedList<Position> body;
    private Position head;
    private boolean grow;
    private Direction direction;

    public Snake(int radius) {
        this.radius = radius;
        this.body = new LinkedList<>();
        create();
    }

    public void create() {
        body.clear();
        int tailSegments = 3;
        int startColumn = 100;
        int startRow = 100;

        head = new Position(startColumn + tailSegments * radius, startRow);
        for (int index = tailSegments - 1; index >= 0; index--) {
            body.addLast(new Position(startColumn + index * radius, startRow));
        }

        grow = false;
        direction = Direction.RIGHT;
    }

    public void move() {
        body.addFirst(head);
        head = direction.translate(head, radius);

        if (!grow) {
            body.removeLast();
        } else {
            grow = false;
        }
    }

    public void change(Direction newDirection) {
        if (!direction.isOpposite(newDirection)) {
            direction = newDirection;
        }
    }

    public boolean eat(Food food) {
        boolean hasEaten = food.isEaten(head);
        if (hasEaten) {
            grow = true;
        }
        return hasEaten;
    }

    public boolean hasCollided() {
        for (Position segment : body) {
            if (head.matches(segment)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOut(Bound bound) {
        return !bound.contains(head);
    }

    public void accept(IGameVisitor visitor) {
        visitor.visit(this, radius);
    }

    @Override
    public void iterate(IPositionVisitor visitor) {
        head.accept(visitor);
        for (Position segment : body) {
            segment.accept(visitor);
        }
    }
}