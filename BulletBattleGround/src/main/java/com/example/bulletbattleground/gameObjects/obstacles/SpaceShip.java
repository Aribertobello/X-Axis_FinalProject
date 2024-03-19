package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class SpaceShip extends Obstacle {
    double speed;
    public SpaceShip(int speed,int coordinateX, int coordinateY){
        this.speed = speed;
        this.coordinate = new Coordinate(coordinateX,coordinateY);
        double width = 40;
        double height = 80;
        double x1=coordinateX-width/2,y1=coordinateY-height/2-25;
        double x2=coordinateX+width/2,y2=coordinateY-height/2;
        double x3=coordinateX+width/2,y3=coordinateY+height/2-10;
        double x4=coordinateX+width/2+10,y4=coordinateY+height/2;
        double x5=coordinateX-width/2,y5=coordinateY+height/2;
        Polygon ship = new Polygon(x1,y1,x2,y2,x3,y3,x4,y4,x5,y5);
        ship.setFill(Color.WHITESMOKE);
        ship.setStroke(Color.BLUE);
        this.getChildren().add(ship);
    }
    @Override
    protected HitBox hitBox() {
        return null;
    }

    @Override
    protected void move(double dt) {
        if(coordinate.getY()<=-160||coordinate.getY()>1200){
            Random random = new Random();
            setX(random.nextInt((int)((Mapp)this.getParent()).getWidth()/4,(int)(2*((Mapp)this.getParent()).getWidth()/3)));
            speed = -speed*(random.nextInt(5,51))/Math.abs(speed);
        }
        setY(-speed * dt + coordinate.getY());
    }

    @Override
    protected void setX(double x) {
        this.coordinate.setX(x);
        double width = 40;
        double height = 80;
            double x1 = coordinate.getX() - width / 2, y1 = coordinate.getY() - height / 2 - 25;
            double x2 = coordinate.getX() + width / 2, y2 = coordinate.getY() - height / 2;
            double x3 = coordinate.getX() + width / 2, y3 = coordinate.getY() + height / 2 - 10;
            double x4 = coordinate.getX() + width / 2 + 10, y4 = coordinate.getY() + height / 2;
            double x5 = coordinate.getX() - width / 2, y5 = coordinate.getY() + height / 2;
            (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
    }

    @Override
    protected void setY(double y) {
        this.coordinate.setY(y);
        double width = 40;
        double height = 80;
        if(speed<=0.0){
            double x1 = coordinate.getX() + width / 2, y1 = coordinate.getY() + height / 2 + 25;
            double x2 = coordinate.getX() - width / 2, y2 = coordinate.getY() + height / 2;
            double x3 = coordinate.getX() - width / 2, y3 = coordinate.getY() - height / 2 + 10;
            double x4 = coordinate.getX() - width / 2 - 10, y4 = coordinate.getY() - height / 2;
            double x5 = coordinate.getX() + width / 2, y5 = coordinate.getY() - height / 2;
            (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        }else {
            double x1 = coordinate.getX() - width / 2, y1 = coordinate.getY() - height / 2 - 25;
            double x2 = coordinate.getX() + width / 2, y2 = coordinate.getY() - height / 2;
            double x3 = coordinate.getX() + width / 2, y3 = coordinate.getY() + height / 2 - 10;
            double x4 = coordinate.getX() + width / 2 + 10, y4 = coordinate.getY() + height / 2;
            double x5 = coordinate.getX() - width / 2, y5 = coordinate.getY() + height / 2;
            (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        }
    }
}
