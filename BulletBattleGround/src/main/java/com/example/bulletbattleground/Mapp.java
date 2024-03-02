package com.example.bulletbattleground;

import java.util.ArrayList;

public class Mapp {
    protected ArrayList<Fighter> people = new ArrayList<Fighter>();
    protected ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    protected int scale;
    protected Projectile activeProjectile;
    protected Loot Loot;

    protected void update(){}
    protected void addFighter(){}
    protected void addObstacle(){}
    protected void addForces(Projectile projectile){}
    protected Boolean checkCollision(Projectile projectile){
        return null;
    }
    protected void explosion(Coordinate coordinate){}

}
