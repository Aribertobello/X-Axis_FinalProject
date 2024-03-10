package com.example.bulletbattleground.utility;

public class Coordinate {
    protected double x;
    protected double y;


    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    protected double distance(Coordinate coordinate){
        return 0;
    }

    protected Vector distanceVector(Coordinate coordinate){
        return null;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
