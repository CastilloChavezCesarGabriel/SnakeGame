package adapters.swing;

import view.IGameTimer;
import view.ITimerCallback;
import javax.swing.Timer;

public final class SwingTimer implements IGameTimer {
    private final Timer timer;

    public SwingTimer(int delay, ITimerCallback callback) {
        this.timer = new Timer(delay, event -> callback.onTick());
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }
}