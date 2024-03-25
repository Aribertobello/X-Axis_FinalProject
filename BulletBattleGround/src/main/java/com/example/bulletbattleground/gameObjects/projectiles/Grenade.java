package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import lombok.Getter;
import lombok.Setter;

public class Grenade extends Projectile {

    Circle grenade = new Circle(10, Color.PALEGREEN);

    public Grenade() {
        fuseTimer = 15.0;
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
    protected int ExplosionRadius;

    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }

    @Override
    public void move(double time) {
        super.move(time);
        fuseTimer = fuseTimer - time;

        if (fuseTimer == 0) {
            grenade.setRadius(0);
        }

    }

    @Override
    public String toString() {
        return "Grenade";
    }
}
