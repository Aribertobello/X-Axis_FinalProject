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

public class Grenade extends Projectile {

    Image explosionEffect1 = new Image("file:1.png");
    Image explosionEffect2 = new Image("file:2.png");
    Image explosionEffect3 = new Image("file:3.png");
    Image explosionEffect4 = new Image("file:4.png");
    Image explosionEffect5 = new Image("file:5.png");
    Image explosionEffect6 = new Image("file:6.png");
    Image explosionEffect7 = new Image("file:7.png");
    Image explosionEffect8 = new Image("file:8.png");

    Circle grenade = new Circle(10, Color.PALEGREEN);

    Circle explosionCircle = new Circle(10, Color.PALEGREEN);

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

    public void explode(){
        if (fuseTimer <= 0.0) {
            grenade.setRadius(0);
          /*  Timeline explosionAnimation = new Timeline(new KeyFrame(Duration.millis(1), e
                    -> {
                explosionCircle.setFill(new ImagePattern(explosionEffect1));

            }));
            explosionAnimation.setCycleCount(Timeline.INDEFINITE);
            explosionAnimation.play();
*/
                //TODO set the picture of the explosion here
        }
    }

    @Override
    public String toString() {
        return "Grenade";
    }
}
