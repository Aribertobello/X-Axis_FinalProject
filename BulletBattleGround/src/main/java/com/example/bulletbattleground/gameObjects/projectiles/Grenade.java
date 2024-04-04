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
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grenade extends Projectile {

    public Image explosionEffect1 = new Image("file:1.PNG");
    public Image explosionEffect2 = new Image("file:2.PNG");
    public Image explosionEffect3 = new Image("file:3.PNG");
    public Image explosionEffect4 = new Image("file:4.PNG");
    public Image explosionEffect5 = new Image("file:5.PNG");
    public Image explosionEffect6 = new Image("file:6.PNG");
    public Image explosionEffect7 = new Image("file:7.PNG");
    public Image explosionEffect8 = new Image("file:8.PNG");

    public Circle grenade = new Circle(10, Color.PALEGREEN);

    public Circle explosionCircle = new Circle(20, Color.PALEGREEN);

    public boolean animationDone;

    public Grenade() {
        fuseTimer = 25.0;
        Image grenadeImg = new Image("file:grenade.png");
        grenade.setFill(new ImagePattern(grenadeImg));
        this.lift = new Vector(0, 0);
        this.getChildren().add(grenade);
        this.forces.add(lift);
        setMass(0.5);
    }

    @Getter
    @Setter
    protected double fuseTimer;

    @Getter
    @Setter
    protected double ExplosionRadius;

    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }

    @Override
    public void move(double time) {
        super.move(time);
            fuseTimer = fuseTimer - time;
        explode();
    }

    public void explode() {

        if (fuseTimer <= 0.0 ) {
          //  fuseTimer = Math.sqrt (-1);
            System.out.println(fuseTimer);
            explosionCircle.setLayoutX(getChildren().get(0).getLayoutX());
            explosionCircle.setLayoutY(getChildren().get(0).getLayoutY());
            this.getChildren().remove(0);
            this.getChildren().add(explosionCircle);
            explosionAnimation();
        }
    }

    public void explosionAnimation() {
        //TODO create the animation for the explosion with the pictures
        Image [] explosionEffects = {explosionEffect1, explosionEffect2, explosionEffect3, explosionEffect4, explosionEffect5, explosionEffect6, explosionEffect7, explosionEffect8};

        for (int index = 0; index <8; index++){
            explosionCircle.setFill(new ImagePattern(explosionEffects[index]));

            if(index > 8){ //TODO CHANGE THIS WHOLE THING
                this.getChildren().remove(explosionCircle);
            }
        }

    }


    @Override
    public String toString() {
        return "Grenade";
    }
}
