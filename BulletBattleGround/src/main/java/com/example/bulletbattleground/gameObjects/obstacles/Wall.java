package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Obstacle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Obstacle {



    int thickness;
    int height;


    public Wall(int height, int thickness,int coordinateX, int coordinateY){
        Rectangle rectangle = new Rectangle(coordinateX,coordinateY,thickness,height);
        rectangle.setFill(Color.SANDYBROWN);
        Label label = new Label("WALL");
        label.setLayoutX(coordinateX);
        label.setLayoutY(coordinateY);
        this.getChildren().addAll(rectangle,label);
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
