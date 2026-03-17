package adapters.swing;

import view.IGameCanvas;
import view.IRenderCallback;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.List;

public final class GameScreenRenderer {
    private final JPanel panel;
    private final int cellSize;

    public GameScreenRenderer(JPanel panel, int cellSize) {
        this.panel = panel;
        this.cellSize = cellSize;
    }

    public void draw(Graphics2D surface, List<IRenderCallback> callbacks) {
        IGameCanvas canvas = new SwingGameCanvas(surface, cellSize);
        canvas.frame(panel.getWidth(), panel.getHeight());
        for (IRenderCallback callback : callbacks) {
            callback.render(canvas);
        }
    }
}