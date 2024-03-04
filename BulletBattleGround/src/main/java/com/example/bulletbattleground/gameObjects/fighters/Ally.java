package com.example.bulletbattleground.gameObjects.fighters;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Projectile;
import javafx.scene.paint.Color;

public class Ally extends Fighter {


    public Ally(int coordinateX, int coordinateY){
        super(coordinateX,coordinateY);
        this.setFill(Color.ROYALBLUE);

    }



    protected void LaunchProjectile(Projectile projectile){}
}
