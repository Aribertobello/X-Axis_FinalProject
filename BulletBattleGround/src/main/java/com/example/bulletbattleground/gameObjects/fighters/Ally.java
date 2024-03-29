package com.example.bulletbattleground.gameObjects.fighters;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.gameObjects.projectiles.Rocket;
import com.example.bulletbattleground.utility.BattleGroundObject;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.HashMap;

public class Ally extends Fighter implements BattleGroundObject {

    public Ally(int coordinateX, int coordinateY, int type) {
        super(coordinateX, coordinateY, type);
        Image ally_Image = new Image("file:Light_Class_Img.png");
        this.setFill(new ImagePattern(ally_Image));
        setHealth(20);
    }

    public void launchProjectile(Projectile projectile, Vector velocity, Coordinate coordinate) {
        projectile.release(velocity, new Coordinate(coordinate.getX(), coordinate.getY()));
        ((Mapp) getParent()).addForces(projectile);

        if (((Mapp) this.getParent()).getActiveProjectile() == null) {
            ((Mapp) this.getParent()).getChildren().add(projectile);
            ((Mapp) this.getParent()).setActiveProjectile(projectile);
        } else {
            ((Mapp) this.getParent()).getChildren().remove(((Mapp) this.getParent()).getActiveProjectile());
            ((Mapp) this.getParent()).getChildren().add(projectile);
            ((Mapp) this.getParent()).setActiveProjectile(projectile);
        }

        ((Mapp) this.getParent()).setBuffer(0);

        if (projectile instanceof Rocket) {
            Vector a = projectile.acceleration();
            double angle = projectile.velocity().angle();
            Vector v = projectile.velocity();
            double vx = v.getX();
            double ax = a.getX();
            double ay = a.getY();
            double tan = Math.tan(Math.PI*(angle/180));
            double dropZone = 220+(2*vx*vx*tan*(ax*tan-ay)/(ay*ay));
             ((Rocket)projectile).setDropZone(dropZone);

            /*((Rocket) projectile).setDropZone(
                    projectile.getCoordinate().getX()
                            + 2 * Math.pow(projectile.getVelocityX(), 2)
                            * Math.tan(Math.PI / 180 * projectile.velocity().angle())
                            * (projectile.acceleration().getX() * Math.tan(Math.PI / 180 * projectile.velocity().angle())
                            - projectile.acceleration().getY())
                            / Math.pow(projectile.acceleration().getY(), 2));*/

            if (((Rocket) projectile).getDropZone() >= 1915) {
                ((Rocket) projectile).setDropZone(1915);
            }

            if (((Rocket) projectile).getDropZone() <= 5) {
                ((Rocket) projectile).setDropZone(5);
            }

        }

        System.out.println("added projectile to scene"); //TODO Remove in final code
        System.out.println(projectile.netForce().magnitude());
        System.out.println(projectile.netForce().angle());

    }
}
