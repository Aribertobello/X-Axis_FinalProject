package com.example.bulletbattleground.utility;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.obstacles.SpaceShip;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class HitBox extends Group {

    protected ArrayList<Coordinate> points = new ArrayList<>();
    protected Coordinate center;
    protected double radius;
    public ArrayList<Line> border = new ArrayList<>();
    public boolean displayed = false;

    public HitBox(Projectile projectile) {
        this.radius = ((Circle) projectile.getChildren().get(0)).getRadius();
        this.center = new Coordinate(projectile.getCoordinate().getX(), projectile.getCoordinate().getY());
        this.getChildren().add(new Circle(
                ((Circle) projectile.getChildren().get(0)).getRadius()
                , projectile.getCoordinate().getX()
                , projectile.getCoordinate().getY()
                , Color.TRANSPARENT));
        ((Circle) this.getChildren().get(0)).setStroke(Color.BLACK);
        this.setVisible(false);
        for (double i = 0; i < 360; i = i + 6) {
            points.add(new Coordinate(center.getX() + radius * Math.cos(Math.PI * i / 180), center.getY() + radius * Math.sin(Math.PI * i / 180)));
        }
        Polygon hitBox = new Polygon();
        for (Coordinate point : points) {
            hitBox.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(hitBox);
    }

    public HitBox(Obstacle obstacle) {
        center = new Coordinate(obstacle.getCoordinate().getX(), obstacle.getCoordinate().getY());
        if (obstacle instanceof Wall) {
            double height = ((Wall) obstacle).getHeight();
            double thickness = ((Wall) obstacle).getThickness();
            for (int i = 0; i < height + 1; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2, center.getY() - height / 2 + i));
                points.add(new Coordinate(center.getX() + thickness / 2, center.getY() - height / 2 + i));
            }
            for (int i = 1; i < thickness; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() - height / 2));
                points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() + height / 2));
            }
        }
        if (obstacle instanceof SpaceShip) {
            double height = 40;
            double thickness = 80;
            for (int i = 0; i < height + 1; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2, center.getY() - height / 2 + i));
                points.add(new Coordinate(center.getX() + thickness / 2, center.getY() - height / 2 + i));
            }
            for (int i = 1; i < thickness; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() - height / 2));
                points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() + height / 2));
            }
        }
        Polygon hitBox = new Polygon();
        for (Coordinate point : points) {
            hitBox.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(hitBox);
        this.setVisible(false);
    }

    public HitBox(Fighter fighter) {
        center = new Coordinate(fighter.getCoordinate().getX(), fighter.getCoordinate().getY());
        double height = 40;
        double thickness = 40;
        for (int i = 0; i < 40 + 1; i++) {
            points.add(new Coordinate(center.getX() - thickness / 2, center.getY() - height / 2 + i));
            points.add(new Coordinate(center.getX() + thickness / 2, center.getY() - height / 2 + i));
        }
        for (int i = 1; i < 40; i++) {
            points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() - height / 2));
            points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() + height / 2));
        }
        Polygon hitBox = new Polygon();
        for (Coordinate point : points) {
            hitBox.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(hitBox);
        this.setVisible(false);
    }

    public HitBox(Loot loot) {
        center = new Coordinate(loot.getCoordinate().getX(), loot.getCoordinate().getY());
        double height = 40;
        double thickness = 40;
        for (int i = 0; i < 40 + 1; i++) {
            points.add(new Coordinate(center.getX() - thickness / 2, center.getY() - height / 2 + i));
            points.add(new Coordinate(center.getX() + thickness / 2, center.getY() - height / 2 + i));
        }
        for (int i = 1; i < 40; i++) {
            points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() - height / 2));
            points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() + height / 2));
        }
        Polygon hitBox = new Polygon();
        for (Coordinate point : points) {
            hitBox.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(hitBox);
        this.setVisible(false);
    }

    public boolean overlaps(HitBox HitBox) {
        for (Coordinate point : HitBox.points) {
            if (point.distance(this.center) <= radius) {
                return true;
            }
        }
        return false;
    }

    protected void setDisplayed(boolean isDisplayed) {
        this.setVisible(isDisplayed);
        this.displayed = isDisplayed;
    }
}
