package view;

public interface IGameCanvas {
    void render(int column, int row);
    void highlight(int column, int row);
    void inscribe(int score);
    void prepare(int width, int height);
}