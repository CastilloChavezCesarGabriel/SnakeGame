package model;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import observer.IRenderListener;
import observer.IScoreListener;

public class Model {
    private int score = 0;
    private final int radius;
    private final LinkedList<Point> body;
    private final Snake snake;
    private final Food food;
    private final Bound bound;
    private Direction direction = Direction.RIGHT;
    private IRenderListener renderables;
    private final List<IScoreListener> newScore = new ArrayList<>();

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
            score++;
            onScoreChanged();
        }
        onRenderRequested();
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
        score = 0;
        onScoreChanged();
    }

    public void addScore(IScoreListener score) {
        this.newScore.add(score);
    }

    public void addRender(IRenderListener renderables) {
        this.renderables = renderables;
    }

    private void onScoreChanged() {
        for (IScoreListener score : this.newScore) {
            score.onScoreChanged(this.score);
        }
    }

    private void onRenderRequested() {
        if (renderables != null) {
            renderables.onRender(List.of(snake, food));
        }
    }
}
