package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.utility.Coordinate;
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

    protected ArrayList<Fighter> people = new ArrayList<Fighter>();

    protected ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

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

    /**
     *
     * @param type
     */
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

    /**
     *
     * @param dt
     */
    protected void update(double dt) {

        for (Obstacle obstacle : obstacles) {
            obstacle.move(dt);
        }

        if (activeProjectile != null) {
            activeProjectile.move(dt);
            addForces(activeProjectile);
            buffer++;

            if (buffer > 50) {

                if (activeProjectile instanceof Grenade) {

                    if (((Grenade) activeProjectile).getFuseTimer() <= 0) {
                        explosion(activeProjectile.getCoordinate());
                        checkCollision(activeProjectile);
                    }

                } else {
                    checkCollision(activeProjectile);
                }
            }
        } else {
            buffer = 0;
        }

    }

    /**
     *
     * @param fighter
     */
    public void addFighter(Fighter fighter) {
        people.add(fighter);
        getChildren().add(fighter);
    }

    /**
     *
     * @param obstacle
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
        getChildren().add(obstacle);
    }

    /**
     *
     * @param projectile
     */
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

    /**
     *
     * @param projectile
     * @return
     */
    public Boolean checkCollision(Projectile projectile) {

        for (Obstacle obstacle : obstacles) {

            if (projectile.hitBox().overlaps(obstacle.hitBox())) {

                double m1 = projectile.getMass();
                double m2 = obstacle.getMass();
                double k1 = projectile.kE();
                double k2 = obstacle.kE();
                double p1 = projectile.momentum();
                double p2 = obstacle.momentum();
                double eDisspated = 0;
                double eFinal = k1 + k2 - eDisspated;
                double v1 = (-sqrt(2 * eFinal * pow(m1, 2) + 2 * eFinal * m2 * m1 - m1 * pow(p1, 2) - m1 * pow(p2, 2) - 2 * m1 * p1 * p2) / sqrt(m2) + (m1 * p1) / m2 + (m1 * p2) / m2) / (pow(m1, 2) / m2 + m1);

                projectile.setVelocityX(projectile.velocity().scale(v1).getX());
                projectile.setVelocityY(projectile.velocity().scale(v1).getY());
                projectile.bounce(obstacle.hitBox());
                obstacle.bounce(projectile.hitBox());

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

    /**
     *
     * @param coordinate
     */
    protected void explosion(Coordinate coordinate) {
        activeProjectile.setVelocity(new Vector(0, 0));
    }
}
