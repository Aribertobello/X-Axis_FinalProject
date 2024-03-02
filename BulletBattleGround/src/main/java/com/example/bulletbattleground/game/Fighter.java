package com.example.bulletbattleground.game;

import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.utility.Bounds;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Fighter extends Rectangle {
    protected Bounds bounds;
    protected Loadout loadout;
    protected int health;
    protected Coordinate coordinate;


    public HBox headsUpDisplay(){
        return this.loadout.display();
    }

}
