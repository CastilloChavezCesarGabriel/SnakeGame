package adapters.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class SwingMouseListener extends MouseAdapter {
    private final SwingView view;

    public SwingMouseListener(SwingView view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        view.click(event.getX(), event.getY());
    }
}