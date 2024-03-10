package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.Loot;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Mapp extends Pane {
    protected ArrayList<Fighter> people = new ArrayList<Fighter>();
    protected ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    protected int scale;
    protected Projectile activeProjectile;
    protected com.example.bulletbattleground.gameObjects.Loot Loot;
    protected Loot loot;
    protected Circle earth;

    public Mapp(String type){
        if(type.equalsIgnoreCase("earth")){
            earth = new Circle(540,673100640,673100000, Color.SEAGREEN);
            this.getChildren().add(earth);
        }
        if(type.equalsIgnoreCase("space")){
            this.setStyle("-fx-background-color: black;");
            earth = new Circle(900,600,120, Color.CORNFLOWERBLUE);
            this.getChildren().add(earth);
        }
    }
    protected void update(){
    }
    public void addFighter(Fighter fighter){
        people.add(fighter);
        getChildren().add(fighter);
    }
    public void addObstacle(Obstacle Obstacle){
        getChildren().add(Obstacle);
    }
    public void addForces(Projectile projectile){}
    public Boolean checkCollision(Projectile projectile){
        return null;
    }
    protected void explosion(Coordinate coordinate){}
}
