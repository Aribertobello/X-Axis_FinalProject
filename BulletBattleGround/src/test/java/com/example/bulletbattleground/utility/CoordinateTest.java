package com.example.bulletbattleground.utility;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void distance() {
        Coordinate coord1 = new Coordinate(0,0);
        Coordinate coord2 = new Coordinate(-9,0);
        double expectedResult = 9;
        double result = coord1.distance(coord2);
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void distanceVector() {
        Coordinate coord1 = new Coordinate(0,0);
        Coordinate coord2 = new Coordinate(-9,0);
        Vector expectedResult = new Vector(9,0);
        Vector result = coord1.distanceVector(coord2);
        assertEquals(expectedResult, result);
    }
    @Test
    void testRotateAbout() throws ParseException {
        Coordinate coord = new Coordinate(5,0);
        Coordinate axis = new Coordinate(0,0);
        Coordinate expectedResult = new Coordinate(4,3);
        Coordinate result = coord.rotateAbout(36.86,axis);
        assertEquals(expectedResult, result);
    }

    @Test
    void testToString() {
    }
    @Test
    void testValueOf() throws ParseException {
        String str = "(8,9)";
        Coordinate result = Coordinate.valueOf(str);
        Coordinate expectedResult = new Coordinate(8,9);
        assertEquals(expectedResult, result);
    }

}