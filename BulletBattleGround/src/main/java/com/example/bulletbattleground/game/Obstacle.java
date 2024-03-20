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
    public void move(double dt){
        setX(getVelocityX() * dt + getCoordinate().getX());
        setY(getVelocityY() * dt + getCoordinate().getY());
    }
    public abstract HitBox hitBox();
    protected abstract void setX(double x);
    protected abstract void setY(double y);
}
