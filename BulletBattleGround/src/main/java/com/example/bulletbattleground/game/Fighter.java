package com.example.bulletbattleground.game;

import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

public class Fighter extends Rectangle {

    @Getter
    @Setter
    protected Loadout loadout;
    @Getter
    @Setter
    protected int health;
    @Getter
    @Setter
    protected Coordinate coordinate = new Coordinate(0,0);
    public Fighter(int coordinateX, int coordinateY){
        super(40,40);
        loadout = new Loadout("light");
        coordinate.setX(coordinateX);
        coordinate.setY(coordinateY);
        this.setLayoutX(coordinateX-20);
        this.setLayoutY(coordinateY-20);
        hitBox();
    }
    public HitBox hitBox(){
        return new HitBox(this);
    }
    public HBox headsUpDisplay(){
        return this.loadout.display();
    }
}
