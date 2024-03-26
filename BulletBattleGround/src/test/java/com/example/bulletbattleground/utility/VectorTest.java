package com.example.bulletbattleground.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void magnitude() {
        Vector vector1 = new Vector(-3,4);
        double expectedResult = 5;
        double result = vector1.magnitude();
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void unitVector() {
        Vector vector1 = new Vector(-20,2);
        Vector vector2 = new Vector(-9,0);
    }

    @Test
    void normal() {
        Vector vector1 = new Vector(-5,9000);
        Vector vector2 = new Vector(-9,0);
    }

    @Test
    void dotProduct1() {
        Vector vector1 = new Vector(-5,9000);
        Vector vector2 = new Vector(-9,0);

        double expectedResult = 45;
        double result = Vector.dotProduct(vector1,vector2);
        assertEquals(expectedResult, result, 0.0001);
    }
    @Test
    void dotProduct2() {
        Vector vector1 = new Vector(1,1);
        Vector vector2 = new Vector(1,1);

        double expectedResult = 2;
        double result = Vector.dotProduct(vector1,vector2);
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void dotProduct3() {
        Vector vector1 = new Vector(12,0);
        Vector vector2 = new Vector(12,0);

        double expectedResult = 144;
        double result = Vector.dotProduct(vector1,vector2);
        assertEquals(expectedResult, result, 0.0001);
    }
    @Test
    void dotProduct4() {
        Vector vector1 = new Vector(0,12);
        Vector vector2 = new Vector(12,0);

        double expectedResult = 0;
        double result = Vector.dotProduct(vector1,vector2);
        assertEquals(expectedResult, result, 0.0001);
    }


    @Test
    void vectorSum() {
        Vector vector1 = new Vector(-5,9000);
        Vector vector2 = new Vector(-9,0);
    }

    @Test
    void angle() {
        Vector vector1 = new Vector(-5,5);

    }

    @Test
    void scale() {
    }

    @Test
    void multiply() {
    }

    @Test
    void rotate() {
    }

    @Test
    void testToString() {
    }
}