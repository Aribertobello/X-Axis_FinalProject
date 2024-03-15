package com.example.bulletbattleground.gameObjects.fighters;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Ally extends Fighter {


    public Ally(int coordinateX, int coordinateY) {
        super(coordinateX, coordinateY);
        this.setFill(Color.ROYALBLUE);
        setHealth(20);
    }
    public void launchProjectile(Projectile projectile, Vector velocity,Coordinate coordinate){
        projectile.setCoordinate(new Coordinate(coordinate.getX(),coordinate.getY()));
        projectile.setVelocity(velocity);
        projectile.forces.clear();
        projectile.forces.add(new Vector(
                ((Mapp)getParent()).environmentForces[0].getX()*projectile.getMass(),((Mapp)getParent()).environmentForces[0].getY()*projectile.getMass()));
        projectile.forces.add(new Vector(
                ((Mapp)getParent()).environmentForces[1].getX()*projectile.getMass(),((Mapp)getParent()).environmentForces[1].getY()*projectile.getMass()));
        if(((Mapp)this.getParent()).getActiveProjectile() == null){
            ((Mapp)this.getParent()).getChildren().add(projectile);
            ((Mapp)this.getParent()).setActiveProjectile(projectile);
        } else {
            ((Mapp)this.getParent()).getChildren().remove(((Mapp)this.getParent()).getActiveProjectile());
            ((Mapp)this.getParent()).getChildren().add(projectile);
            ((Mapp)this.getParent()).setActiveProjectile(projectile);
        }
        ((Mapp)this.getParent()).setBuffer((short)0);
        System.out.println("added projectile to scene"); //TODO Remove in final code
        System.out.println(projectile.netForce().magnitude());
        System.out.println(projectile.netForce().angle());
        projectile.hitBox();
    }
}
