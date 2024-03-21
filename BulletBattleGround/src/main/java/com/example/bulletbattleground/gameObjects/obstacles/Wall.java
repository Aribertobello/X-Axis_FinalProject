package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

public class Wall extends Obstacle {

    @Getter
    @Setter
    int thickness;
    @Getter
    @Setter
    int height;
    public Wall(int height, int thickness,int coordinateX, int coordinateY){
        setHeight(height);
        setThickness(thickness);
        Rectangle rectangle = new Rectangle(coordinateX-(double)thickness/2,coordinateY-(double)height/2,thickness,height);
        rectangle.setFill(Color.SANDYBROWN);
        Label label = new Label("WALL");
        label.setLayoutX(coordinateX-(double)thickness/2);
        label.setLayoutY(coordinateY);
        this.getChildren().addAll(rectangle,label);
        this.setCoordinate(new Coordinate(coordinateX, coordinateY));
    }
    @Override
    public HitBox hitBox() {
        return new HitBox(this);
    }
    @Override
    public void move(double dt) {
        super.move(dt);
    }
    @Override
    public void bounce(HitBox hitBox) {
    }
    @Override
    protected void setX(double x) {
        ((Rectangle)this.getChildren().get(0)).setX(x-(double)thickness/2);
        this.getCoordinate().setX(x);
    }
    @Override
    protected void setY(double y) {
        ((Rectangle)this.getChildren().get(0)).setY(y-(double)height/2);
        this.getCoordinate().setY(y);
    }
}
