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

    public double magnitude(){
        return Math.sqrt(x*x+y*y);
    }
    public Vector unitVector(){
        return null;
    }
    public Vector normal(){
        return null;
    }
    public static double dotProduct(Vector...Vectors){
        return 0;
    }
    public static Vector crossProduct(Vector vector1, Vector vector2){
        return null;
    }
    public static Vector vectorSum(Vector...Vectors){
        double sumX = 0;
        double sumY = 0;
        for(Vector vector : Vectors){
            sumX = sumX + vector.x;
            sumY = sumY + vector.y;
        }
        return new Vector(sumX,sumY);
    }

    public double angle(){
        return 180*Math.atan(y/x)/Math.PI;
    }
    @Override
    public String toString() {
        return '<' +
                " " + x +
                ", " + y +
                '>';
    }

}
