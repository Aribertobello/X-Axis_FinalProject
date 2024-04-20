package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.*;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

public abstract class Obstacle extends MovingBody implements BattleGroundObject {

    protected boolean ispenetrable;
    @Getter
    @Setter
    protected double rotationAngle;

    /**
     *
     * @param dt
     */
    public void move(double dt) {
        setCoordinate(getVelocityX() * dt*10 + getCoordinate().getX(),getVelocityY() * dt*10 + getCoordinate().getY());
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
    public void rotate(double angle){
        this.setRotationAngle(angle);
        this.getChildren().get(0).setRotate(angle);
    }
}
