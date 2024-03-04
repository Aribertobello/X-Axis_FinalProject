package com.example.bulletbattleground.utility;

import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Bounds extends Group {

    public Bounds(){
        this.setVisible(false);
    }
    protected ArrayList<Line> border = new ArrayList<Line>();
    protected boolean displayed;
    protected boolean overlaps(Bounds bounds){
        return false;
    }
    protected void setDisplayed(){}

}
