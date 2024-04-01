package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SmokeScreen extends Obstacle {

    protected int radius;

    public SmokeScreen(double radius, double coordinateX, double coordinateY) {
        setCoordinate(new Coordinate(coordinateX, coordinateY));
        ispenetrable = true;
        this.radius = (int) (radius);
        Circle ball = new Circle(coordinateX, coordinateY, radius, Color.GREY);
        Label label = new Label("SMOKE SCREEN");
        label.setLayoutX(coordinateX - radius);
        label.setLayoutY(coordinateY);
        this.getChildren().addAll(ball, label);
    }

    @Override
    public void move(double dt) {
        super.move(dt);
    }
    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }
    @Override
    public void allign() {
        this.getChildren().get(0).setLayoutX(getCoordinate().getX());
        this.getChildren().get(0).setLayoutX(getCoordinate().getY());
    }
}
