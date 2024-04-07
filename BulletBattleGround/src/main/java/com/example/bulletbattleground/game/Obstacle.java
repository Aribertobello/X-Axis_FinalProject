package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.*;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

public abstract class Obstacle extends MovingBody implements BattleGroundObject {

    protected boolean ispenetrable;

    /**
     *
     * @param dt
     */
    public void move(double dt) {
        setCoordinate(getVelocityX() * dt + getCoordinate().getX(),getVelocityY() * dt + getCoordinate().getY());
    }
    @Override
    public void setCoordinate(Coordinate coordinate){
        super.setCoordinate(coordinate);
        allign();
    }
    @Override
    public void setCoordinate(double x, double y){
        super.setCoordinate(x,y);
        allign();
    }
    public HitBox hitBox(){
        hitBox = new HitBox(this);
        return hitBox;
    }
}
