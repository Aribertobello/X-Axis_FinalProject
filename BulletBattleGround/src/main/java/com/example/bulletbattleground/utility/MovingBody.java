package com.example.bulletbattleground.utility;

import com.example.bulletbattleground.utility.Vector;
import javafx.scene.Group;

import java.util.ArrayList;

public abstract class MovingBody extends Group {
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
