package model;

import observer.IGameListener;
import observer.GameStateNotifier;
import model.visitor.IGameVisitor;

public final class Model {
    private int score;
    private final Snake snake;
    private final Food food;
    private final Bound bound;
    private final GameStateNotifier stateNotifier;

    public Model(Bound bound, int cellSize) {
        this.score = 0;
        this.bound = bound;
        this.snake = new Snake(cellSize);
        this.food = new Food(cellSize, bound);
        this.stateNotifier = new GameStateNotifier();
    }

    public void update() {
        snake.move();
        feed();
        if (snake.hasCollided() || snake.isOut(bound)) {
            stateNotifier.end();
        } else {
            stateNotifier.tick();
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
        snake.create();
        food.relocate();
        score = 0;
    }

    public void register(IGameListener listener) {
        stateNotifier.add(listener);
    }

    public void accept(IGameVisitor visitor) {
        snake.accept(visitor);
        food.accept(visitor);
        visitor.count(score);
    }
}