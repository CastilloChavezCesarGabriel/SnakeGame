package controller;

import model.IRenderable;
import model.visitor.IGameVisitor;
import view.IGameCanvas;

public final class GameRenderer implements IGameVisitor {
    private final IGameCanvas canvas;

    public GameRenderer(IGameCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void visit(IRenderable entity, int radius) {
        entity.iterate(canvas::render);
    }

    @Override
    public void spotlight(IRenderable entity, int radius) {
        entity.iterate(canvas::highlight);
    }

    @Override
    public void tally(int score) {
        canvas.inscribe(score);
    }
}