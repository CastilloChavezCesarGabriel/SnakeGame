package adapters.swing;

import view.IGameView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public final class SwingMouseListener extends MouseAdapter {
    private final StartScreen startScreen;
    private final List<IGameView> listeners;

    public SwingMouseListener(StartScreen startScreen, List<IGameView> listeners) {
        this.startScreen = startScreen;
        this.listeners = listeners;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (startScreen.isClicked(event.getX(), event.getY())) {
            for (IGameView listener : listeners) {
                listener.onStartRequested();
            }
        }
    }
}