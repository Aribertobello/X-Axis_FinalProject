package com.example.bulletbattleground.game;

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

    protected Circle floor;



    public Mapp(){
        floor = new Circle(0,6731000,6371000-500, Color.SADDLEBROWN);
        this.getChildren().add(floor);
    }
    public Mapp(String type){
        if(type.equalsIgnoreCase("earth")){
            floor = new Circle(6731000,63710000,63710000-900, Color.GREY);

        }
        if(type.equalsIgnoreCase("space")){
            floor = new Circle(6731000,63710000,63710000, Color.GREY);
        }
    }





    protected void update(){



    }
    public void addFighter(Fighter fighter){}
    public void addObstacle(Obstacle Obstacle){
        getChildren().add(Obstacle);
        System.out.println(getChildren().get(0));
    }
    public void addForces(Projectile projectile){}
    public Boolean checkCollision(Projectile projectile){
        return null;
    }
    protected void explosion(Coordinate coordinate){}

}
