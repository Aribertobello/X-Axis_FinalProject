package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.Loot;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Mapp extends Pane {
    protected ArrayList<Fighter> people = new ArrayList<Fighter>();
    protected ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    protected int scale;
    @Getter
    @Setter
    protected Projectile activeProjectile;
    @Getter
    @Setter
    protected short buffer = 0;
    protected Loot loot;
    protected Circle earth;
    public Vector[] environmentForces = {new Vector(0,9.8),new Vector(0,-4)};

    public Mapp(String type){
        if(type.equalsIgnoreCase("earth")){
            this.setStyle("-fx-background-color: #bce1f5;");
            earth = new Circle(540,673100640,673100000, Color.SEAGREEN);
            this.getChildren().add(earth);
        }
        if(type.equalsIgnoreCase("space")){
            this.setStyle("-fx-background-color: Black;");
            earth = new Circle(900,600,120, Color.CORNFLOWERBLUE);
            this.getChildren().add(earth);
        }
    }
    protected void update(double dt){
        if(activeProjectile!=null) {
            activeProjectile.move(dt);
            buffer++;
            if(buffer>10) {
                if (activeProjectile instanceof Grenade) {
                    if (((Grenade) activeProjectile).getFuseTimer() <= 0) {
                        explosion(activeProjectile.getCoordinate());
                        checkCollision(activeProjectile);
                    }
                } else {
                    checkCollision(activeProjectile);
                }
            }
        }
        else {
            buffer = 0;
        }
    }
    public void addFighter(Fighter fighter){
        people.add(fighter);
        getChildren().add(fighter);
    }
    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
        getChildren().add(obstacle);
    }
    public void addForces(Projectile projectile){}
    public Boolean checkCollision(Projectile projectile){
        for (Obstacle obstacle : obstacles){
            if(projectile.hitBox().overlaps(obstacle.hitBox())){
                activeProjectile.bounce(obstacle.hitBox());
                return true;
            }
        }
        for(Fighter fighter : people){
            if(projectile.hitBox().overlaps(fighter.hitBox())){
                fighter.setHealth(fighter.getHealth()-projectile.getDamage());
                this.getChildren().remove(activeProjectile);
                activeProjectile = null;
                if(fighter.getHealth()<=0){
                    people.remove(fighter);
                    this.getChildren().remove(fighter);
                }
                return true;
            }
        }
        if(loot!=null&&projectile.hitBox().overlaps(loot.hitBox())){
            this.getChildren().remove(loot);
            loot = null;
            //TODO Game Won
        }
        return false;
    }
    protected void explosion(Coordinate coordinate){
        activeProjectile.setVelocity(new Vector(0,0));
    }
}
