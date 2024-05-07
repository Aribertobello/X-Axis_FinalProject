package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.levels.EduLevel;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MapSelectorScene {

    public void earthSelected(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(new EduLevel(new Mapp("earth")));
        }catch(IOException e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void spaceSelected(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(new EduLevel(new Mapp("space")));
        }catch(IOException e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }
}
