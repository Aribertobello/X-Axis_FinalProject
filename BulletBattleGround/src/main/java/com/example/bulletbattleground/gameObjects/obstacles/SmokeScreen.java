package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SmokeScreen extends Obstacle {



    protected int radius;

    public SmokeScreen(double radius,double coordinateX,double coordinateY){
        this.coordinate = new Coordinate(coordinateX,coordinateY);
        ispenetrable = true;
        Velocity = new Vector(0,0);
        this.radius = (int) (radius);
        Circle ball = new Circle(coordinateX,coordinateY,radius,Color.GREY);
        Label label = new Label("SMOKE SCREEN");
        label.setLayoutX(coordinateX-radius);
        label.setLayoutY(coordinateY);
        this.getChildren().addAll(ball,label);
    }

    @Override
    protected void move() {
        //this.setY();
    }

    @Override
    protected void setX(double x) {
        this.getChildren().get(0).setLayoutX(x);
        this.coordinate.setX(x);
    }

    @Override
    protected void setY(double y) {
        this.getChildren().get(0).setLayoutX(y);
        this.coordinate.setX(y);
    }


}
