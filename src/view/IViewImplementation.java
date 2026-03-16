package view;

public interface IViewImplementation {
    void add(IGameView listener);
    void add(IRenderCallback callback);
    void start();
    void conclude();
    void render();
    void focus();
}