package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.DescriptionBoxController;
import com.example.bulletbattleground.controllers.GameOverBoxController;
import com.example.bulletbattleground.fileManagement.User;
import com.example.bulletbattleground.game.levels.StandardLevel;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.TurnManager;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lombok.Getter;


import java.io.IOException;
import java.util.ArrayList;

public class Game extends Scene {

    protected Boolean gameOver = false;
    @Getter
    protected Level level;
    protected Boolean gameWon = false;
    protected Integer tickRate = 100;
    protected double time = 0;
    protected Timeline timeline;
    private boolean isTicking;
    boolean gameStart = false;
    TurnManager turnManager ;

    public int tickDuration = 10;

    public Label congratulationsLabel;
    public Button exitBtn;
    public VBox gameOverBox;


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
        timeline = new Timeline(new KeyFrame(Duration.millis(tickDuration), e
                -> {
            double dt = (1.0 / tickRate);
            time += dt;
            tick(dt);
        }));
        turnManager = new TurnManager(level);
        timeline.setCycleCount(Timeline.INDEFINITE);
        if (BattleGround.user.isUnlocked(getLevel())) {
            getLevel().displayDescription();
        }
        else {
            endGame();
            congratulationsLabel.setText("Level Not Unlocked Yet");
        }
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
                computer.launchProjectile(computer.loadout.mainWeapon,StandardLevel.compShtAngles[level.index-1]);
            }
        }
        boolean[] gameStatus = level.update(dt, time);
        gameOver = gameStatus[0];
        gameWon = gameStatus[1];
        if(gameOver){
            timeline.stop();
            endGame();
        }
    }

   // public String username;

    private void endGame() {
        FXMLLoader gameOverBoxLoader = new FXMLLoader(BattleGround.class.getResource("GameOverBox.fxml"));
        try {
            gameOverBox = gameOverBoxLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameOverBoxController controller = gameOverBoxLoader.getController();
        exitBtn = controller.exitBtn;
        congratulationsLabel = controller.congratulationsLabel;
        int currentPveProgress = BattleGround.user.getPVEProgress(BattleGround.username);
        int currentPvcProgress = BattleGround.user.getPVCProgress(BattleGround.username);

        if (gameWon){ //this is a case where the user replays his previous level... it's at least gotta display congrats even if u win without updating the progress...
            congratulationsLabel.setText("CONGRATULATIONS YOU HAVE WON!");
        }

        if(gameWon && level.getIndex() >= currentPveProgress){
            BattleGround.user.updatePVEProgress(BattleGround.username, BattleGround.user.getPVEProgress(BattleGround.username)+ 1);
        }

        if(gameWon && level.getIndex() >= currentPvcProgress){
            BattleGround.user.updatePVCProgress(BattleGround.username, BattleGround.user.getPVCProgress(BattleGround.username)+ 1);
        }

       else if (!gameWon) {
            congratulationsLabel.setText("BETTER LUCK NEXT TIME");
        }

        gameOverBox.setLayoutX(BattleGround.screenWidth/3);
        gameOverBox.setLayoutY(BattleGround.screenHeight/4);
        level.container.setOpacity(0.25);
        level.getChildren().add(gameOverBox);
    }

    public void unfocus(){
        this.level.getContainer().setOpacity(0.2);
    }
    public void focus(){
        this.level.getContainer().setOpacity(1);
    }

    public void exitGame() {
        timeline.stop();
        BattleGround.prevScene();
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
            if (level.dragging && isWithinPlayerBounds(dragStartX[0],dragStartY[0]) && level.selectedFighter instanceof Ally) {
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
                    level.arrow.updateDrag(level.selectedFighter,level.getMap(),(1.0 / tickRate),level.getOrigin(),new Vector(-event.getSceneX() + dragStartX[0],-event.getSceneY() + dragStartY[0]));
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
                          level.selectedFighter.loadout.smokeGrenades.get(0), new Vector(15, 0.0));
                level.selectedFighter.loadout.smokeGrenades.remove(level.selectedFighter.loadout.smokeGrenades.get(0));
                    turnManager.projectileShot();
                    System.out.println("Smoke grenade deployed");
            }
        });
    }

    private boolean isWithinPlayerBounds(double x,double y) {
        if(level.selectedFighter!=null){
            double boundX = level.selectedFighter.getCoordinate().getX();
            double boundY = level.selectedFighter.getCoordinate().getY();
            return x < boundX + 20 && x > boundX - 20 && y < boundY + 20 && y > boundY - 20;
        }
        return  false;
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
                            selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY));
                    turnManager.projectileShot();
                }// TODO -LAUNCH MAIN PROJECTILE
                if (event.getButton() == MouseButton.SECONDARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY));
                    selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                    turnManager.projectileShot();
                }

            } else if(turnManager.isPlayer2Turn() && level.team2.contains(selectedFighter)){
                if (event.getButton() == MouseButton.PRIMARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY));
                    turnManager.projectileShot();
                }// TODO -LAUNCH MAIN PROJECTILE
                if (event.getButton() == MouseButton.SECONDARY) {
                    selectedFighter.launchProjectile(
                            selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY));
                    selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                    turnManager.projectileShot();
                }
            }
        } else {
            if (event.getButton() == MouseButton.PRIMARY) {
                selectedFighter.launchProjectile(
                        selectedFighter.loadout.mainWeapon, new Vector(velocityX, velocityY));
                turnManager.projectileShot();
            }// TODO -LAUNCH MAIN PROJECTILE
            if (event.getButton() == MouseButton.SECONDARY) {
                selectedFighter.launchProjectile(
                        selectedFighter.loadout.grenades.get(0), new Vector(velocityX, velocityY));
                selectedFighter.loadout.grenades.remove(level.selectedFighter.loadout.grenades.get(0));
                turnManager.projectileShot();
            }
        }
    }

    public void pauseGame(){
        if (timeline.getStatus() == Animation.Status.PAUSED){
            isTicking = true;
            timeline.play();
        } else {
            //Debug
            var variableOfInterest = level.map.activeProjectile.acceleration();
            //----------------
            timeline.pause();
            isTicking = false;
        }
    }

    public void play() {
        timeline.play();
    }


    public class pauseEvent implements EventHandler {
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
                //tickRate = -tickRate;
                isTicking = false;
            }
        }
    }
}
