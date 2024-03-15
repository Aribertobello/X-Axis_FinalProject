package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.MovingBody;
import com.example.bulletbattleground.utility.Vector;
import lombok.Getter;
import lombok.Setter;

public class Projectile extends MovingBody {
    @Setter
    @Getter
    protected Coordinate coordinate;
    @Setter
    @Getter
    protected int damage;
    @Setter
    @Getter
    protected Vector lift;
    public void bounce(HitBox hitBox){
        this.setVelocity(new Vector(-this.velocityX,velocityY));
    }
    protected void move(double time){}
    protected void release(){}

    public void setVelocity(Vector vector) {
        this.velocityX = vector.getX();
        this.velocityY = vector.getY();
    }

    @Override
    public HitBox hitBox() {
        return null;
    }
}
