package controller;
import model.Model;
import view.View;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

import strategy.IRender;
import observer.IScoreListener;
import observer.IRenderListener;

public class Controller implements ActionListener, KeyListener, IScoreListener, IRenderListener {
    private final Model model;
    private final View view;
    private Timer timer;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.model.addScore(this);
        this.model.addRender(this);
        this.view.addKeyListener(this);
        this.view.requestFocusInWindow();

        if (!view.ask("Start game")) {
            System.exit(0);
        }

        onStart();
    }

    private void onStart() {
        timer = new Timer(100, this);
        timer.start();
    }

    private void onReset() {
        model.reset();
        view.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.update();
        if(model.isGameOver()) {
            timer.stop();
            if (view.ask("Game Over! Try again?")) {
                onReset();
                onStart();
            } else {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        Model.Direction newDirection = switch (event.getKeyCode()) {
            case KeyEvent.VK_UP -> Model.Direction.UP;
            case KeyEvent.VK_DOWN -> Model.Direction.DOWN;
            case KeyEvent.VK_LEFT -> Model.Direction.LEFT;
            case KeyEvent.VK_RIGHT -> Model.Direction.RIGHT;
            default -> null;
        };

        if (newDirection != null) {
            model.change(newDirection);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void onScoreChanged(int newScore) {
        view.updateScore(newScore);
    }

    @Override
    public void onRender(List<IRender> renderables) {
        view.render(renderables);
    }
}
