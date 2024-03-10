package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;

public class Grenade extends Projectile {
    protected int fuseTimer;
    protected int radius;

    public void bounce(){}

    @Override
    public String toString() {
        return "Grenade";
    }
}
