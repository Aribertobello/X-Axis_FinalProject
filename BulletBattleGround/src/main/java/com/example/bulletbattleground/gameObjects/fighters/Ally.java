package com.example.bulletbattleground.gameObjects.fighters;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.gameObjects.projectiles.Rocket;
import com.example.bulletbattleground.utility.BattleGroundObject;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.HashMap;

public class Ally extends Fighter {

    /**
     *
     * @param coordinateX
     * @param coordinateY
     * @param type
     */
    public Ally(int type, int health, int coordinateX, int coordinateY) {
        super( type, health, coordinateX, coordinateY);
        Image ally_Image = new Image("file:Files/img/Light_Class_Img.png");
        this.setFill(new ImagePattern(ally_Image));
        setHealth(health);
    }
}
