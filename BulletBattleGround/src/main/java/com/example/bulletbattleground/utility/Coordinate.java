package com.example.bulletbattleground.utility;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Coordinate {

    protected double x;
    protected double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    protected double distance(Coordinate coordinate){
        return distanceVector(coordinate).magnitude();
    }
    protected Vector distanceVector(Coordinate coordinate){
        return new Vector(this.getX()-coordinate.getX(),this.getY()-coordinate.getY());
    }
    @Override
    public String toString() {
        return '(' + " " + x + ", " + y + ')';
    }
}
