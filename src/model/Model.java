package model;

import observer.IGameListener;
import observer.IGameNotifier;
import model.visitor.IGameVisitor;

public final class Model {
    private int score;
    private final Snake snake;
    private final Food food;
    private final Bound bound;
    private final IGameNotifier notifier;

    public Model(Bound bound, int cellSize, IGameNotifier notifier) {
        this.score = 0;
        this.bound = bound;
        this.snake = new Snake(cellSize);
        this.food = new Food(cellSize, bound);
        this.notifier = notifier;
    }

    public void update() {
        snake.move();
        feed();
        if (snake.hasCollided() || snake.isOut(bound)) {
            notifier.end();
        } else {
            notifier.tick();
        }
    }

    private void feed() {
        if (snake.eat(food)) {
            food.relocate();
            score++;
        }
    }

    public void change(Direction newDirection) {
        snake.change(newDirection);
    }

    public void reset() {
        snake.spawn();
        food.relocate();
        score = 0;
    }

    public void register(IGameListener listener) {
        notifier.add(listener);
    }

    public void accept(IGameVisitor visitor) {
        snake.accept(visitor);
        food.accept(visitor);
        visitor.tally(score);
    }
}