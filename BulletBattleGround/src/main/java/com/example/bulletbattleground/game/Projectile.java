package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.MovingBody;
import com.example.bulletbattleground.utility.Vector;

public class Projectile extends MovingBody {
    protected Coordinate coordinate;
    protected int damage;
    protected HitBox hitBox;
    protected Vector lift;
    protected void move(){}
    protected void release(){}


    public void setVelocity(Vector vector) {
        this.velocityX = vector.getX();
        this.velocityY = vector.getY();
    }
}
