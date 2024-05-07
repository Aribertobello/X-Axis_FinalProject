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

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Rocket extends Projectile {

    @Getter @Setter int nausea;

    @Getter @Setter double dropZone;

    Vector LIFT_VECTOR = new Vector(0, -2.0);
    int CIRCLE_ROCKET_RADIUS = 2;
    int nauseaEffect = 3;
    int rocketDamage = 3;
    double rocketMass = 9.0;
    final int ROCKET_TERMINAL_VELOCITY = 50;

    public Rocket() {
        this.nausea = nauseaEffect;
        this.getChildren().add(new Circle(CIRCLE_ROCKET_RADIUS, Color.DARKGRAY));
        this.getChildren().add(new Polygon());
        this.damage = rocketDamage;
        this.lift = LIFT_VECTOR;
        this.forces.add(lift);
        this.setMass(rocketMass);
        setTerminalVelocity(ROCKET_TERMINAL_VELOCITY);
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
    public void bounce(HitBox hitBox) {}

    public void align() {
        int NINETY_DEGREE_ANGLE = 90;
        int TWO_SEVENTY_DEGREE_ANGLE = 270;
        int newScaleMagnitude1 = 3;
        int newScaleMagnitude2 = 6;
        int newScaleMagnitude3 = 25;

        if (velocity().magnitude() != 0) {


            double x0 = getChildren().get(0).getLayoutX() + velocity().scale(3).getX(),
                    y0 = getChildren().get(0).getLayoutY() + velocity().scale(3).getY();

            double x1 = x0 + velocity().scale(newScaleMagnitude1).getX(),
                    y1 = y0 + velocity().scale(newScaleMagnitude1).getY();

            double x2 = x0 + velocity().scale(newScaleMagnitude1).rotate(TWO_SEVENTY_DEGREE_ANGLE).getX(),
                    y2 = y0 + velocity().scale(newScaleMagnitude1).rotate(TWO_SEVENTY_DEGREE_ANGLE).getY();

            double x3 = x2 - velocity().scale(newScaleMagnitude3).getX(),
                    y3 = y2 - velocity().scale(newScaleMagnitude3).getY();

            double x4 = x3 + velocity().scale(newScaleMagnitude2).rotate(NINETY_DEGREE_ANGLE).getX(),
                    y4 = y3 + velocity().scale(newScaleMagnitude2).rotate(NINETY_DEGREE_ANGLE).getY();

            double x5 = x4 + velocity().scale(newScaleMagnitude3).getX(),
                    y5 = y4 + velocity().scale(newScaleMagnitude3).getY();

            ((Polygon) this.getChildren().get(1)).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        }

    }

}
