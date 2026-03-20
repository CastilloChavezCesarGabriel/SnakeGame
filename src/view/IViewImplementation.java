package view;

public interface IViewImplementation {
    void add(IGameView listener);
    void add(IRenderCallback callback);
    void start();
    void finish();
    void render();
    void focus();
    void setup(int width, int height);
    IGameTimer schedule(int delay, ITimerCallback callback);
}