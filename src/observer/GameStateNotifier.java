package observer;

import java.util.ArrayList;
import java.util.List;

public final class GameStateNotifier implements IGameNotifier {
    private final List<IGameListener> listeners;

    public GameStateNotifier() {
        this.listeners = new ArrayList<>();
    }

    public void add(IGameListener listener) {
        listeners.add(listener);
    }

    public void tick() {
        for (IGameListener listener : listeners) {
            listener.onStateChanged();
        }
    }

    public void end() {
        for (IGameListener listener : listeners) {
            listener.onGameOver();
        }
    }
}