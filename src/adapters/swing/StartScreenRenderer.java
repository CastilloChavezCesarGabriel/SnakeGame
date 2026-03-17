package adapters.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

public final class StartScreenRenderer {
    private static final int BUTTON_WIDTH = 160;
    private static final int BUTTON_HEIGHT = 48;
    private static final int BUTTON_ARC = 12;
    private static final Color ACCENT = new Color(74, 222, 128);
    private static final Color DARK = new Color(26, 26, 46);
    private final JPanel panel;
    private final String title;

    public StartScreenRenderer(JPanel panel, String title) {
        this.panel = panel;
        this.title = title;
    }

    public void draw(Graphics2D surface) {
        fill(surface);
        announce(surface);
        instruct(surface);
        Point button = center();
        frame(surface, button);
        caption(surface, button);
    }

    public boolean isClicked(int mouseX, int mouseY) {
        Point button = center();
        return mouseX >= button.x && mouseX <= button.x + BUTTON_WIDTH
            && mouseY >= button.y && mouseY <= button.y + BUTTON_HEIGHT;
    }

    private void style(Graphics2D surface, Color color) {
        surface.setColor(color);
    }

    private FontMetrics font(Graphics2D surface, Font typeface) {
        surface.setFont(typeface);
        return surface.getFontMetrics();
    }

    private void fill(Graphics2D surface) {
        style(surface, DARK);
        surface.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    private void announce(Graphics2D surface) {
        style(surface, ACCENT);
        FontMetrics metrics = font(surface, new Font("SansSerif", Font.BOLD, 52));
        int titleColumn = (panel.getWidth() - metrics.stringWidth(title)) / 2;
        surface.drawString(title, titleColumn, panel.getHeight() / 3);
    }

    private void instruct(Graphics2D surface) {
        String instruction = "Use arrow keys to control the snake";
        style(surface, new Color(148, 163, 184));
        FontMetrics metrics = font(surface, new Font("SansSerif", Font.PLAIN, 16));
        int instructionColumn = (panel.getWidth() - metrics.stringWidth(instruction)) / 2;
        int verticalOffset = 36;
        surface.drawString(instruction, instructionColumn, panel.getHeight() / 3 + verticalOffset);
    }

    private Point center() {
        int verticalOffset = 20;
        int column = (panel.getWidth() - BUTTON_WIDTH) / 2;
        int row = panel.getHeight() / 2 + verticalOffset;
        return new Point(column, row);
    }

    private void frame(Graphics2D surface, Point button) {
        style(surface, ACCENT);
        surface.fillRoundRect(button.x, button.y, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_ARC, BUTTON_ARC);
    }

    private void caption(Graphics2D surface, Point button) {
        style(surface, DARK);
        FontMetrics metrics = font(surface, new Font("SansSerif", Font.BOLD, 18));
        String label = "START";
        int labelColumn = button.x + (BUTTON_WIDTH - metrics.stringWidth(label)) / 2;
        int labelRow = button.y + (BUTTON_HEIGHT + metrics.getAscent() - metrics.getDescent()) / 2;
        surface.drawString(label, labelColumn, labelRow);
    }
}