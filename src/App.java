import model.Model;
import view.View;
import controller.Controller;

public class App {
    public static void main(String[] args) {
        int score = 0;
        int radius = 20;
        int width = 600;
        int height = 400;

        Model model = new Model(score, radius, width, height);
        View view = new View(score, width, height);
        new Controller(model, view);
    }
}