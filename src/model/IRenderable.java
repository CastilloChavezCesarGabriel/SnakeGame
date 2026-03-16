package model;

import utilities.IPositionVisitor;

public interface IRenderable {
    void iterate(IPositionVisitor visitor);
}