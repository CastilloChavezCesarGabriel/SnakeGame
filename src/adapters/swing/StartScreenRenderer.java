package adapters.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public final class StartScreenRenderer {
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private final JPanel panel;
    private final String title;

    public StartScreenRenderer(JPanel panel, String title) {
        this.panel = panel;
        this.title = title;
    }

    public void draw(Graphics graphics) {
        Point button = center();
        announce(graphics);
        frame(graphics, button);
        caption(graphics, button);
    }

    public boolean isClicked(int mouseColumn, int mouseRow) {
        Point button = center();
        return mouseColumn >= button.x && mouseColumn <= button.x + BUTTON_WIDTH
            && mouseRow >= button.y && mouseRow <= button.y + BUTTON_HEIGHT;
    }

    private Point center() {
        int column = (panel.getWidth() - BUTTON_WIDTH) / 2;
        int row = (panel.getHeight() - BUTTON_HEIGHT) / 2;
        return new Point(column, row);
    }

    private void announce(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 48));
        int titleWidth = graphics.getFontMetrics().stringWidth(title);
        graphics.drawString(title, (panel.getWidth() - titleWidth) / 2, panel.getHeight() / 3);
    }

    private void frame(Graphics graphics, Point button) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(button.x, button.y, BUTTON_WIDTH, BUTTON_HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(button.x, button.y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void caption(Graphics graphics, Point button) {
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        String buttonText = "START";
        int textWidth = graphics.getFontMetrics().stringWidth(buttonText);
        int textHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(buttonText, button.x + (BUTTON_WIDTH - textWidth) / 2,
                           button.y + (BUTTON_HEIGHT + textHeight / 2) / 2);
    }
}