package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class Game extends Scene{
    protected Boolean gameOver;
    protected Level level;
    protected Boolean gameWon;
    protected Integer tickrate;
    protected double time;
    protected Timeline timeline;

    public Game(Level level) {
        super(level);
        this.level = level;
    }

    public void run(){
        System.out.println("wow");
        for(Fighter fighter : level.map.people) {
            if (fighter instanceof Ally) {
                fighter.setOnMousePressed(event -> {
                    System.out.println("WOw");
                    level.headsUpDisplay.getChildren().clear();
                    level.selectedFighter = fighter;
                    level.origin = fighter.coordinate;
                    level.selectedFighter.setStroke(Color.CYAN);
                    level.headsUpDisplay.getChildren().add(fighter.headsUpDisplay());
                });
            }
        }
        this.level.update();

    }
    protected void tick(Level level){}
    protected void handleClick(){
        level.trajectoryLine.setStroke(Color.GOLD);
        final double[] dragStartX = {0.0};
        final double[] dragStartY = {0.0};
        this.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                dragStartX[0] = event.getSceneX();
                dragStartY[0] = event.getSceneY();
                level.dragging = true;
            }
        });
        this.setOnMouseDragged(event -> {
            if (level.dragging) {
                double dragX = event.getSceneX() - dragStartX[0];
                double dragY = event.getSceneY() - dragStartY[0];
                level.trajectoryLine.setStartX(level.origin.getX());
                level.trajectoryLine.setStartY(level.origin.getY());
                level.trajectoryLine.setEndX(level.origin.getX() + dragX);
                level.trajectoryLine.setEndY(level.origin.getY()+ dragY);
            }
        });
        this.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                level.trajectoryLine.setStartX(0);
                level.trajectoryLine.setStartY(0);
                level.trajectoryLine.setEndX(0);
                level.trajectoryLine.setEndY(0);double velocityX = -event.getSceneX() + dragStartX[0];
                double velocityY = -event.getSceneY() + dragStartY[0];
                level.map.activeProjectile.setVelocity(new Vector(velocityX,velocityY));
                level.dragging = false;
            }
        });

    }
}
