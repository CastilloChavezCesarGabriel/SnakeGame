package adapters.swing;

import view.IGameView;
import view.IRenderCallback;
import view.IViewImplementation;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public final class SwingView extends JPanel implements IViewImplementation {
    private static final String GAME_TITLE = "Snake Game";
    private final List<IGameView> listeners;
    private final List<IRenderCallback> renderCallbacks;
    private final StartScreenRenderer startScreen;
    private final int cellSize;
    private boolean showStartScreen = true;

    public SwingView(int cellSize) {
        this.cellSize = cellSize;
        this.listeners = new ArrayList<>();
        this.renderCallbacks = new ArrayList<>();
        this.startScreen = new StartScreenRenderer(this, GAME_TITLE);
        setFocusable(true);
        addKeyListener(new SwingKeyListener(listeners));
        addMouseListener(new SwingMouseListener(this));
    }

    public void setup(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        JFrame frame = new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void add(IGameView listener) {
        listeners.add(listener);
    }

    @Override
    public void add(IRenderCallback callback) {
        renderCallbacks.add(callback);
    }

    @Override
    public void start() {
        showStartScreen = false;
        requestFocusInWindow();
        repaint();
    }

    @Override
    public void conclude() {
        new GameOverDialog(this, GAME_TITLE).show(listeners);
    }

    @Override
    public void render() {
        repaint();
    }

    @Override
    public void focus() {
        requestFocusInWindow();
    }

    public void click(int mouseColumn, int mouseRow) {
        if (!showStartScreen) return;
        if (startScreen.isClicked(mouseColumn, mouseRow)) {
            for (IGameView listener : listeners) {
                listener.onStartRequested();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (showStartScreen) {
            startScreen.draw(graphics);
        } else {
            new GameScreenRenderer(this, cellSize).draw(graphics, renderCallbacks);
        }
    }
}