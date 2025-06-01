package model;

import java.awt.*;
import java.util.LinkedList;
import strategy.IRender;

public class Snake implements IRender {
    private final int radius;
    private final LinkedList<Point> body;
    private boolean grow = false;

    public Snake(int radius, LinkedList<Point> body) {
        this.radius = radius;
        this.body = body;
        initialize();
    }

    public void initialize() {
        body.clear();
        for (int i = 0; i < 4; i++) {
            body.addFirst(new Point(100 + i * radius, 100));
        }
        grow = false;
    }

    public void move(Point newHeadPosition) {
        body.addFirst(newHeadPosition);
        if (!grow) {
            body.removeLast();
        } else {
            grow = false;
        }
    }

    public void grow() {
        grow = true;
    }

    public boolean eat(Food food) {
        if(food.isEaten(body.getFirst())) {
            grow();
            return true;
        }
        return false;
    }

    public boolean hasCollided() {
        Point head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfBounds(int width, int height) {
        Point head = body.getFirst();
        if(head.x < 0 || head.y < 0 || head.x >= width || head.y >= height) {
            return true;
        }
        return false;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        for (Point snake : body) {
            graphics.fillOval(snake.x, snake.y, radius, radius);
        }
    }
}
