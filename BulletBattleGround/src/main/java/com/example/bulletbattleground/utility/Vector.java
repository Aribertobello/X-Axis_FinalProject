package com.example.bulletbattleground.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;

@AllArgsConstructor
public class Vector {
    @Getter
    @Setter
    protected double x;
    @Getter
    @Setter
    protected double y;

    public static Vector valueOf(String string) throws ParseException {

        ParseException e = new ParseException("Coordinate String is incorrectly formatted, must be of type \"<x,y>\"",0);
        if (string.startsWith("<") && string.endsWith(">") && string.contains(",")) {
            string = string.substring(1, string.length() - 1);
            String[] coordinates = string.split(",");
            if (coordinates.length == 2) {
                try {
                    double x = Double.parseDouble(coordinates[0]);
                    double y = Double.parseDouble(coordinates[1]);
                    return new Vector(x, y);
                } catch (NumberFormatException exception) {
                    throw e;
                }
            }
        }
        throw e;
    }


    /**
     * calculates the magnitude of this Vector object
     * follows formula sqrt(x^2+y^2)
     * @return magnitude of this Vector
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Calculates the unit Vector of this vector object
     * the unit vector is a vector in the same direction as this vector but with a magnitude of 1
     * follows formula unitX  = x/magnitude unitY = y/magnitude
     * @return new Vector <x/magnitude , y/magnitude>
     */
    public Vector unitVector() {
        return new Vector(x / magnitude(), y / magnitude());
    }

    /**
     * calculates the normal of this vector with the same magnitude
     * the normal is a vector orthogonal to a given vector or plane
     * follows formula normalX = -y  normalY = x
     * @return a Vector orthogonal this one with same magnitude
     */
    public Vector normal() {
        return new Vector(-y, x);
    }

    /**
     * static
     * calculates the dot product of 2 given vectors
     * follws  formula x1*x2+y1*y2
     * @param vector1 vector to be dotted
     * @param vector2 vector to be dotted
     * @return dot product of the vectors
     */
    public static double dotProduct(Vector vector1, Vector vector2) {
        double x = vector1.getX()*vector2.getX();
        double y = vector1.getY()* vector2.getY();
        return x+y;
    }

    /**
     * static
     * calculates the sum of an array of vectors
     * follows formula sumX = x1+x2+x3...   sumY = y1+y2+y3...
     * @param Vectors given array of vectors to be summed
     * @return new Vector equivalent to the sum of the given vectors <sumX , sumY>
     */
    public static Vector vectorSum(Vector... Vectors) {
        double sumX = 0;
        double sumY = 0;
        for (Vector vector : Vectors) {
            sumX = sumX + vector.x;
            sumY = sumY + vector.y;
        }
        return new Vector(sumX, sumY);
    }

    /**
     *Calculates the angle of this given vector
     * if the x component of the vector is negative it uses the formula arctan(y/x)+180
     * if the x component is positive it uses the formula arctan(y/x)
     * result is returned in degrees
     * @return angle of direction of the object
     */
    public double angle() {
        if (x >= 0) {
            return 180 * Math.atan(y / x) / Math.PI;
        } else {
            return 180 * Math.atan(y / x) / Math.PI + 180;
        }
    }

    /**
     * calculates a vector with same direction as this vector that has been scaled to a given magnitude
     * changes x and y components in order to fit new magnitude
     * follows formula scaledX = x*(given magnitude/object magnitude)   scaledY = y*(given magnitude/object magnitude)
     * @param newMagnitude magnitude of the returned vector
     * @return scaled vector
     */
    public Vector scale(double newMagnitude) {
        double scale = newMagnitude / this.magnitude();
        return this.multiply(scale);
    }

    /**
     * multiplies a vector by a given scalar
     * follows formula multipliedX = x*scalar  multipliedY = y*scalar
     * @param scalar scalar by which the vector is multiplied
     * @return muliplied vector
     */
    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector projectOver(Vector vector){
        Vector Reflexion = vector.unitVector().multiply(dotProduct(this,vector.unitVector()));
        return Reflexion;
    }

    /**
     * calculates a vector with the same magnitude as the object that has been rotated by a given angle.
     * angle should be in degrees
     * vector is rotated counterclockwise
     * follows formula rotatedX = x * cos(angle) - y * sin(angle)
     * rotaedY = x * Math.sin(angle) + y * cos(angle);
     * @param angle nagle by which the vector will be rotated
     * @return rotated vector
     */
    public Vector rotate(double angle) {
        return new Vector(
                x * Math.cos(angle / 180 * Math.PI ) - y * Math.sin(angle / 180 * Math.PI )
                , x * Math.sin(angle / 180 * Math.PI ) + y * Math.cos(angle / 180 * Math.PI ));
    }
    public double slope(){
        return y/x;
    }

    /**
     * generates a string representing the vector
     * uses standard vector notation: <x , y>
     * @return vector as a string
     */
    @Override
    public String toString() {
        return '<' +
                " " + x +
                ", " + y +
                " >" ;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vector vector = (Vector) object;
        return Double.compare(x, vector.x) == 0 && Double.compare(y, vector.y) == 0;
    }
}
