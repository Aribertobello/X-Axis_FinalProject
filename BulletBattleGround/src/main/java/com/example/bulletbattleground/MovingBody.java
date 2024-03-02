package com.example.bulletbattleground;

import java.util.ArrayList;

public abstract class MovingBody {
    protected double velocityX;
    protected double velocityY;
    protected ArrayList<Vector> forces = new ArrayList<Vector>();
    protected double mass;
    protected Vector netForce(){
        return null;
    }
    protected Vector acceleration(){
        return null;
    }
    protected Vector Velocity(){
        return null;
    }
    protected double KE(){
        return 0;
    }
}
