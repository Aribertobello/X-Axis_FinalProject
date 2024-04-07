package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.MovingBody;
import com.example.bulletbattleground.utility.Vector;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public abstract class Projectile extends MovingBody {

    @Setter
    @Getter
    protected int damage;

    @Setter
    @Getter
    protected Vector lift;


    /**
     *
     * @param time
     */
    public void move(double time) {
        double x = ((acceleration().getX() / 2) * time * time + getVelocityX() * time + getCoordinate().getX());
        double y  = ((acceleration().getY() / 2) * time * time + getVelocityY() * time + getCoordinate().getY());
        this.setVelocityX(acceleration().getX() * time + getVelocityX());
        this.setVelocityY(acceleration().getY() * time + getVelocityY());
        setCoordinate(new Coordinate(x,y));
    }

    /**
     *
     * @param velocity
     * @param coordinate
     * @param Forces
     */
    public void release(Vector velocity, Coordinate coordinate, Vector... Forces) {
        setVelocity(velocity);
        setCoordinate(coordinate);
        forces.clear();
        forces.add(lift);
        forces.addAll(Arrays.asList(Forces));
    }

    /**
     *
     * @param vector
     */
    public void setVelocity(Vector vector) {
        setVelocityX(vector.getX());
        setVelocityY(vector.getY());
    }
    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
        super.setCoordinate(coordinate);
    }
    public HitBox hitBox(){
        hitBox = new HitBox(this);
        return hitBox;
    }

}
