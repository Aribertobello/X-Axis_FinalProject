package com.example.bulletbattleground.game;

import com.example.bulletbattleground.game.levels.StandardLevel;
import javafx.animation.Animation;
import javafx.scene.paint.Color;
import lombok.Getter;

public class TurnManager {


    public static final double MAX_PROJ_ANIMATION_TIMER = 15.0;
    public static final double MAX_TURN_TIMER = 15.0;
    @Getter
    private int currentPlayer;
    private boolean isAnimationPlaying;
    private double turnTimer;
    private double animationTimer;
    int previousPlayer;
    boolean activeTurn = true;
    Level level;

    public TurnManager(Level level) {
        this.level = level;
        currentPlayer = 1;
        isAnimationPlaying = false;
        turnTimer = 0;
        animationTimer = 0;
    }

    public void startTurn() {
        activeTurn = true;
        if (!isAnimationPlaying) {
        } else {
            System.out.println("Projectile animation is still playing. Player cannot shoot.");//TODO UI
        }
    }

    /**
     * updates the turns of the players playing the game
     *
     * @param dt time increment of the game being run
     * @return
     */
    public void updateTurn(double dt){
         if(!isAnimationPlaying) {
            if (activeTurn && turnTimer < MAX_TURN_TIMER) {
                turnTimer+=dt;
            } else {
                turnTimer = 0;
                startAnimation();
                activeTurn = false;
            }
        } else {
            if (animationTimer < MAX_PROJ_ANIMATION_TIMER) {
                animationTimer+=dt;
            } else {
                animationTimer = 0;
                endAnimation();
                activeTurn = true;
            }
        }
         level.updateTurnBox(turnTimer,MAX_TURN_TIMER,currentPlayer);
    }

    private void endTurn() {
        System.out.println("Player " + currentPlayer + "'s turn ended");
        currentPlayer = previousPlayer == 1 ? 2 : 1;
        startTurn();
    }
    public void startAnimation() {
        previousPlayer = currentPlayer;
        currentPlayer = 0;
        isAnimationPlaying = true;
    }

    public void endAnimation() {
        level.map.removeActiveProjectile();
        isAnimationPlaying = false;
        endTurn();
    }

    public void projectileShot() {
        System.out.println("Player " + currentPlayer + " shot projectile");
        turnTimer = 15;
        activeTurn = false;
    }
    public boolean isPlayer1Turn(){
        return currentPlayer == 1;
    }
    public boolean isPlayer2Turn(){
        return currentPlayer == 2;
    }
    public boolean isAnimationPlaying(){
        return currentPlayer == 0;
    }
}