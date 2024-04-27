package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;


import java.io.IOException;
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

    /**
     *
     * @param level
     */
    public Game(Level level) throws IOException {
        super(level);
        this.level = level;
        Button pausebtn = new Button("Pause");
        pausebtn.setOnAction(new pauseEvent());
        pausebtn.setPrefWidth(250);
        pausebtn.setPrefHeight(20);
        pausebtn.setLayoutX(900);
        level.getChildren().add(pausebtn);

        //TODO add this to fxml and handle click

        isTicking=true;
    }

    /**
     *
     */
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

    /**
     *
     * @param dt
     */
    protected void tick(double dt) {

        level.update(dt);
    }

    /**
     *
     */
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
                    level.displaceTrajectoryLine(
                            level.origin.getX(),
                            level.origin.getY(),
                            level.origin.getX() + dragX,
                            level.origin.getY() + dragY);
                    level.arrow.updateDrag(level.selectedFighter,(1.0 / tickRate),level.getOrigin(),new Vector(-event.getSceneX() + dragStartX[0],-event.getSceneY() + dragStartY[0]));
                }
            }
        });

        this.setOnMouseReleased(event -> {

            double velocityX = -event.getSceneX() + dragStartX[0];

            double velocityY = -event.getSceneY() + dragStartY[0];
            level.resetTrajectoryLine();
            if(level.selectedFighter!=null && level.selectedFighter instanceof Ally && level.selectedFighter.isHighlighted() && checkVelocity(velocityX,velocityY) && isWithinPlayerBounds(dragStartX[0],dragStartY[0])){
                shoot(event, (Ally) level.selectedFighter,velocityX,velocityY);
            }
            // TODO -LAUNCH GRENADE
        });

        this.setOnKeyPressed(event -> {//Pauses the game when hitting key P

            if (event.getCode() == KeyCode.P) {
                new pauseEvent().handle(event);
            }

            if (event.getCode() == KeyCode.S && level.selectedFighter!=null) {
                   level.selectedFighter.launchProjectile(
                          level.selectedFighter.loadout.smokeGrenades.get(0), new Vector(15, 0.0), level.origin);
                level.selectedFighter.loadout.smokeGrenades.remove(level.selectedFighter.loadout.smokeGrenades.get(0));
                    turnManager.projectileShot();
                    System.out.println("Smoke grenade deployed");
                    }
        });
    }

    private boolean isWithinPlayerBounds(double x,double y) {
        double boundX = level.selectedFighter.getCoordinate().getX();
        double boundY = level.selectedFighter.getCoordinate().getY();
        return x < boundX + 20 && x > boundX - 20 && y < boundY + 20 && y > boundY - 20;
    }

    private boolean checkVelocity(double velocityX, double velocityY) {
        return (new Vector(velocityX,velocityY)).magnitude() > Projectile.MIN_LAUNCH_VELOCITY;
    }

    private void shoot(MouseEvent event, Ally selectedFighter, double velocityX, double velocityY) {
        if(level instanceof StandardLevel && level.origin!=null){
            StandardLevel level = (StandardLevel) this.level;
            if(turnManager.isPlayer1Turn() && level.team1.contains(selectedFighter)) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY), level.origin);
                    turnManager.projectileShot();
                }// TODO -LAUNCH MAIN PROJECTILE
                if (event.getButton() == MouseButton.SECONDARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY), level.origin);
                    selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                    turnManager.projectileShot();
                }

            } else if(turnManager.isPlayer2Turn() && level.team2.contains(selectedFighter)){
                if (event.getButton() == MouseButton.PRIMARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY), level.origin);
                    turnManager.projectileShot();
                }// TODO -LAUNCH MAIN PROJECTILE
                if (event.getButton() == MouseButton.SECONDARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY), level.origin);
                    selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                    turnManager.projectileShot();
                }
            }
        } else {
            if (event.getButton() == MouseButton.PRIMARY) {
                selectedFighter.launchProjectile(
                        selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY), level.origin);
                turnManager.projectileShot();
            }// TODO -LAUNCH MAIN PROJECTILE

            if (event.getButton() == MouseButton.SECONDARY && clickNb.get() > 0) {
                level.selectedFighter.launchProjectile(level.selectedFighter.loadout.grenades.get(0), new Vector(-event.getSceneX() + dragStartX[0], -event.getSceneY() + dragStartY[0]), level.origin);
                level.selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                clickNb.set(0);
            }// TODO -LAUNCH GRENADE
            level.dragging = false;
        }
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
