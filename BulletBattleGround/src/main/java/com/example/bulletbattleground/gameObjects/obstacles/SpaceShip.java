package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class SpaceShip extends Obstacle {
    public SpaceShip(int speed, int coordinateX, int coordinateY) {

        double width = 40;

        double height = 80;

        setVelocityY(speed);
        setCoordinate(new Coordinate(coordinateX, coordinateY));

        double x1 = coordinateX - width / 2, y1 = coordinateY - height / 2 - 25;
        double x2 = coordinateX + width / 2, y2 = coordinateY - height / 2;
        double x3 = coordinateX + width / 2, y3 = coordinateY + height / 2 - 10;
        double x4 = coordinateX + width / 2 + 10, y4 = coordinateY + height / 2;
        double x5 = coordinateX - width / 2, y5 = coordinateY + height / 2;

        Polygon ship = new Polygon(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        ship.setFill(Color.WHITESMOKE);
        ship.setStroke(Color.BLUE);
        this.getChildren().add(ship);
        setMass(190000);
    }

    @Override
    public void move(double dt) {

        if (getCoordinate().getY() <= -160 || getCoordinate().getY() > 1200) {
            Random random = new Random();
            setX(random.nextInt((int) ((Mapp) this.getParent()).getWidth() / 4, (int) (2 * ((Mapp) this.getParent()).getWidth() / 3)));
            setVelocityY(-getVelocityY() * (random.nextInt(5, 51)) / Math.abs(getVelocityY()));
            setVelocityX(0);
        }

        super.move(-dt);
    }

    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }

    @Override
    public void setX(double x) {

        double width = 40;

        double height = 80;

        getCoordinate().setX(x);

        double x1 = getCoordinate().getX() - width / 2, y1 = getCoordinate().getY() - height / 2 - 25;
        double x2 = getCoordinate().getX() + width / 2, y2 = getCoordinate().getY() - height / 2;
        double x3 = getCoordinate().getX() + width / 2, y3 = getCoordinate().getY() + height / 2 - 10;
        double x4 = getCoordinate().getX() + width / 2 + 10, y4 = getCoordinate().getY() + height / 2;
        double x5 = getCoordinate().getX() - width / 2, y5 = getCoordinate().getY() + height / 2;
        (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);

    }

    @Override
    public void setY(double y) {

        double width = 40;

        double height = 80;

        getCoordinate().setY(y);

        if (getVelocityY() <= 0.0) {
            double x1 = getCoordinate().getX() + width / 2, y1 = getCoordinate().getY() + height / 2 + 25;
            double x2 = getCoordinate().getX() - width / 2, y2 = getCoordinate().getY() + height / 2;
            double x3 = getCoordinate().getX() - width / 2, y3 = getCoordinate().getY() - height / 2 + 10;
            double x4 = getCoordinate().getX() - width / 2 - 10, y4 = getCoordinate().getY() - height / 2;
            double x5 = getCoordinate().getX() + width / 2, y5 = getCoordinate().getY() - height / 2;
            (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        } else {
            double x1 = getCoordinate().getX() - width / 2, y1 = getCoordinate().getY() - height / 2 - 25;
            double x2 = getCoordinate().getX() + width / 2, y2 = getCoordinate().getY() - height / 2;
            double x3 = getCoordinate().getX() + width / 2, y3 = getCoordinate().getY() + height / 2 - 10;
            double x4 = getCoordinate().getX() + width / 2 + 10, y4 = getCoordinate().getY() + height / 2;
            double x5 = getCoordinate().getX() - width / 2, y5 = getCoordinate().getY() + height / 2;
            (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        }
    }

}
