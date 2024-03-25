package com.example.bulletbattleground.utility;

import com.example.bulletbattleground.MathematicsInterface;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public abstract class MovingBody extends Group implements MathematicsInterface {
    @Setter
    @Getter
    private Coordinate coordinate;
    @Setter
    @Getter
    private double velocityX;
    @Setter
    @Getter
    private double velocityY;
    @Setter
    @Getter
    private double mass;
    public ArrayList<Vector> forces = new ArrayList<>();

    /**
     * calculates the net force on the object
     * sums the forces in ArrayList:forces  and returns a net force
     * calls Vector.vectorSum to sum the force vectors
     * @return the net force acting on the object
     */
    public Vector netForce() {
        return Vector.vectorSum(forces.toArray(new Vector[forces.size()]));
    }

    /**
     *
     * @return
     */
    public Vector acceleration() {
        forces.toArray(new Vector[2]);
        return new Vector(netForce().getX() / mass, netForce().getY() / mass);
    }

    /**
     *
     * @return
     */
    public Vector velocity() {
        return new Vector(velocityX, velocityY);
    }

    /**
     *
     * @return
     */
    public double kE() {
        return mass * Math.pow(velocity().magnitude(), 2) / 2;
    }

    /**
     *
     * @return
     */
    public double momentum() {
        return mass * velocity().magnitude();
    }

    /**]
     *
     * @param dt
     */
    public abstract void move(double dt);

    /**
     *
     * @param hitBox
     */
    public abstract void bounce(HitBox hitBox);
}
