package com.example.bulletbattleground.gameObjects.fighters;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Computer extends Fighter {


    public Computer(int coordinateX, int coordinateY) {
        super(coordinateX, coordinateY);

        //Image Image = new Image("C:\\Users\\aribe\\IdeaProjects\\X-Axis_FinalProject\\BulletBattleGround\\build\\classes\\EnemyImage.png");
        //this.setFill(new ImagePattern(Image));
    }

    protected Vector calculateLaunchAngle(){
        return null;
    }
    protected void LaunchProjectile(Projectile projectile){}
}
