package adapters.swing;

import view.IGameCanvas;
import java.awt.Graphics2D;

public final class SwingGameCanvas implements IGameCanvas {
    private final SnakePainter snakePainter;
    private final FoodPainter foodPainter;
    private final BoardPainter boardPainter;

    public SwingGameCanvas(Graphics2D surface, int cellSize) {
        this.snakePainter = new SnakePainter(surface, cellSize);
        this.foodPainter = new FoodPainter(surface, cellSize);
        this.boardPainter = new BoardPainter(surface, cellSize);
    }

    @Override
    public void render(int column, int row) {
        snakePainter.render(column, row);
    }

    @Override
    public void highlight(int column, int row) {
        foodPainter.highlight(column, row);
    }

    @Override
    public void display(int score) {
        boardPainter.display(score);
    }

    @Override
    public void frame(int width, int height) {
        boardPainter.frame(width, height);
    }

}