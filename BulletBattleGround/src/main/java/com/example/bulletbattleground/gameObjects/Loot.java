package com.example.bulletbattleground.gameObjects;

import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.utility.Bounds;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Loot extends Rectangle {



    public Loot(int coordinateX,int coordinateY){
        super(40,40);
        this.setFill(Color.GOLD);
        coordinate = new Coordinate(coordinateX,coordinateY);
        this.setLayoutX(coordinateX);
        this.setLayoutY(coordinateY);
    }

    protected Coordinate coordinate;
    protected Bounds bounds;
}
