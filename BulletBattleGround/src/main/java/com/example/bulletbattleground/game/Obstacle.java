package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.Bounds;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.Group;

public abstract class Obstacle extends Group{
    protected Bounds bounds;
    protected Vector Velocity;
    protected Coordinate coordinate;

    protected boolean ispenetrable;

    protected abstract void move();
    protected abstract void setX(double x);
    protected abstract void setY(double y);
}
