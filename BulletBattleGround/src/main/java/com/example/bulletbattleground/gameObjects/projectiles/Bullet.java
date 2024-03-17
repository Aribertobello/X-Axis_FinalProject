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

    @Override
    public HitBox hitBox(){
        return new HitBox(this);
    }

    public Bullet(){
        Circle small_bullet = new Circle(10);
        small_bullet.setRotate(90);
        Image smallBulletImg = new Image("file:smallBullet.png");
        small_bullet.setFill(new ImagePattern(smallBulletImg));
        this.getChildren().add(small_bullet);
        this.damage = 3;
        this.lift = new Vector(0,-2.0);
        this.forces.add(lift);
        this.mass = 0.5;


        Timeline bulletAnimation = new Timeline(new KeyFrame(Duration.millis(1), e
                -> {
            small_bullet.setRotate(velocity().angle());
        }));
        bulletAnimation.setCycleCount(Timeline.INDEFINITE);
        bulletAnimation.play();
    }
    @Override
    public void move(double time) {
        this.coordinate.setX( (acceleration().getX()/2)*time*time + velocityX*time + coordinate.getX() );
        this.coordinate.setY( (acceleration().getY()/2)*time*time + velocityY*time + coordinate.getY() );
        this.setVelocityX( acceleration().getX()*time + velocityX );
        this.setVelocityY( acceleration().getY()*time + velocityY );

        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
    }
    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
        this.coordinate = coordinate;
    }
}
