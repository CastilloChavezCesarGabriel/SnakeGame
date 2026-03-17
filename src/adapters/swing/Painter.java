package adapters.swing;

import java.awt.Graphics2D;

public abstract class Painter {
    protected final Graphics2D graphics;
    protected final int cellSize;

    protected Painter(Graphics2D graphics, int cellSize) {
        this.graphics = graphics;
        this.cellSize = cellSize;
    }
}