package com.example.bulletbattleground.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Coordinate {

    private double x;
    private double y;

    /**
     *
     * @param x
     * @param y
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void displace(Vector vector){
        setX(x+ vector.getX());
        setY(y+ vector.getY());
    }
    public Coordinate move(Vector vector){
        return new Coordinate(x+ vector.getX(),y+ vector.getY());
    }

    /**
     *
     * @param coordinate
     * @return
     */
    public double distance(Coordinate coordinate) {
        return distanceVector(coordinate).magnitude();
    }

    /**
     *
     * @param coordinate
     * @return
     */
    public Vector distanceVector(Coordinate coordinate) {
        return new Vector(this.getX() - coordinate.getX(), this.getY() - coordinate.getY());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return '(' + " " + x + ", " + y + ')';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coordinate that = (Coordinate) object;
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0;
    }

}
