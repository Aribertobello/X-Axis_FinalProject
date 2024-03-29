package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;


import java.util.concurrent.atomic.AtomicInteger;

public class Game extends Scene {

    protected Boolean gameOver;

    protected Level level;

    protected Boolean gameWon;

    protected Integer tickRate = 100;

    protected double time = 0;

    protected Timeline timeline;

    private double dragStartX, dragStartY;

    private boolean dragging = false;

    private boolean isTicking;

    private boolean printCoordinate = false;
    Label mouseCoordinatesLabel = new Label();
    private ProgressBar healthBar = new ProgressBar();


    public Game(Level level) {
        super(level);

        Button pausebtn = new Button("Pause");
        pausebtn.setOnAction(new pauseEvent());
        pausebtn.setPrefWidth(250);
        pausebtn.setPrefHeight(20);
        level.getChildren().add(pausebtn);

        //TODO add this to fxml and handle click

        this.level = level;
        isTicking=true;
    }


    public void run() {
        handleClick();
        System.out.println("wow");

        timeline = new Timeline(new KeyFrame(Duration.millis(1), e
                -> {
            time += (1.0 / tickRate);
            tick(1.0 / tickRate);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    protected void tick(double dt) {

        level.update(dt);
    }

    protected void handleClick() {

        final double[] dragStartX = {0.0};

        final double[] dragStartY = {0.0};

        AtomicInteger clickNb = new AtomicInteger();
        level.trajectoryLine.setStroke(Color.GOLD);

        this.setOnMousePressed(event -> {
            dragStartX[0] = event.getSceneX();
            dragStartY[0] = event.getSceneY();
            level.dragging = true;

            if (event.getButton() == MouseButton.PRIMARY) {
                level.trajectoryLine.setStroke(Color.GOLD);
            }

            if (event.getButton() == MouseButton.SECONDARY) {
                level.trajectoryLine.setStroke(Color.DARKGRAY);
            }
        });

        this.setOnMouseDragged(event -> {
            if (level.dragging) {
                if (level.origin == null) {
                    /*TODO  -notify user to select a fighter   */
                } else {
                    double dragX = event.getSceneX() - dragStartX[0];
                    double dragY = event.getSceneY() - dragStartY[0];
                    level.trajectoryLine.setStartX(level.origin.getX());
                    level.trajectoryLine.setStartY(level.origin.getY());
                    level.trajectoryLine.setEndX(level.origin.getX() + dragX);
                    level.trajectoryLine.setEndY(level.origin.getY() + dragY);
                }
            }
        });

        this.setOnMouseReleased(event -> {

            double velocityX = -event.getSceneX() + dragStartX[0];

            double velocityY = -event.getSceneY() + dragStartY[0];

            Vector direction = new Vector(level.trajectoryLine.getEndX() - level.trajectoryLine.getStartX(), level.trajectoryLine.getEndY() - level.trajectoryLine.getStartY());
            System.out.println("Mouse coordinates:" + (-direction.angle()));
            double angle = 180 -  direction.angle() ;
            level.headsUpDisplay.getChildren().add(mouseCoordinatesLabel);
            mouseCoordinatesLabel.setText(" Mouse coordinates: " + angle + "  Degrees  ");
            level.headsUpDisplay.getChildren().add(healthBar);
            healthBar.setMaxSize(100,100);

            level.trajectoryLine.setStartX(0);
            level.trajectoryLine.setStartY(0);
            level.trajectoryLine.setEndX(0);
            level.trajectoryLine.setEndY(0);

            if (event.getButton() == MouseButton.PRIMARY && clickNb.get() > 0) {
                level.selectedFighter.launchProjectile(level.selectedFighter.loadout.mainWeapon, new Vector(-event.getSceneX() + dragStartX[0], -event.getSceneY() + dragStartY[0]), level.origin);
                clickNb.set(0);
            }// TODO -LAUNCH MAIN PROJECTILE

            if (event.getButton() == MouseButton.SECONDARY && clickNb.get() > 0) {
                level.selectedFighter.launchProjectile(level.selectedFighter.loadout.grenades.get(0), new Vector(-event.getSceneX() + dragStartX[0], -event.getSceneY() + dragStartY[0]), level.origin);
                level.selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                clickNb.set(0);
            }// TODO -LAUNCH GRENADE

            level.dragging = false;
        });

        for (Fighter fighter : level.map.people) {
            if (fighter instanceof Ally) {
                fighter.setOnMousePressed(event -> {
                    clickNb.getAndIncrement();
                    System.out.println(clickNb);
                    System.out.println("Fighter selected"); //TODO remove this in final code
                    level.headsUpDisplay.getChildren().clear();
                    level.selectedFighter = (Ally) fighter;
                    level.origin = new Coordinate(
                            level.selectedFighter.getCoordinate().getX() + level.selectedFighter.getWidth() / 2
                            , level.selectedFighter.getCoordinate().getY() - level.selectedFighter.getHeight() / 2);
                    level.selectedFighter.setStroke(Color.CYAN);
                    level.headsUpDisplay.getChildren().add(fighter.headsUpDisplay());
                });
            }
        }
        this.setOnKeyPressed(new pauseEvent());

    }
    private class pauseEvent implements EventHandler {

        @Override
        public void handle(Event t) {
            if(timeline.getStatus() == Animation.Status.PAUSED){
                isTicking = true;
                timeline.play();
            }else{
                //Debug
                Object variableOfInterest = level.map.activeProjectile;
                //----------------
                timeline.pause();
                isTicking = false;

            }
        }
    }
}
