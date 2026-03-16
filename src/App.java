import model.Bound;
import model.Model;
import adapters.swing.SwingView;
import controller.Controller;

public final class App {
    public static void main(String[] args) {
        int width = 600;
        int height = 400;
        int cellSize = 20;
        Bound bound = new Bound(width, height);
        Model model = new Model(bound, cellSize);
        SwingView swingView = new SwingView(cellSize);
        swingView.setup(width, height);
        Controller controller = new Controller(model, swingView);
    }
}
