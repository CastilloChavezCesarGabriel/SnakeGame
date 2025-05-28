package model;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import strategy.IRender;

public class Model {
    private final int radius;
    private final LinkedList<Point> body;
    private final Snake snake;
    private final Food food;
    private final Bound bound;
    private Direction direction = Direction.RIGHT;

    public Model(int radius, int width, int height) {
        this.radius = radius;
        this.body = new LinkedList<>();
        this.snake = new Snake(radius, body);
        this.bound = new Bound(width, height);
        this.food = new Food(radius, bound);
    }

    public enum Direction { UP, DOWN, LEFT, RIGHT }

    public void update() {
        Point head = body.getFirst();
        int x = head.x;
        int y = head.y;

        switch (direction) {
            case RIGHT -> x += radius;
            case LEFT -> x -= radius;
            case UP -> y -= radius;
            case DOWN -> y += radius;
        }

        Point newHead = new Point(x, y);
        snake.move(newHead);
        if(snake.eat(food)) {
            food.relocate();
        }
    }

    public void change(Direction newDirection) {
        if ((direction == Direction.LEFT && newDirection == Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDirection == Direction.LEFT) ||
                (direction == Direction.UP && newDirection == Direction.DOWN) ||
                (direction == Direction.DOWN && newDirection == Direction.UP)) {
            return;
        }

        direction = newDirection;
    }

    public boolean isGameOver() {
        return snake.hasCollided() || bound.isOutside(snake);
    }

    public void reset() {
        snake.initialize();
        direction = Direction.RIGHT;
        food.relocate();
    }

    public List<IRender> getRenderables() {
        return List.of(snake, food);
    }
}
