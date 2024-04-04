package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyDoubleWrapper;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends Scene {

    private double TURN_TIMER_LIMIT = 150;
    private double PROJ_TIMER_LIMIT = 150;

    protected Boolean gameOver = false;

    protected Level level;

    protected Boolean gameWon;

    protected Integer tickRate = 100;

    protected double time = 0;

    protected Timeline timeline;

    private boolean dragging = false;
    private boolean activeprojpreviousState = false;

    private boolean isTicking;
    private int turn = 0;
    boolean gameStart = false;
    private boolean activeTurn = true;
    private boolean player1turn = true;
    private double activeTurnTimer = 0;
    private double projectileTimer = 0;

    private boolean printCoordinate = false;
    Label mouseCoordinatesLabel = new Label();
    private ProgressBar healthBar = new ProgressBar();


    public Game(Level level) throws IOException {
        super(level);
        this.level = level;

        Button pausebtn = new Button("Pause");
        pausebtn.setOnAction(new pauseEvent());
        pausebtn.setPrefWidth(250);
        pausebtn.setPrefHeight(20);
        pausebtn.setLayoutX(900);
        ((AnchorPane) level.getChildren().get(0)).getChildren().add(pausebtn);
        //TODO add this to fxml and handle click
        isTicking = true;
    }

    public void run() {
        handleDragAndShoot();
        timeline = new Timeline(new KeyFrame(Duration.millis(1), e
                -> {
            double dt = (1.0 / tickRate);
            time += dt;
            tick(dt);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private boolean updateTurns(double dt) {
            if((level.map.activeProjectile==null)&&activeprojpreviousState){
                activeTurn = true;
                player1turn = !player1turn;
            }

        if (activeTurn) {
            level.map.removeActiveProjectile();
            projectileTimer = 0;
            activeTurnTimer += dt;
            if( activeTurnTimer > TURN_TIMER_LIMIT){
                player1turn = !player1turn;
                activeTurn = false;
            }
        }
        if (!activeTurn) {
            activeTurnTimer = 0;
            projectileTimer += dt;
            if (projectileTimer > PROJ_TIMER_LIMIT) {
                player1turn = !player1turn;
                activeTurn = true;
            }
        }
        if(player1turn){
            level.playerturnSquare.setStroke(Color.CYAN);
        } else {
            level.playerturnSquare.setStroke(Color.DARKRED);
        }
        if(!activeTurn){
            level.playerturnSquare.setFill(Color.BEIGE);
        } else {
            level.playerturnSquare.setFill(Color.WHITE);
        }
        level.player1Turn.setText(String.valueOf(player1turn));
        level.activeTurn.setText(String.valueOf(activeTurn));
        level.turnTimer.setText(String.valueOf(activeTurnTimer));
        level.activeProjectileTimer.setText(String.valueOf(projectileTimer));

        activeprojpreviousState = level.map.activeProjectile != null;

        return player1turn;
    }

    protected void tick(double dt) {
        if(gameStart){
            updateTurns(dt);
        }
        if(level.type == 1 && !player1turn && activeTurn){
            Fighter computer = level.team2.get(0);
               computer.launchProjectile(computer.getLoadout().mainWeapon, new Vector(-180,-63),computer.getCoordinate().move(new Vector(-20,-20)));
               activeTurn = false;
        }

        boolean[] gameStatus = level.update(dt);
        gameOver = gameStatus[0];
        gameWon = gameStatus[1];
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

        AtomicInteger clickNb = new AtomicInteger();
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
                    level.changeTrajectoryLine(
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

            //level.getHeadsUpDisplay().getChildren().add(mouseCoordinatesLabel);
            //mouseCoordinatesLabel.setText(" Mouse coordinates: " + angle + "  Degrees  ");
            //level.getHeadsUpDisplay().getChildren().add(healthBar);
            //healthBar.setMaxSize(100,100);

            level.resetTrajectoryLine();
            shoot(event,level.selectedFighter,velocityX,velocityY);
            // TODO -LAUNCH GRENADE
        });
        handleFighterClick();
        this.setOnKeyPressed(new pauseEvent());
    }

    private void shoot(MouseEvent event, Ally selectedFighter, double velocityX, double velocityY) {

        if (activeTurn) {
            if(player1turn&&level.team1.contains(selectedFighter)) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY), level.origin);
                }// TODO -LAUNCH MAIN PROJECTILE
                if (event.getButton() == MouseButton.SECONDARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY), level.origin);
                    selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                }
            } else if(!player1turn&&level.team2.contains(selectedFighter)){
                if (event.getButton() == MouseButton.PRIMARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY), level.origin);
                }// TODO -LAUNCH MAIN PROJECTILE
                if (event.getButton() == MouseButton.SECONDARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY), level.origin);
                    selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                }
            }
            activeTurn = false;
            gameStart = true;
        }
    }
    private void handleFighterClick() {
        for (Fighter fighter : level.map.people) {
            if (fighter instanceof Ally) {
                fighter.setOnMousePressed(event -> {
                    System.out.println("Fighter selected"); //TODO remove this in final code
                    if (level.selectedFighter != null) {
                        level.selectedFighter.setStroke(Color.TRANSPARENT);
                    }
                    level.selectedFighter = (Ally) fighter;
                    if (level.team2.contains(fighter)) {
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
    }

    public void storeProjStatus(){
        //if(activep)
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
