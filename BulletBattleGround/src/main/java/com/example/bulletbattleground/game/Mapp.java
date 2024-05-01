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
            earth = new Rectangle(0, BattleGround.screenHeight-200, 3000,250);
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

            earth = new Circle(BattleGround.screenWidth/2.0, BattleGround.screenHeight/2.0, 120);
            Image earth_image = new Image(earthFilePath);
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
            Circle earth = (Circle) this.earth;
            projectile.setMass(2000);
            Coordinate earthCenterOfGravity = new Coordinate(earth.getCenterX(), earth.getCenterY());
            double bigG = 6.67 * pow(10, -11);
            double massEarth = 5.97 * pow(10, 24);
            Vector distance = earthCenterOfGravity.distanceVector(projectile.getCoordinate());
            double gMagnitude = (bigG * massEarth * projectile.getMass()) / pow(distance.magnitude() + 120 * 106183, 2);
            Vector gForce = distance.scale(gMagnitude);
            environmentForces[0] = gForce.multiply(1);
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
    @Override
    public Object clone() {
        try {
            Mapp clonedMap = (Mapp) super.clone();
            clonedMap.people = new ArrayList<>(this.people.size());
            for (Fighter fighter : this.people) {
                Fighter person  = new Fighter(fighter.loadout.type,fighter.health, (int)fighter.coordinate.getX(),(int)fighter.getCoordinate().getY());
                clonedMap.people.add(person);
                person.setMap(clonedMap);
            }

            clonedMap.obstacles = new ArrayList<>(this.obstacles.size());
            for (Obstacle obstacle : this.obstacles) {
                if(obstacle instanceof Wall) {
                    Wall wall = (Wall)obstacle;
                    Wall cloneWall = new Wall(wall.getHeight(),wall.getThickness(), (int)wall.getCoordinate().getX(),(int)wall.getCoordinate().getY(),wall.getRotationAngle(),(int)wall.getMass());
                    cloneWall.setMap(clonedMap);
                    clonedMap.obstacles.add(cloneWall);
                }
                if( obstacle instanceof SpaceShip){
                    SpaceShip spaceShip = (SpaceShip) obstacle;
                    SpaceShip cloneSpaceShip = new SpaceShip((int)obstacle.velocity().magnitude(),(int)obstacle.getCoordinate().getX(),(int)obstacle.getCoordinate().getY());
                    cloneSpaceShip.setMap(clonedMap);
                    clonedMap.obstacles.add(cloneSpaceShip);
                }
            }

            // Clone the Shape objects
            if (this.earth instanceof Circle) {
                Circle earthCircle = (Circle) this.earth;
                Circle clonedEarth = new Circle(earthCircle.getCenterX(), earthCircle.getCenterY(), earthCircle.getRadius());
                clonedEarth.setFill(earthCircle.getFill());
                clonedMap.earth = clonedEarth;
            } else if (this.earth instanceof Rectangle) {
                Rectangle earthRectangle = (Rectangle) this.earth;
                Rectangle clonedEarth = new Rectangle(earthRectangle.getX(), earthRectangle.getY(), earthRectangle.getWidth(), earthRectangle.getHeight());
                clonedEarth.setFill(earthRectangle.getFill());
                clonedMap.earth = clonedEarth;
            }

            // Clone other fields
            // (you may need to implement deep copying for other fields if necessary)

            return clonedMap;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
