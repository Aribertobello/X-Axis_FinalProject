package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.MovingBody;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Mapp extends Pane {

    private int type;

    protected ArrayList<Fighter> people = new ArrayList<>();

    protected ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<HitBox> hitBoxes = new ArrayList<>();
    private Pane hitBoxPane = new Pane();


    protected int scale;

    @Getter
    @Setter
    protected Projectile activeProjectile;

    @Getter
    @Setter
    protected int buffer = 0;

    protected Loot loot;

    protected Circle earth;

    public Vector[] environmentForces = {new Vector(0, 9.8), new Vector(-1, 0)};

    public Mapp(String type) {

        if (type.equalsIgnoreCase("earth")) {
            this.setStyle("-fx-background-color: #bce1f5;");
            earth = new Circle(540, 673100640, 673100000, Color.SEAGREEN);
            this.getChildren().add(earth);
            this.type = 0;
        }

        if (type.equalsIgnoreCase("space")) {
            this.setStyle("-fx-background-color: #150c26;");
            earth = new Circle(900, 600, 120);
            Image earth_image = new Image("file:earth_Image.png");
            earth.setFill(new ImagePattern(earth_image));
            this.getChildren().add(earth);
            this.type = 1;
        }

    }

    public void update(double dt) {

        for (Obstacle obstacle : obstacles) {
            obstacle.move(dt);
        }

        if (activeProjectile != null) {
            activeProjectile.move(dt);
            addForces(activeProjectile);
            buffer++;

            if (buffer > 50) {

                if (activeProjectile instanceof Grenade) {
                    checkCollision(activeProjectile);
                    if (((Grenade) activeProjectile).getFuseTimer() <= 0) {
                        explosion(activeProjectile.getCoordinate());
                    }

                } else {
                    checkCollision(activeProjectile);
                }
            }
        } else {
            buffer = 0;
        }

    }

    public void addFighter(Fighter fighter) {
        people.add(fighter);
        hitBoxes.add(fighter.hitBox());
        getChildren().add(fighter);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
        getChildren().add(obstacle);
    }
    public void addHitBox(HitBox hitBox) {
        boolean exists = false;
        int i = 0;
        for (; i < hitBoxes.size() ; i++) {
            if(hitBox.belongsTo()==hitBoxes.get(i).belongsTo()){
                exists = true;
                break;
            }
        }
        if(exists){
            getChildren().remove(hitBoxes.get(i));
            hitBoxes.set(i,hitBox);
            getChildren().add(hitBox);
        } else {
            hitBoxes.add(hitBox);
            getChildren().add(hitBox);
        }


    }

    public void addForces(Projectile projectile) {

        if (type == 0) {
            environmentForces[0] = environmentForces[0].multiply(projectile.getMass());
            environmentForces[1] = environmentForces[1].multiply(projectile.getMass());
        } else {
            projectile.setMass(2000);
            Coordinate earthCenterOfGravity = new Coordinate(earth.getCenterX(), earth.getCenterY());
            double bigG = 6.67 * pow(10, -11);
            double massEarth = 5.97 * pow(10, 24);
            Vector distance = earthCenterOfGravity.distanceVector(projectile.getCoordinate());
            double gMagnitude = (bigG * massEarth * projectile.getMass()) / pow(distance.magnitude() + 120 * 106183, 2);
            Vector gForce = distance.scale(gMagnitude);
            environmentForces[0] = gForce;
            environmentForces[1] = new Vector(-0.00, 0);
            projectile.setLift(new Vector(0, 0));
        }

        if (projectile.forces.size() >= 3) {
            projectile.forces.set(1, environmentForces[0]);
            projectile.forces.set(2, environmentForces[1]);
        } else {
            projectile.forces.add(environmentForces[0]);
            projectile.forces.add(environmentForces[1]);
        }

    }

    public Boolean checkCollision(Projectile projectile) {

        for (Obstacle obstacle : obstacles) {
            if (projectile.hitBox().overlaps(obstacle.hitBox())) {

                MovingBody.collision(projectile,obstacle);
                HitBox collidedObstacleHitBox = obstacle.hitBox();
                collidedObstacleHitBox.setDisplayed(true);
                addHitBox(collidedObstacleHitBox);
                return true;
            }
        }
        for (Fighter fighter : people) {

            if (projectile.hitBox().overlaps(fighter.hitBox())) {
                fighter.setHealth(fighter.getHealth() - projectile.getDamage());
                this.getChildren().remove(activeProjectile);
                activeProjectile = null;
                if (fighter.getHealth() <= 0) {
                    people.remove(fighter);
                    this.getChildren().remove(fighter);
                }
                return true;
            }

        }

        if (loot != null && projectile.hitBox().overlaps(loot.hitBox())) {
            this.getChildren().remove(loot);
            loot = null;
            //TODO Game Won
        }
        return false;
    }

    public void explosion(Coordinate coordinate) {
        activeProjectile.setVelocity(new Vector(0, 0));
    }

}
