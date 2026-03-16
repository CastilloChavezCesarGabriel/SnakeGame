package adapters.swing;

import view.IGameView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public final class SwingKeyListener extends KeyAdapter {
    private final List<IGameView> listeners;

    public SwingKeyListener(List<IGameView> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        for (IGameView listener : listeners) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP -> listener.onUpPressed();
                case KeyEvent.VK_DOWN -> listener.onDownPressed();
                case KeyEvent.VK_LEFT -> listener.onLeftPressed();
                case KeyEvent.VK_RIGHT -> listener.onRightPressed();
            }
        }
    }
}