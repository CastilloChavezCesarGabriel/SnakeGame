package adapters.swing;

import java.awt.Color;

public final class FoodPainter extends Painter {
    private static final Color CORE = new Color(248, 113, 113);
    private static final Color GLOW = new Color(248, 113, 113, 40);
    private static final int PADDING = 6;

    public FoodPainter(java.awt.Graphics2D graphics, int cellSize) {
        super(graphics, cellSize);
    }

    public void paint(int column, int row) {
        int glowSize = cellSize + PADDING * 2;
        graphics.setColor(GLOW);
        graphics.fillOval(column - PADDING, row - PADDING, glowSize, glowSize);
        graphics.setColor(CORE);
        graphics.fillOval(column, row, cellSize, cellSize);
        shine(column, row);
    }

    private void shine(int column, int row) {
        Color accent = new Color(255, 200, 200);
        int shineSize = cellSize / 3;
        graphics.setColor(accent);
        graphics.fillOval(column + cellSize / 4, row + cellSize / 4, shineSize, shineSize);
    }
}