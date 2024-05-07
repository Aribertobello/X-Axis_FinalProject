package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.obstacles.SpaceShip;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.utility.*;
import javafx.geometry.Side;
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
@Getter@Setter
public class Mapp extends Pane implements GameUI , Cloneable {

    private int type;
    protected ArrayList<Fighter> people = new ArrayList<>();
    protected ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<HitBox> hitBoxes = new ArrayList<>();
    private double gravity;
    private double airResistance;
    protected int scale;
    private double[] bounds  = {BattleGround.screenWidth,BattleGround.screenHeight};
    public Projectile activeProjectile;
    protected int buffer = 0;
    public Loot loot;
    protected Shape earth;
    public Vector[] environmentForces = {new Vector(0,0),new Vector(0,0),new Vector(0,0)};
    private boolean hasLoot = false;
    private boolean fighterHit = false;
    private String skyFilePath = "file:Files/img/sky.jpeg";
    private String groundFilePath = "file:Files/img/ground.jpeg";
    private String spaceFilePath = "file:Files/img/spaceBackground.png";
    private String earthFilePath = "file:Files/img/earth_Image.png";


    /**
     *
     * @param type
     */
    public Mapp(String type) {
        if (type.equalsIgnoreCase("earth")) {
            Image backgroundImageSpace = new Image(skyFilePath);
            BackgroundImage background = new BackgroundImage(
                    backgroundImageSpace,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
            this.setBackground(new Background(background));
            Image groundEarthImage = new Image(groundFilePath);
            int padding = 200;
            double earthWidth = 3000;
            double earthHeight = 300;
            earth = new Rectangle(0, BattleGround.screenHeight-padding, earthWidth,earthHeight);
            earth.setFill(new ImagePattern(groundEarthImage));
            this.getChildren().add(earth);
            gravity  = 9.8;
            airResistance = 1.5;
            this.type = 0;
        }
        if (type.equalsIgnoreCase("space")) {
            Image backgroundImageSpace = new Image(spaceFilePath);
            BackgroundImage background = new BackgroundImage(
                    backgroundImageSpace,
                    BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
            this.setBackground(new Background(background));

            double earthRadius = 120;
            earth = new Circle(BattleGround.screenWidth/2.0, BattleGround.screenHeight/2.0, earthRadius);
            Image earth_image = new Image(earthFilePath);
            earth.setFill(new ImagePattern(earth_image));
            this.getChildren().add(earth);
            this.type = 1;
        }
        // object to keep background maximized
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
                    return new boolean[]{false,false};
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
                    if(checkCollision(activeProjectile)) return new boolean[]{true,fighterHit};
                }
                if(Double.isNaN(activeProjectile.getCoordinate().getX())||Double.isNaN(activeProjectile.getCoordinate().getY())) removeActiveProjectile();
            } else {
                buffer = 0;
            }
        }
        fighterHit = false;
        return new boolean[]{false, false};
    }

    /**
     *
     * @param fighter
     */
    public void addFighter(Fighter fighter) {
        people.add(fighter);
        hitBoxes.add(fighter.hitBox());
        getChildren().add(fighter);
        fighter.setMap(this);
    }

    /**
     *
     * @param obstacle
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
        getChildren().add(obstacle);
        obstacle.setMap(this);
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
            double projMass = 2000;
            Circle earth = (Circle) this.earth;
            projectile.setMass(projMass);
            Coordinate earthCenterOfGravity = new Coordinate(earth.getCenterX(), earth.getCenterY());
            double bigG = 6.67 * pow(10, -11);
            double massEarth = 5.97 * pow(10, 24);
            Vector distance = earthCenterOfGravity.distanceVector(projectile.getCoordinate());
            double earthRadReal = 106183;
            double earthRadPixel = 120;
            double gMagnitude = (bigG * massEarth * projectile.getMass()) / pow(distance.magnitude() + earthRadPixel * earthRadReal, 2);
            Vector gForce = distance.scale(gMagnitude);
            environmentForces[0] = gForce.multiply(1);
            environmentForces[1] = new Vector(-0.00, 0);
            projectile.setLift(new Vector(0, 0));
        }
        //value to properly scale large object Mathematics to fit the computer screen
        double scalar = 1000000;

        environmentForces[1] = projectile.velocity().multiply(-airResistance/scalar*Math.pow(projectile.velocity().magnitude(),2));
            projectile.forces.clear();
            projectile.forces.add(environmentForces[0]);
            projectile.forces.add(environmentForces[1]);
    }

    /**
     * Checks if a projectile within this collides with another game element at a given moment
     * @param projectile projectile to check collisions for
     * @return true if the projectile collides with something
     */
    public Boolean checkCollision(Projectile projectile) {

        for (Obstacle obstacle : obstacles) {
            if (projectile.hitBox().overlaps(obstacle.hitBox())) {

                MovingBody.collision(projectile,obstacle);
                HitBox collidedObstacleHitBox = obstacle.hitBox();
                collidedObstacleHitBox.setDisplayed(true);
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
                fighterHit = true;
                return true;
            }

        }
        if (loot != null && projectile.hitBox().overlaps(loot.hitBox())) {
            removeLoot();
            removeActiveProjectile();
            return true;
        }
        return false;
    }

    /**
     *  TRIGGERS AN EXPLOSION
     * @param coordinate
     */
    protected void explosion(Coordinate coordinate) {
        activeProjectile.setVelocity(new Vector(0, 0));
    }

    private boolean isInBounds(MovingBody body) {
        int boundsPadding = 50;
        double x = body.getCoordinate().getX();
        double y = body.getCoordinate().getY();
        return !(x < BattleGround.screenWidth-bounds[0]-boundsPadding|| x > bounds[0]+boundsPadding || y <  BattleGround.screenHeight-bounds[0]-boundsPadding || y > bounds[1]+boundsPadding);
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
}
