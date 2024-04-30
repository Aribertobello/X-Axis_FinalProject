package com.example.bulletbattleground.utility;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Spear;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class Arrow extends Polyline {
    Coordinate ultimateCoord = null;
    Coordinate penUltimateCoord = null;
    public Arrow(){
        super();
        this.setStroke(Color.WHITE);
    }
    public Arrow(Vector vector, Coordinate origin){
        super();
        this.setStroke(Color.WHITE);
        this.getPoints().addAll(origin.getX(),origin.getY(),origin.move(vector).getX(),origin.move(vector).getY());
        penUltimateCoord = origin;
        ultimateCoord = origin.move(vector);
        addTip();
    }
    public void updateDrag(Fighter fighter, Mapp map , double dt, Coordinate coordinate, Vector direction){

        Projectile projectile;
        this.getPoints().clear();
        this.getPoints().addAll(coordinate.getX(),coordinate.getY());
        map.getChildren().remove(this);
        switch(fighter.getLoadout().getType()){
            case 1 -> projectile = new Bullet();
            case 2 -> projectile = new Spear();
            default -> projectile = new Bullet();
        }
        projectile.setVelocity(direction);
        projectile.setCoordinate(coordinate);

        for(double T = 0 ; T < 19; T += 5*dt){
            if(fighter.getLoadout().getType()==3){
                projectile.forces.clear();
                projectile.forces.add(new Vector(0,4.9).multiply(projectile.getMass()));
            }
            projectile.move(2*dt);
            map.addForces(projectile);
            this.getPoints().addAll(projectile.getCoordinate().getX(),projectile.getCoordinate().getY());
            penUltimateCoord = ultimateCoord;
            ultimateCoord = projectile.getCoordinate();

        }
        map.getChildren().add(this);
        addTip();
    }
    public void addTip(){
        if(!this.getPoints().isEmpty()){
            Vector slopeVector = ultimateCoord.distanceVector(penUltimateCoord).scale(5);
            getPoints().addAll(ultimateCoord.move(slopeVector.rotate(90)).getX(), ultimateCoord.move(slopeVector.rotate(90)).getY());
            getPoints().addAll(ultimateCoord.move(slopeVector.rotate(-90)).getX(), ultimateCoord.move(slopeVector.rotate(-90)).getY());
            getPoints().addAll(ultimateCoord.move(slopeVector.multiply(2)).getX(), ultimateCoord.move(slopeVector.multiply(2)).getY());
            getPoints().addAll(ultimateCoord.move(slopeVector.rotate(90)).getX(), ultimateCoord.move(slopeVector.rotate(90)).getY());
        }
    }
}
