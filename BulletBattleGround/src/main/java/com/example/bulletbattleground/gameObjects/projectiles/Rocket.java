package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

public class Rocket extends Projectile {

    @Getter
    @Setter
    int nausea;

    @Getter
    @Setter
    double dropZone;

    public Rocket() {
        this.nausea = 3;
        this.getChildren().add(new Circle(2, Color.DARKGRAY));
        this.getChildren().add(new Polygon());
        this.damage = 3;
        this.lift = new Vector(0, -2.0);
        this.forces.add(lift);
        this.setMass(9.0);
    }


    @Override
    public void move(double time) {
        forces.clear();
        if (getCoordinate().getY() < -20) {
            setCoordinate(new Coordinate(dropZone, 1));
            setVelocity(new Vector(0, 50));
        } else if (getCoordinate().getX() != dropZone) {
            setVelocity(new Vector(0, -50));
        }
        align();
        super.move(time);
    }

    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }

    public void align() {
        //Got to make all these x values down below more readable

        if (velocity().magnitude() != 0) {
            double x0 = getChildren().get(0).getLayoutX() + velocity().scale(3).getX(), y0 = getChildren().get(0).getLayoutY() + velocity().scale(3).getY();
            double x1 = x0 + velocity().scale(3).getX(), y1 = y0 + velocity().scale(3).getY();
            double x2 = x0 + velocity().scale(3).rotate(270).getX(), y2 = y0 + velocity().scale(3).rotate(270).getY();
            double x3 = x2 - velocity().scale(25).getX(), y3 = y2 - velocity().scale(25).getY();
            double x4 = x3 + velocity().scale(6).rotate(90).getX(), y4 = y3 + velocity().scale(6).rotate(90).getY();
            double x5 = x4 + velocity().scale(25).getX(), y5 = y4 + velocity().scale(25).getY();
            ((Polygon) this.getChildren().get(1)).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        }

    }

}
