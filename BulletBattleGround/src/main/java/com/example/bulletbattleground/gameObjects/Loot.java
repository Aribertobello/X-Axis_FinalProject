package com.example.bulletbattleground.gameObjects;

import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

public class Loot extends Rectangle {
    public Loot(int coordinateX,int coordinateY){
        super(40,40);
        this.setFill(Color.GOLD);
        coordinate = new Coordinate(coordinateX,coordinateY);
        this.setLayoutX(coordinateX);
        this.setLayoutY(coordinateY);
    }
    @Getter
    @Setter
    protected Coordinate coordinate;
    public HitBox hitBox(){return new HitBox(this);};
}
