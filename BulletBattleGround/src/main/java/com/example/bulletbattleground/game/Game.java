package com.example.bulletbattleground.game;

import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
        this.level.update();

    }
    protected void tick(Level level){}
    protected void handleClick(){}
}
