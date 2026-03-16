package view;

public interface IGameCanvas {
    void render(int column, int row);
    void highlight(int column, int row);
    void display(int score);
    void frame(int width, int height);
}