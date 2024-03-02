package com.example.bulletbattleground.gameObjects.fighters;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Projectile;

public class Ally extends Fighter {


    public Ally(int coordinateX, int coordinateY){
        this.coordinate.setX(coordinateX);
        this.coordinate.setY(coordinateY);
    }



    protected void LaunchProjectile(Projectile projectile){}
}
