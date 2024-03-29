package com.example.bulletbattleground.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
public class Vector {
    @Getter
    @Setter
    protected double x;
    @Getter
    @Setter
    protected double y;


    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector unitVector() {
        return new Vector(x / magnitude(), y / magnitude());
    }

    public Vector normal() {
        return new Vector(-y, x);
    }

    public static double dotProduct(Vector vector1, Vector vector2) {
        double x = vector1.getX()*vector2.getX();
        double y = vector1.getY()* vector2.getY();
        return x+y;
    }

    public static Vector crossProduct(Vector vector1, Vector vector2) {
        return null;
    }

    public static Vector vectorSum(Vector... Vectors) {
        double sumX = 0;
        double sumY = 0;
        for (Vector vector : Vectors) {
            sumX = sumX + vector.x;
            sumY = sumY + vector.y;
        }
        return new Vector(sumX, sumY);
    }

    public double angle() {
        if (x >= 0) {
            return 180 * Math.atan(y / x) / Math.PI;
        } else {
            return 180 * Math.atan(y / x) / Math.PI + 180;
        }
    }

    public Vector scale(double magnitude) {
        double scale = magnitude / this.magnitude();
        return this.multiply(Math.abs(scale));
    }

    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector projectOver(Vector vector){
        Vector Reflexion = vector.unitVector().multiply(dotProduct(this,vector.unitVector()));
        return Reflexion;
    }

    public Vector rotate(double angle) {
        return new Vector(
                x * Math.cos(angle) - y * Math.sin(angle / Math.PI * 180)
                , x * Math.sin(angle) + y * Math.cos(angle / Math.PI * 180));
    }

    @Override
    public String toString() {
        return '<' +
                " " + x +
                ", " + y +
                " >" ;
    }

}
