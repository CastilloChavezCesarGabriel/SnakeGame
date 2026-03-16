package adapters.swing;

import view.IGameCanvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public final class SwingGameCanvas implements IGameCanvas {
    private final Graphics graphics;
    private final int cellSize;

    public SwingGameCanvas(Graphics graphics, int cellSize) {
        this.graphics = graphics;
        this.cellSize = cellSize;
    }

    @Override
    public void render(int column, int row) {
        fill(column, row, Color.GREEN);
    }

    @Override
    public void highlight(int column, int row) {
        fill(column, row, Color.RED);
    }

    @Override
    public void display(int score) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        graphics.drawString("Score: " + score, 20, 20);
    }

    @Override
    public void frame(int width, int height) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawRect(0, 0, width - 1, height - 1);
    }

    private void fill(int column, int row, Color color) {
        graphics.setColor(color);
        graphics.fillOval(column, row, cellSize, cellSize);
    }
}