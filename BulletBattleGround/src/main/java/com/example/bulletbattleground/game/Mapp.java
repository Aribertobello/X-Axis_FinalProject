package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.utility.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Mapp extends Pane implements GameUI {

    @Getter
    private int type;

    @Getter
    @Setter
    protected ArrayList<Fighter> people = new ArrayList<>();
    @Getter
    @Setter
    protected ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<HitBox> hitBoxes = new ArrayList<>();
    double terminalVelocity;
    private Pane hitBoxPane = new Pane();
    @Getter
    @Setter
    private double gravity;
    @Getter
    @Setter
    private double airResistance;

    protected int scale;
    @Setter
    private double[] bounds  = {BattleGround.screenWidth,BattleGround.screenHeight};

    @Getter
    @Setter
    public Projectile activeProjectile;
    @Getter
    @Setter
    protected int buffer = 0;
    @Getter
    public Loot loot;
    @Getter
    @Setter
    protected Shape earth;
    public Vector[] environmentForces = {new Vector(0,0),new Vector(0,0),new Vector(0,0)};
    @Getter
    @Setter
    private boolean hasLoot = false;

    /**
     *
     * @param type
     */
    public Mapp(String type) {
        if (type.equalsIgnoreCase("earth")) {
            Image backgroundImageSpace = new Image("file:sky.jpeg");
            BackgroundImage background = new BackgroundImage(
                    backgroundImageSpace,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
            this.setBackground(new Background(background));
            Image groundEarthImage = new Image("file:ground.jpeg");
            earth = new Rectangle(0, BattleGround.screenHeight-200, 3000,250);
            earth.setFill(new ImagePattern(groundEarthImage));
            this.getChildren().add(earth);
            gravity  = 9.8;
            airResistance = 3;
            this.type = 0;
        }
        if (type.equalsIgnoreCase("space")) {
            Image backgroundImageSpace = new Image("file:spaceBackground.png");
            BackgroundImage background = new BackgroundImage(
                    backgroundImageSpace,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
            this.setBackground(new Background(background));

            earth = new Circle(BattleGround.screenWidth/2.0, BattleGround.screenHeight/2.0, 120);
            Image earth_image = new Image("file:Files/img/earth_Image.png");
            earth.setFill(new ImagePattern(earth_image));
            this.getChildren().add(earth);
            this.type = 1;
        }
        this.getChildren().add(new Circle(2000, 1200, 1));
    }

    @Override
    public boolean[] update(double dt, double time) {
        for (double t = dt/10; t < dt; t+=(dt/10)) {
            for (Obstacle obstacle : obstacles) {
                obstacle.move(dt/10);
            }
            if (activeProjectile != null) {
                if (!isInBounds(activeProjectile)) {
                    removeActiveProjectile();
                    return new boolean[]{false};
                }
                addForces(activeProjectile);
                activeProjectile.move(dt);
                buffer++;
                if (buffer > 100) {
                    if (activeProjectile instanceof Grenade) {

                        if (((Grenade) activeProjectile).getFuseTimer() <= 0) {
                            explosion(activeProjectile.getCoordinate());
                        }
                    }
                    if(checkCollision(activeProjectile)) return new boolean[]{true};
                }
            } else {
                buffer = 0;
            }
        }
        return new boolean[]{false};
    }

    /**
     *
     * @param fighter
     */
    public void addFighter(Fighter fighter) {
        people.add(fighter);
        hitBoxes.add(fighter.hitBox());
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

    /**
     *
     * @param projectile
     */
    public void addForces(Projectile projectile) {
        if (type == 0) {
            environmentForces[0] = new Vector(0,gravity).multiply(projectile.getMass());
        } else {
            Circle earth = (Circle) this.earth;
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
        environmentForces[1] = projectile.velocity().multiply(-airResistance/1000000*Math.pow(projectile.velocity().magnitude(),2));
            projectile.forces.clear();
            projectile.forces.add(environmentForces[0]);
            projectile.forces.add(environmentForces[1]);
    }

    /**
     *
     * @param projectile
     * @return
     */
    public Boolean checkCollision(Projectile projectile) {

        for (Obstacle obstacle : obstacles) {
            if (projectile.hitBox().overlaps(obstacle.hitBox())) {

                MovingBody.collision(projectile,obstacle);
                HitBox collidedObstacleHitBox = obstacle.hitBox();
                collidedObstacleHitBox.setDisplayed(true);
                //addHitBox(collidedObstacleHitBox);
                return true;
            }
        }
        for (Fighter fighter : people) {

            if (projectile.hitBox().overlaps(fighter.hitBox())) {
                fighter.setHealth(fighter.getHealth() - projectile.getDamage());
                removeActiveProjectile();
                if (fighter.getHealth() <= 0) {
                    ((Level)getParent().getParent()).removeFighter(fighter);
                }
                return true;
            }

        }
        if (loot != null && projectile.hitBox().overlaps(loot.hitBox())) {
            removeLoot();
            removeActiveProjectile();
            return true;
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

    private boolean isInBounds(MovingBody body) {
        double x = body.getCoordinate().getX();
        double y = body.getCoordinate().getY();
        return !(x < BattleGround.screenWidth-bounds[0]-50 || x > bounds[0]+50 || y <  BattleGround.screenHeight-bounds[0]-50 || y > bounds[1]+50);
    }
    public void removeActiveProjectile() {
        if(activeProjectile!=null){
            this.getChildren().remove(activeProjectile);
            activeProjectile = null;
            ((Game)getScene()).turnManager.endAnimation();
        }
    }
    public void removeFighter(Fighter fighter){
        people.remove(fighter);
        this.getChildren().remove(fighter);
    }
    public void removeLoot(){
        this.getChildren().remove(loot);
        loot = null;
        hasLoot = false;
    }

    public void setAirResistanceMagnitude(double magnitude){
        //airResistance = airResistance.scale(magnitude);
    }
    public void setGravityMagnitude(double magnitude){

        //gravity = gravity.scale(magnitude);
    }

}
