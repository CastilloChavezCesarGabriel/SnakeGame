package adapters.swing;

import java.awt.Color;

public final class SnakePainter extends Painter {
    private static final Color BODY = new Color(74, 222, 128);
    private static final Color EDGE = new Color(34, 150, 74);
    private static final int ARC = 6;

    public SnakePainter(java.awt.Graphics2D graphics, int cellSize) {
        super(graphics, cellSize);
    }

    public void render(int column, int row) {
        int inset = 1;
        graphics.setColor(EDGE);
        graphics.fillRoundRect(column, row, cellSize, cellSize, ARC, ARC);
        int innerSize = cellSize - inset * 2;
        graphics.setColor(BODY);
        graphics.fillRoundRect(column + inset, row + inset, innerSize, innerSize, ARC, ARC);
    }
}