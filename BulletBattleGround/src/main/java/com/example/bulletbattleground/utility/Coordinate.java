package com.example.bulletbattleground.utility;

import lombok.Getter;
import lombok.Setter;

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
}
