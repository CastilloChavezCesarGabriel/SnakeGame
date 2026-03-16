package controller;

import model.Direction;
import model.Model;
import view.IViewImplementation;
import view.IGameView;
import view.IGameCanvas;
import view.IRenderCallback;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import observer.IGameListener;

public final class Controller implements ActionListener, IGameListener, IGameView, IRenderCallback {
    private static final int TICK_DELAY = 100;
    private final Model model;
    private final IViewImplementation view;
    private Timer timer;

    public Controller(Model model, IViewImplementation view) {
        this.model = model;
        this.view = view;
        this.model.register(this);
        this.view.add((IGameView) this);
        this.view.add((IRenderCallback) this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        model.update();
    }

    @Override
    public void onTick() {
        view.render();
    }

    @Override
    public void onGameOver() {
        timer.stop();
        view.conclude();
    }

    @Override
    public void onStartRequested() {
        view.start();
        timer = new Timer(TICK_DELAY, this);
        timer.start();
    }

    @Override
    public void onRestartRequested() {
        model.reset();
        view.focus();
        view.start();
        timer = new Timer(TICK_DELAY, this);
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