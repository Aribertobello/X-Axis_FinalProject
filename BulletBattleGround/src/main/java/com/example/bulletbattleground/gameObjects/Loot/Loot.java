package com.example.bulletbattleground.gameObjects.Loot;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

public class Loot extends Rectangle {

    public Loot(int coordinateX, int coordinateY) {
        super(Fighter.FIGHTER_DIMENSIONS*2, Fighter.FIGHTER_DIMENSIONS*2);
        this.setFill(Color.GOLD);
        coordinate = new Coordinate(coordinateX, coordinateY);
        this.setLayoutX(coordinateX);
        this.setLayoutY(coordinateY);
        Image Lootimg = new Image("file:Files/img/Loot.png");
        this.setFill(new ImagePattern(Lootimg));
    }

    @Getter
    @Setter
    protected Coordinate coordinate;

    public HitBox hitBox() {
        return new HitBox(this);
    }

}
