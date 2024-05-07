package com.example.bulletbattleground.utility;

import javafx.scene.layout.CornerRadii;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.util.IllegalFormatException;
import java.util.Objects;

@Setter
@Getter
public class Coordinate {

    private double x;
    private double y;

    /**
     * creates a 2D coordinate (x,y)
     * @param x x component
     * @param y y component
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
    public Coordinate rotateAbout(double angle, Coordinate axisOfRotation){
        Vector v = distanceVector(axisOfRotation);
        Vector r  = v.rotate(angle);
        return axisOfRotation.move(distanceVector(axisOfRotation).rotate(angle));
    }

    @Override
    public String toString() {
        return '(' + " " + Math.round(x) + ", " + Math.round(y) + ')';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coordinate otherCoord = (Coordinate) object;
        return Math.round(x - otherCoord.x) == 0 && Math.round(y -otherCoord.y) == 0;
    }

    /**
     * reads a String and creates a Coordinate object from it
     * @throws ParseException when the string is formatted improperly
     * must be formatted as such : "(x,y)"
     * @return the String as a Coordinate
     */
    public static Coordinate valueOf(String string) throws ParseException {
        ParseException e = new ParseException("Coordinate String is incorrectly formatted, must be of type \"(x,y)\"",0);
        if (string.startsWith("(") && string.endsWith(")") && string.contains(",")) {
            string = string.substring(1, string.length() - 1);
            String[] coordinates = string.split(",");
            if (coordinates.length == 2) {
                try {
                    double x = Double.parseDouble(coordinates[0]);
                    double y = Double.parseDouble(coordinates[1]);
                    return new Coordinate(x, y);
                } catch (NumberFormatException exception) {
                    throw e;
                }
            }
        }
        throw e;
    }
}
