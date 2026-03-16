package model.visitor;

import model.IRenderable;

public interface IGameVisitor {
    void visit(IRenderable entity, int radius);
    void spotlight(IRenderable entity, int radius);
    void count(int score);
}