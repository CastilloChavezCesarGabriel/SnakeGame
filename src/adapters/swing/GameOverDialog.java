package adapters.swing;

import view.IGameView;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.List;

public final class GameOverDialog {
    private final JPanel parent;
    private final String title;

    public GameOverDialog(JPanel parent, String title) {
        this.parent = parent;
        this.title = title;
    }

    public void show(List<IGameView> listeners) {
        int result = JOptionPane.showConfirmDialog(
            parent, "Game Over! Try again?", title, JOptionPane.YES_NO_OPTION
        );

        for (IGameView listener : listeners) {
            if (result == JOptionPane.YES_OPTION) {
                listener.onRestartRequested();
            } else {
                listener.onExitRequested();
            }
        }
    }
}