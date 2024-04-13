package com.example.bulletbattleground.game;

import com.example.bulletbattleground.game.levels.StandardLevel;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lombok.Getter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends Scene {

    private static final double MIN_LAUNCH_VELOCITY = 10.0;
    protected Boolean gameOver = false;

    @Getter
    protected Level level;
    protected Boolean gameWon;
    protected Integer tickRate = 100;

    protected double time = 0;
    protected Timeline timeline;
    private boolean isTicking;
    boolean gameStart = false;
    TurnManager turnManager ;


    /**
     * creates a game object
     * since the game class is a child of the Scene Class it includes its constructor, where the root must be specified
     * the root in this case is the level which it will run
     * @param level the level this game object will run
     */
    public Game(Level level) throws IOException {
        super(level);
        this.level = level;

        //TODO add this to fxml and handle click
        isTicking = true;
    }

    /**
     * initiates the Game
     *
     */
    public void run() {
        handleDragAndShoot();
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e
                -> {
            double dt = (1.0 / tickRate);
            time += dt;
            tick(dt);
        }));
        turnManager = new TurnManager(level);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
    *
    * @param dt
    */
    protected void tick(double dt) {
        if(level instanceof StandardLevel) {
            StandardLevel level = (StandardLevel) this.level;
            if (/*gameStart*/true) {
                turnManager.updateTurn(dt);
            }
            if (level.type == 2 && turnManager.isPlayer2Turn()) {
                Fighter computer = level.team2.get(0);
                computer.launchProjectile(
                        computer.getLoadout().mainWeapon, new Vector(-97.8, -57.00), computer.getCoordinate().move(new Vector(-20, -20)));
                turnManager.projectileShot();
            }
        }
        boolean[] gameStatus = level.update(dt, time);
        gameOver = gameStatus[0];
        //gameWon = gameStatus[1];
        if(gameOver){
            timeline.stop();
            exitGame();
        }
    }

    private void exitGame() {
        //TODO
        this.setRoot(new Pane(new Label("GAMEOVER")));
    }

    protected void handleDragAndShoot() {

        final double[] dragStartX = {0.0};
        final double[] dragStartY = {0.0};
        level.trajectoryLine.setStroke(Color.GOLD);

        this.setOnMousePressed(event -> {
            dragStartX[0] = event.getSceneX();
            dragStartY[0] = event.getSceneY();
            level.dragging = true;

            if (event.getButton() == MouseButton.PRIMARY) {
                level.trajectoryLine.setStroke(Color.GOLD);//TODO ARROW
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                level.trajectoryLine.setStroke(Color.DARKGRAY);//TODO ARROW
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
                }
            }
        });

        this.setOnMouseReleased(event -> {

            double velocityX = -event.getSceneX() + dragStartX[0];
            double velocityY = -event.getSceneY() + dragStartY[0];

            level.resetTrajectoryLine();
            if(level.selectedFighter!=null && level.selectedFighter instanceof Ally && level.selectedFighter.isHighlighted() && checkvelocity(velocityX,velocityY) ){
                shoot(event, (Ally) level.selectedFighter,velocityX,velocityY);
            }
            // TODO -LAUNCH GRENADE
        });
        this.setOnKeyPressed(new pauseEvent());
    }

    private boolean checkvelocity(double velocityX, double velocityY) {
        return (new Vector(velocityX,velocityY)).magnitude() > MIN_LAUNCH_VELOCITY;
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
            if (event.getButton() == MouseButton.SECONDARY) {
                selectedFighter.launchProjectile(
                        selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY), level.origin);
                selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                turnManager.projectileShot();
            }
        }
    }

    /*public void handleFighterClick() {
        ArrayList team2;
        if(level instanceof StandardLevel){
            team2 = ((StandardLevel)level).team2;
        } else {
            team2 = null;
        }
        for (Fighter fighter : level.map.people) {
            if (fighter instanceof Ally) {
                fighter.setOnMouseReleased(event -> {
                    System.out.println("Fighter selected"); //TODO remove this in final code
                    if (level.selectedFighter != null) {
                        level.selectedFighter.setStroke(Color.TRANSPARENT);
                    }
                    level.selectedFighter = (Ally) fighter;
                    if (level instanceof StandardLevel && team2.contains(fighter)) {
                        level.origin = new Coordinate(
                                level.selectedFighter.getCoordinate().getX() - level.selectedFighter.getWidth() / 2
                                , level.selectedFighter.getCoordinate().getY() - level.selectedFighter.getHeight() / 2);
                        level.selectedFighter.setStroke(Color.DARKRED);
                    } else {
                        level.origin = new Coordinate(
                                level.selectedFighter.getCoordinate().getX() + level.selectedFighter.getWidth() / 2
                                , level.selectedFighter.getCoordinate().getY() - level.selectedFighter.getHeight() / 2);
                        level.selectedFighter.setStroke(Color.CYAN);
                    }
                });
            }
        }
    }*/

    private class pauseEvent implements EventHandler {
        @Override
        public void handle(Event t) {
            if(timeline.getStatus() == Animation.Status.PAUSED){
                isTicking = true;
                timeline.play();
            }else{
                //Debug
                Object variableOfInterest = turnManager;
                //----------------
                timeline.pause();
                //tickRate = -tickRate;
                isTicking = false;
            }
        }
    }
}
