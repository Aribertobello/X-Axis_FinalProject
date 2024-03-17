package com.example.bulletbattleground.utility;

import com.example.bulletbattleground.utility.Vector;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public abstract class MovingBody extends Group {
    @Setter
    @Getter
    protected double velocityX;
    @Setter
    @Getter
    protected double velocityY;
    @Setter
    @Getter
    protected double mass;
    public ArrayList<Vector> forces = new ArrayList<>();

    public abstract HitBox hitBox();
    public Vector netForce(){
        return Vector.vectorSum(forces.toArray(new Vector[forces.size()]));
    }
    public Vector acceleration(){
        forces.toArray(new Vector[2]);
        return new Vector(netForce().getX()/mass,netForce().getY()/mass);
    }
    public Vector velocity(){
        return new Vector(velocityX,velocityY);
    }
    public double kE(){
        return mass*Math.pow(velocity().magnitude(),2)/2;
    }

}
