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
    }
    public void launchProjectile(Projectile projectile, Vector velocity, Coordinate coordinate){
        projectile.setCoordinate(new Coordinate(this.coordinate.getX()+this.getWidth(),this.coordinate.getY()));
        projectile.setVelocity(velocity);
        if(((Mapp)this.getParent()).getActiveProjectile() == null){
            ((Mapp)this.getParent()).getChildren().add(projectile);
            ((Mapp)this.getParent()).setActiveProjectile(projectile);
        }
        System.out.println("added projectile to scene"); //TODO Remove in final code
    }
}
