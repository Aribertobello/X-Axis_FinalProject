package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Bullet extends Projectile {

    private final static int BULLET_DAMAGE = 3;
    private final static int SMALL_BULLET_RADIUS = 10;
    private final static double BULLET_MASS = 0.5;
    private final static Vector BULLET_LIFT = new Vector(0, -2.0);
    private final static int TERMINAL_VELOCITY = 190;

    /**
     * Constructs a new Bullet object.
     * Creates the Bullet object using a circle shape and an image.
     * The bullet has a default damage value of 3, upward lift force, and a set mass.
     */
    public Bullet() {
        Circle small_bullet = new Circle(SMALL_BULLET_RADIUS);
        Image smallBulletImg = new Image("file:Files/img/smallBullet.png");
        small_bullet.setFill(new ImagePattern(smallBulletImg));
        this.getChildren().add(small_bullet);
        this.damage = BULLET_DAMAGE;
        this.lift = BULLET_LIFT;
        this.forces.add(lift);
        setTerminalVelocity(TERMINAL_VELOCITY);
        setMass(BULLET_MASS);

    }

    @Override
    public void bounce(HitBox hitBox) {
        System.out.println(velocity().angle());
        System.out.println(velocity());
    }

    @Override
    public void move(double time) {
        super.move(time);
        allign();
    }

    @Override
    public String toString() {
        return "bullet : "+getCoordinate()+velocity().magnitude();
    }

    /**
     * alligns the object in the scene according to position and velocity and other individual properties
     */
    @Override
    public void allign() {
        this.getChildren().get(0).setRotate(velocity().angle());
    }
}
