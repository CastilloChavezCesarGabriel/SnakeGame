package view;

public interface IViewImplementation {
    void add(IGameView listener);
    void add(IRenderCallback callback);
    void start();
    void finish();
    void render();
    void focus();
}