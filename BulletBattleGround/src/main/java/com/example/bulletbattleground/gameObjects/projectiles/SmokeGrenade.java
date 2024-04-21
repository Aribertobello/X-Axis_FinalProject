package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.gameObjects.obstacles.SmokeScreen;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.Getter;


public class SmokeGrenade extends Grenade{

    double fuseTimer;
    double lastPositionX;
    double lastPositionY;
    protected final Image[] explosionEffects = {};


    public SmokeGrenade(){
         super();
         fuseTimer = 0.8;
         setDamage(0);
    }

    @Override
    public void move(double time) {
        super.move(time);
        fuseTimer -= time;
            explode();
    }
    @Override
    public void explode() {
        if(fuseTimer <= 0.0) {
            fuseTimer = Math.sqrt(-1); //Makes sure that the explode method is only called once, or else it gets called every 1ms
            lastPositionX = getChildren().get(0).getLayoutX();
            lastPositionY = getChildren().get(0).getLayoutY();
            SmokeScreen s = new SmokeScreen(400, lastPositionX, lastPositionY);
            s.createSmokeScreen();
            BattleGround.activeGame.getLevel().map.removeActiveProjectile();
            BattleGround.activeGame.getLevel().map.addObstacle(s);
        }
    }

    @Override
    public String toString() {
        return "SmokeGrenade";
    }

    @Override
    public void bounce(HitBox hitBox) {

    }
}
