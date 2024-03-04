package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.fighters.Ally;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                    level.selectedFighter.setStroke(Color.CYAN);
                    level.headsUpDisplay.getChildren().add(fighter.headsUpDisplay());
                });
            }
        }
        this.level.update();

    }
    protected void tick(Level level){}
    protected void handleClick(){}
}
