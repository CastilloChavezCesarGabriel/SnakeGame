package view;
import strategy.IRender;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View extends JPanel {
    private final String title = "Snake Game";
    private List<IRender> renderables;

    public View(int width, int height) {
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        setUpFrame();
    }

    private void setUpFrame() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public boolean ask(String message) {
        int result = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public void render(List<IRender> renderables) {
        this.renderables = renderables;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (renderables != null) {
            for (IRender item : renderables) {
                item.render(graphics);
            }
        }
    }
}
