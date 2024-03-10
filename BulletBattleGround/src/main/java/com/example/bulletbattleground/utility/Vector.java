package com.example.bulletbattleground.utility;

public class Vector {
    protected double x;
    protected double y;

    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    protected double magnitude(){
        return 0;
    }
    protected Vector unitVector(){
        return null;
    }
    protected Vector normal(){
        return null;
    }
    protected static double dotProduct(Vector...Vectors){
        return 0;
    }
    protected static Vector crossProduct(Vector vector1, Vector vector2){
        return null;
    }
    protected static Vector vectorSum(Vector...Vectors){
        return null;
    }
    protected double angle(){
        return 0;
    }

}
