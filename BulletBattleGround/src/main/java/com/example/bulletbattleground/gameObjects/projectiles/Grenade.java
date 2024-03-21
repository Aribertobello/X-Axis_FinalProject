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
    public Grenade(){
        fuseTimer = 7.0;
        this.lift = new Vector(0,0);
        this.getChildren().add(new Circle(10, Color.PALEGREEN));
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
    public void bounce(HitBox hitBox) {}
    @Override
    public void move(double time) {
        super.move(time);
        fuseTimer = fuseTimer - time;
    }
    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
        super.setCoordinate(coordinate);
    }
    @Override
    public HitBox hitBox(){
        return new HitBox(this);
    }
    @Override
    public String toString() {
        return "Grenade";
    }
}
