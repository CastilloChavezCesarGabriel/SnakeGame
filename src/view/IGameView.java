package view;

public interface IGameView {
    void onStartRequested();
    void onUpPressed();
    void onDownPressed();
    void onLeftPressed();
    void onRightPressed();
    void onRestartRequested();
    void onExitRequested();
}