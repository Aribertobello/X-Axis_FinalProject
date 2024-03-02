package com.example.bulletbattleground;

import javafx.animation.Timeline;

public class Game {
    protected Boolean gameOver;
    protected Level level;
    protected Boolean gameWon;
    protected Integer tickrate;
    protected double time;
    protected Timeline timeline;

    protected void run(Level level){}
    protected void tick(Level level){}
    protected void handleClick(){}
}
