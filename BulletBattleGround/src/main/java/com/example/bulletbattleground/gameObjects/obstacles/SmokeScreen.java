package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SmokeScreen extends Obstacle {

    double radius;
    double coordinateX;
    double coordinateY;

    private final Image smokeEffect1 = new Image("file:smoke.png");

    public SmokeScreen(double radius, double coordinateX, double coordinateY) {
        setCoordinate(new Coordinate(coordinateX, coordinateY));
        ispenetrable = true;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.radius = radius;
        Label label = new Label("SMOKE SCREEN");
        label.setLayoutX(coordinateX - radius);
        label.setLayoutY(coordinateY);
    }

    public void createSmokeScreen(){
        Circle ball = new Circle(radius, Color.GREY);
        ball.setCenterX(coordinateX);
        ball.setCenterY(coordinateY);
        this.getChildren().add(ball);

        ball.setFill(new ImagePattern(smokeEffect1));

    }

    @Override
    public void move(double dt) {super.move(dt);}
    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }
    @Override
    public void allign() {
        //this.getChildren().get(0).setLayoutX(getCoordinate().getX());
        //this.getChildren().get(0).setLayoutX(getCoordinate().getY());
    }

    @Override
    public HitBox hitBox(){
        hitBox = new HitBox(new SpaceShip(0,0,0));
        return hitBox;
    }
}
