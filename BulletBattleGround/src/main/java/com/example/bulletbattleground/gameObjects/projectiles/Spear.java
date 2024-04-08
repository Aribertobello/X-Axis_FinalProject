package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

public class Spear extends Projectile {
    
    Circle spearHitBox;
    Circle spear;

    public Spear() {
        Image spearImg = new Image("file:spear.png");
        spearHitBox = new Circle(2, Color.DARKGRAY);
        spear = new Circle(30 ,Color.BLUE);
        spear.setFill(new ImagePattern((spearImg)));
        this.getChildren().add(spearHitBox);
        this.getChildren().add(spear);
        this.damage = 6;
        this.lift = new Vector(0, -0.5);
        this.forces.add(lift);
        this.setMass(3.0);
    }

    @Override
    public void move(double time) {
        allign();
        super.move(time);
    }

    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
        super.setCoordinate(coordinate);
    }

    public void allign() {
        double offset = 2; // Adjust as needed
        double offsetX = offset * Math.cos(Math.toRadians(velocity().angle()));
        double offsetY = offset * Math.sin(Math.toRadians(velocity().angle()));
        spear.setCenterX(getChildren().get(0).getLayoutX() - offsetX);
        spear.setCenterY(getChildren().get(0).getLayoutY() - offsetY);
        spear.setRotate(velocity().angle());
        //Rotate();
    }

}
