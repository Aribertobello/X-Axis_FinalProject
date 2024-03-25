package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.MovingBody;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

public abstract class Obstacle extends MovingBody {

    protected boolean ispenetrable;

    /**
     *
     * @param dt
     */
    public void move(double dt) {
        setX(getVelocityX() * dt + getCoordinate().getX());
        setY(getVelocityY() * dt + getCoordinate().getY());
    }

    /**
     *
     * @return
     */
    public abstract HitBox hitBox();

    /**
     *
     * @param x
     */
    protected abstract void setX(double x);

    /**
     *
     * @param y
     */
    protected abstract void setY(double y);
}
