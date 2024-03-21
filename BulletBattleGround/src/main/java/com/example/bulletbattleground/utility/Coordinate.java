package com.example.bulletbattleground.utility;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Coordinate {

    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Coordinate coordinate) {
        return distanceVector(coordinate).magnitude();
    }

    public Vector distanceVector(Coordinate coordinate) {
        return new Vector(this.getX() - coordinate.getX(), this.getY() - coordinate.getY());
    }

    @Override
    public String toString() {
        return '(' + " " + x + ", " + y + ')';
    }
}
