package com.example.bulletbattleground.utility;

import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public abstract class MovingBody extends Group {
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
    @Getter
    protected HitBox hitBox;   //Keep protected
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
    public void setCoordinate(double x, double y){
        coordinate.setX(x);
        coordinate.setY(y);
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

    public abstract HitBox hitBox();

    public static void collision(MovingBody a,MovingBody b) {

        int index  = b.getHitBox().getPoints().indexOf(b.getHitBox().getOverlapped());
        Coordinate point1;
        Coordinate point2;


        if (index+1==b.hitBox.getPoints().size()){
            point1 = b.hitBox.getPoints().get(index-1);
            point2 = b.hitBox.getPoints().get(0);
        } else if (index==0){
            point1 = b.hitBox.getPoints().get(b.hitBox.getPoints().size());
            point2 = b.hitBox.getPoints().get(1);
        } else {
            point1 = b.hitBox.getPoints().get(index-1);
            point2 = b.hitBox.getPoints().get(index+1);
        }
        Vector aReflexion = Vector.vectorSum(a.velocity(),a.velocity().projectOver(point1.distanceVector(point2).normal()).multiply(-2));

        double x1;
        double y1;
        double x2;
        double y2;
        double m1 = a.getMass();
        double m2 = b.getMass();
        double k1 = a.kE();
        double k2 = b.kE();
        double p1 = a.momentum();
        double p2 = b.momentum();
        double eDissipated = 0;
        double eFinal = k1 + k2 - eDissipated;
        double v1 = (sqrt(2 * eFinal * pow(m1, 2) + 2 * eFinal * m2 * m1 - m1 * pow(p1, 2) - m1 * pow(p2, 2) - 2 * m1 * p1 * p2) / sqrt(m2) + (m1 * p1) / m2 + (m1 * p2) / m2) / (pow(m1, 2) / m2 + m1);
        double v2 = (p1+p2-m1*v1)/m1;
        a.getCoordinate().displace(aReflexion.unitVector());
        a.setVelocityX(aReflexion.scale(v1).getX());
        a.setVelocityY(aReflexion.scale( v1).getY());
        //a.setVelocityX(bReflexion.scale(-v2).getX()); TODO V2
        // a.setVelocityY(bReflexion.scale(-v2).getY()); TODO V2
        a.bounce(b.hitBox());
        b.bounce(a.hitBox());
    }

}