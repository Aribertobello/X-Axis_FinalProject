package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class GameTest extends Scene {
    private final double TURN_TIMER_LIMIT = 5;
    private final double PROJ_TIMER_LIMIT = 150;
    protected Boolean gameOver = false;
    protected Level level;
    protected Boolean gameWon;
    protected Integer tickRate = 100;
    protected double time = 0;
    protected Timeline timeline;
    private boolean activeprojpreviousState = false;
    private boolean isTicking;
    boolean gameStart = false;
    private boolean activeTurn = true;
    private boolean player1turn = true;
    private double activeTurnTimer = 0;
    private double projectileTimer = 0;


    /**
     * creates a game object
     * since the game class is a child of the Scene Class it includes its constructor, where the root must be specified
     * the root in this case is the level which it will run
     *
     * @param level the level this game object will run
     */
    public GameTest(Level level) throws IOException {
        super(level);
        this.level = level;

        Button pausebtn = new Button("Pause");
        pausebtn.setOnAction(new GameTest.pauseEvent());
        pausebtn.setPrefWidth(250);
        pausebtn.setPrefHeight(20);
        pausebtn.setLayoutX(900);
        level.getChildren().add(pausebtn);

        //TODO add this to fxml and handle click
        isTicking = true;
    }

    /**
     * initiates the Game
     */
    public void run() {
        handleDragAndShoot();
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e
                -> {
            double dt = (1.0 / tickRate);
            time += dt;
            tick(dt);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * updates the turns of the players playing the game
     *
     * @param dt time increment of the game being run
     * @return
     */
    private boolean updateTurns(double dt) {
        if ((level.map.activeProjectile == null) && activeprojpreviousState) {
            activeTurn = true;
            player1turn = !player1turn;
        }
        if (activeTurn) {
            level.map.removeActiveProjectile();
            projectileTimer = 0;
            activeTurnTimer += dt;
            if (activeTurnTimer > TURN_TIMER_LIMIT) {
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
        activeprojpreviousState = level.map.activeProjectile != null;
        return player1turn;
    }

    /**
     * @param dt
     */
    protected void tick(double dt) {
        if (false/*TODO*/) {
            updateTurns(dt);
        }
        boolean[] gameStatus = level.update(dt, time);
        gameOver = gameStatus[0];
        if(gameStatus.length>1){ gameWon = gameStatus[1];}
        if (gameOver) {
            timeline.stop();
            exitGame();
        }
    }

    private void exitGame() {
        //TODO

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
            shoot(event, level.selectedFighter, velocityX, velocityY);
            // TODO -LAUNCH GRENADE
        });
        handleFighterClick();
        this.setOnKeyPressed(new GameTest.pauseEvent());
    }

    private void shoot(MouseEvent event, Ally selectedFighter, double velocityX, double velocityY) {

        if (activeTurn) {
            if (player1turn) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY), level.origin);
                }// TODO -LAUNCH MAIN PROJECTILE
                if (event.getButton() == MouseButton.SECONDARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY), level.origin);
                    selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                }
            } else if (!player1turn) {
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
            gameStart = true;
        }
    }

    public void handleFighterClick() {
        for (Fighter fighter : level.map.people) {
            if (fighter instanceof Ally) {
                fighter.setOnMousePressed(event -> {
                    System.out.println("Fighter selected"); //TODO remove this in final code
                    if (level.selectedFighter != null) {
                        level.selectedFighter.setStroke(Color.TRANSPARENT);
                    }
                    level.selectedFighter = (Ally) fighter;
                    if (true) {
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

    public class pauseEvent implements EventHandler {
        @Override
        public void handle(Event t) {
            if (timeline.getStatus() == Animation.Status.PAUSED) {
                isTicking = true;
                timeline.play();
            } else {
                //Debug
                Object variableOfInterest = level.map.activeProjectile;
                //----------------
                timeline.pause();
                //tickRate = -tickRate;
                isTicking = false;
            }
        }
    }
}