package adapters.swing;

import java.awt.Color;
import java.awt.Font;

public final class BoardPainter extends Painter {
    private static final Color BACKGROUND = new Color(26, 26, 46);
    private static final Color CHECKER = new Color(30, 30, 52);
    private static final Color BORDER = new Color(55, 55, 85);

    public BoardPainter(java.awt.Graphics2D graphics, int cellSize) {
        super(graphics, cellSize);
    }

    public void paint(int width, int height) {
        fill(width, height);
        tile(width, height);
        outline(width, height);
    }

    public void tally(int score) {
        int labelColumn = 12;
        int labelRow = 20;
        Color labelColor = new Color(226, 232, 240);
        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        graphics.setColor(labelColor);
        graphics.setFont(labelFont);
        graphics.drawString("SCORE  " + score, labelColumn, labelRow);
    }

    private void fill(int width, int height) {
        graphics.setColor(BACKGROUND);
        graphics.fillRect(0, 0, width, height);
    }

    private void tile(int width, int height) {
        graphics.setColor(CHECKER);
        for (int column = 0; column < width; column += cellSize) {
            for (int row = 0; row < height; row += cellSize) {
                if ((column / cellSize + row / cellSize) % 2 == 0)
                    graphics.fillRect(column, row, cellSize, cellSize);
            }
        }
    }

    private void outline(int width, int height) {
        graphics.setColor(BORDER);
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.drawRect(1, 1, width - 3, height - 3);
    }
}