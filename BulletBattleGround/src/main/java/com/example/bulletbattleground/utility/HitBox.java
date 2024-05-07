package com.example.bulletbattleground.utility;

import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.obstacles.SpaceShip;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class HitBox extends Group {

    @Getter
    @Setter
    private ArrayList<Coordinate> points = new ArrayList<>();
    private final Coordinate center;
    @Getter
    @Setter
    private Coordinate overlapped;
    protected double radius;
    @Getter
    @Setter
    protected Polygon border;
    public boolean displayed = false;
    protected Node body;

    /**
     * creates a hitbox representing the projectile
     * @param projectile projectile whose hitbox is created
     */
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
        border = new Polygon();
        for (Coordinate point : points) {
            border.getPoints().addAll(point.getX(), point.getY());
        }
        border.setStroke(Color.LAWNGREEN);
        this.getChildren().add(border);
        this.setVisible(false);
        body = projectile;
    }


    /**
     *creates a wall representing the projectile
      @param wall projectile whose hitbox is created
     */
    public HitBox(Wall wall) {
        center = new Coordinate(wall.getCoordinate().getX(), wall.getCoordinate().getY());
        double height = wall.getHeight();
        double thickness = wall.getThickness();
        for (int i = 1; i < thickness; i++) {
            points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() - height / 2));
        }
        for (int i = 0; i < height + 1; i++) {
            points.add(new Coordinate(center.getX() + thickness / 2, center.getY() - height / 2 + i));
        }
        for (int i = 1; i < thickness; i++) {
            points.add(new Coordinate(center.getX() + thickness / 2 - i, center.getY() + height / 2));
        }
        for (int i = 0; i < height + 1; i++) {
            points.add(new Coordinate(center.getX() - thickness / 2, center.getY() + height / 2 - i));
        }
        for(int i = 0 ; i < points.size() ; i++){
            points.set(i,points.get(i).rotateAbout(wall.getRotationAngle(),center));
        }
        border = new Polygon();
        for (Coordinate point : points) {
            border.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(border);
        this.setVisible(false);
        body = wall;
        overlapped =new Coordinate(0,0);
    }

    /**
     *creates a spaceship representing the projectile
     @param spaceShip projectile whose hitbox is created
     */
    public HitBox(SpaceShip spaceShip) {
        center = new Coordinate(spaceShip.getCoordinate().getX(), spaceShip.getCoordinate().getY());
        int height = (int) SpaceShip.DEFAULT_HEIGHT;
        int thickness = (int) SpaceShip.DEFAULT_WIDTH;


        if (spaceShip.getVelocityY() > 0) {

            for (int i = 1; i < thickness; i++) {
                points.add(new Coordinate(center.getX() + thickness / 2 - i, center.getY() + height / 2 + 25 - 25.0 * i / 40.0));
            }
            for (int i = 0; i < height - 10 + 1; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2, center.getY() + height / 2 - i));
            }
            for (int i = height - 10; i < height; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2 - i + height - 10, center.getY() + height / 2 - i));
            }
            for (int i = 1; i < thickness; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() - height / 2));
            }
            for (int i = 0; i < height + 1; i++) {
                points.add(new Coordinate(center.getX() + thickness / 2, center.getY() - height / 2 + i));
            }
        } else {
            for (int i = 1; i < thickness; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2 + i, center.getY() - height / 2 - 25 + 25.0 * i / 40.0));
            }
            for (int i = 0; i < height - 10 + 1; i++) {
                points.add(new Coordinate(center.getX() + thickness / 2, center.getY() - height / 2 + i));
            }
            for (int i = height - 10; i < height; i++) {
                points.add(new Coordinate(center.getX() + thickness / 2 + i - height + 10, center.getY() - height / 2 + i));
            }
            for (int i = 1; i < thickness; i++) {
                points.add(new Coordinate(center.getX() + thickness / 2 - i, center.getY() + height / 2));
            }
            for (int i = 0; i < height + 1; i++) {
                points.add(new Coordinate(center.getX() - thickness / 2, center.getY() + height / 2 - i));
            }
        }

        border = new Polygon();
        for (Coordinate point : points) {
            border.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(border);
        this.setVisible(false);
        body = spaceShip;
        overlapped = new Coordinate(0, 0);
    }



    /**
     *creates a hitbox representing the fighter
     @param fighter projectile whose hitbox is created
     */
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
        border = new Polygon();
        for (Coordinate point : points) {
            border.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(border);
        this.setVisible(false);
    }

    /**
     *creates a hitbox representing the loot
     @param loot projectile whose hitbox is created
     */
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
        border = new Polygon();
        for (Coordinate point : points) {
            border.getPoints().addAll(point.getX(), point.getY());
        }
        this.getChildren().add(border);
        this.setVisible(false);
    }


    /**
     * checks if this hitbox overlaps with another
     * @param hitBox another hitbox to compare this one to
     * @return true if it ovelaps over the given hitbox
     */
    public boolean overlaps(HitBox hitBox) {
        for (int i = 0; i < hitBox.points.size() ; i++) {
            if (hitBox.points.get(i).distance(this.center) <= radius) {
                hitBox.overlapped = hitBox.points.get(i);
                return true;
            }
        }
        return false;
    }

    /**
     * sets the hitbox to be displayed in the scene
     * @param isDisplayed
     */
    public void setDisplayed(boolean isDisplayed) {
        this.setVisible(isDisplayed);
        this.displayed = isDisplayed;
    }
    public Node belongsTo(){
        return body;
    }
}
