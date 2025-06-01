package model;
import java.awt.*;
import java.util.Random;
import strategy.IRender;

public class Food implements IRender {
    private final int radius;
    private Point position;
    private final Bound bound;
    private final Random random = new Random();

    public Food(int radius, Bound Bound) {
        this.radius = radius;
        this.bound = Bound;
        relocate();
    }

    public boolean isEaten(Point fruit) {
        return this.position.equals(fruit);
    }

    public void relocate() {
        int x = random.nextInt(bound.calculateColumns(radius)) * radius;
        int y = random.nextInt(bound.calculateRows(radius)) * radius;
        this.position = new Point(x, y);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(position.x, position.y, radius, radius);
    }
}
