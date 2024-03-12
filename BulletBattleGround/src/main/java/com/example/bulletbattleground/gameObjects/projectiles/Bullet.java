package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Projectile {
    protected HitBox hitBox;

    public Bullet(){
        this.getChildren().add(new Circle(20, Color.DARKGRAY));
        this.lift = new Vector(0,8.0);
        this.forces.add(lift);
        this.mass = 10000.5;
    }
    @Override
    public void move(double time) {

        this.coordinate.setX( (acceleration().getX()/2)*time*time + velocityX*time + coordinate.getX() );
        this.coordinate.setY( (acceleration().getY()/2)*time*time + velocityY*time + coordinate.getY() );
        this.setVelocityX( acceleration().getX()*time + velocityX );
        this.setVelocityY( acceleration().getY()*time + velocityY );

        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());

        System.out.println(coordinate.getX());
        System.out.println(coordinate.getY());



    }
    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
        this.coordinate = coordinate;
    }

}
