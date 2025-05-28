import model.Model;
import view.View;
import controller.Controller;

public class App {
    public static void main(String[] args) {
        int radius = 20;
        int width = 600;
        int height = 400;

        Model model = new Model(radius, width, height);
        View view = new View(width, height);
        new Controller(model, view);
    }
}