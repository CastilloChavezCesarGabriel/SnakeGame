package adapters.swing;

import view.IGameView;
import view.IGameTimer;
import view.IRenderCallback;
import view.ITimerCallback;
import view.IViewImplementation;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

public final class SwingView extends JPanel implements IViewImplementation {
    private static final String GAME_TITLE = "Snake Game";
    private final List<IGameView> listeners;
    private final List<IRenderCallback> renderCallbacks;
    private final StartScreen startScreen;
    private final SwingMouseListener mouseListener;
    private final int cellSize;
    private boolean showStartScreen = true;

    public SwingView(int cellSize) {
        this.cellSize = cellSize;
        this.listeners = new ArrayList<>();
        this.renderCallbacks = new ArrayList<>();
        this.startScreen = new StartScreen(this, GAME_TITLE);
        this.mouseListener = new SwingMouseListener(startScreen, listeners);
        setBackground(new Color(26, 26, 46));
        setFocusable(true);
        addKeyListener(new SwingKeyListener(listeners));
        addMouseListener(mouseListener);
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
        removeMouseListener(mouseListener);
        requestFocusInWindow();
        repaint();
    }

    @Override
    public void finish() {
        new GameOverDialog(this, GAME_TITLE).show(listeners);
    }

    @Override
    public void refresh() {
        repaint();
    }

    @Override
    public void focus() {
        requestFocusInWindow();
    }

    @Override
    public void setup(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        JFrame window = new JFrame(GAME_TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    @Override
    public IGameTimer schedule(int delay, ITimerCallback callback) {
        return new SwingTimer(delay, callback);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D surface = (Graphics2D) graphics;
        polish(surface);
        if (showStartScreen) {
            startScreen.render(surface);
        } else {
            new GameScreen(this, cellSize).render(surface, renderCallbacks);
        }
    }

    private void polish(Graphics2D surface) {
        surface.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        surface.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    }
}