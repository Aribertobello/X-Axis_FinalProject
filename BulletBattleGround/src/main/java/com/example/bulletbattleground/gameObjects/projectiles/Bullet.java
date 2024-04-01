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

public class Bullet extends Projectile {

    public Bullet() {
        Circle small_bullet = new Circle(10);
        small_bullet.setRotate(90);
        Image smallBulletImg = new Image("file:smallBullet.png");
        small_bullet.setFill(new ImagePattern(smallBulletImg));
        this.getChildren().add(small_bullet);
        this.damage = 3;
        this.lift = new Vector(0, -2.0);
        this.forces.add(lift);
        setMass(0.5);
    }

    @Override
    public void bounce(HitBox hitBox) {


        System.out.println(velocity().angle());
        System.out.println(velocity());


    }

    @Override
    public void move(double time) {
        super.move(time);
        this.getChildren().get(0).setRotate(velocity().angle());
    }

    @Override
    public String toString() {
        return "bullet : "+getCoordinate()+velocity().magnitude();
    }

}
