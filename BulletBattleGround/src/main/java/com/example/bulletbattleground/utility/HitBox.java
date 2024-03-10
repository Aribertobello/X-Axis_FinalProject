package com.example.bulletbattleground.utility;

import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class HitBox extends Group {

    public HitBox(){
        this.setVisible(false);
    }
    protected ArrayList<Line> border = new ArrayList<Line>();
    protected boolean displayed;
    protected boolean overlaps(HitBox HitBox){
        return false;
    }
    protected void setDisplayed(){}

}
