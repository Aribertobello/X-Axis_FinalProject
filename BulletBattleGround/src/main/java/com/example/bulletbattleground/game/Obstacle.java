package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

public abstract class Obstacle extends Group{

    @Getter
    @Setter
    protected Vector Velocity;
    @Getter
    @Setter
    protected Coordinate coordinate;

    protected boolean ispenetrable;
    protected abstract HitBox hitBox();

    protected abstract void move();
    protected abstract void setX(double x);
    protected abstract void setY(double y);
}
