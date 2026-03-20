package observer;

public interface IGameNotifier {
    void add(IGameListener listener);
    void tick();
    void end();
}
