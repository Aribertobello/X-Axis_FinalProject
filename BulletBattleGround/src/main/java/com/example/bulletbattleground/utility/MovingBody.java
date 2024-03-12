package com.example.bulletbattleground.utility;

import com.example.bulletbattleground.utility.Vector;
import javafx.scene.Group;

import java.util.ArrayList;

public abstract class MovingBody extends Group {
    protected double velocityX;
    protected double velocityY;
    public ArrayList<Vector> forces = new ArrayList<>();
    public double mass;
    protected Vector netForce(){
        return Vector.vectorSum(forces.toArray(new Vector[forces.size()]));
    }
    protected Vector acceleration(){
        forces.toArray(new Vector[2]);
        return new Vector(netForce().getX()/mass,netForce().getY()/mass);
    }
    protected Vector Velocity(){
        return new Vector(velocityX,velocityY);
    }
    protected double kE(){
        return 0;
    }

    public double getVelocityX() { return velocityX; }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}
