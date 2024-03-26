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

    @Override
    public HitBox hitBox() {
        return new HitBox(this);
    }

    Circle spearHitBox;
    Circle spear;

    //Polygon p = new Polygon();

    public Spear() {
        Image spearImg = new Image("file:spear.png");
       // p.setFill(new ImagePattern(spearImg));
        spearHitBox = new Circle(2, Color.DARKGRAY);
        spear = new Circle(20, Color.BLUE);
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
        Rotate();
      /*  if (velocity().magnitude() != 0) {
            double x1 = getChildren().get(0).getLayoutX() + velocity().scale(3).getX(), y1 = getChildren().get(0).getLayoutY() + velocity().scale(3).getY();
            double x2 = x1 + velocity().scale(3).rotate(270).getX(), y2 = y1 + velocity().scale(3).rotate(270).getY();
            double x3 = x2 - velocity().scale(45).getX(), y3 = y2 - velocity().scale(45).getY();
            double x4 = x3 + velocity().scale(6).rotate(90).getX(), y4 = y3 + velocity().scale(6).rotate(90).getY();
            double x5 = x4 + velocity().scale(45).getX(), y5 = y4 + velocity().scale(45).getY();
            ((Polygon) this.getChildren().get(1)).getPoints().setAll(x2, y2, x3, y3, x4, y4, x5, y5);
        }*/

    }

    public void Rotate(){
        double angle = velocity().angle();
        double pivotX = spearHitBox.getCenterX();
        double pivotY = spearHitBox.getCenterY();

        spear.getTransforms().add(new Rotate(angle,pivotX,pivotY));
        spear.setLayoutX(pivotX);
        spear.setLayoutY(pivotY);
    }

    public double setRotate(){
      return velocity().angle();
    }

}
