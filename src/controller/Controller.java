package controller;

import model.Direction;
import model.Model;
import view.IViewImplementation;
import view.IGameView;
import view.IGameCanvas;
import view.IGameTimer;
import view.IRenderCallback;
import view.ITimerCallback;
import observer.IGameListener;

public final class Controller implements IGameListener, IGameView, IRenderCallback, ITimerCallback {
    private static final int TICK_DELAY = 100;
    private final Model model;
    private final IViewImplementation view;
    private IGameTimer timer;

    public Controller(Model model, IViewImplementation view) {
        this.model = model;
        this.view = view;
        this.model.register(this);
        this.view.add((IGameView) this);
        this.view.add((IRenderCallback) this);
    }

    @Override
    public void onTick() {
        model.update();
    }

    @Override
    public void onStateChanged() {
        view.render();
    }

    @Override
    public void onGameOver() {
        timer.stop();
        view.finish();
    }

    @Override
    public void onStartRequested() {
        launch();
    }

    @Override
    public void onRestartRequested() {
        model.reset();
        view.focus();
        launch();
    }

    private void launch() {
        view.start();
        schedule();
    }

    private void schedule() {
        timer = view.schedule(TICK_DELAY, this);
        timer.start();
    }

    @Override
    public void onExitRequested() {
        System.exit(0);
    }

    @Override
    public void onUpPressed() {
        model.change(Direction.UP);
    }

    @Override
    public void onDownPressed() {
        model.change(Direction.DOWN);
    }

    @Override
    public void onLeftPressed() {
        model.change(Direction.LEFT);
    }

    @Override
    public void onRightPressed() {
        model.change(Direction.RIGHT);
    }

    @Override
    public void render(IGameCanvas canvas) {
        model.accept(new GameRenderer(canvas));
    }
}