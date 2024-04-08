package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PVCLevelSelectorController {
    public Button level1Btn;
    public Button level2Btn;
    public Button level3Btn;
    public Button level4Btn;

    public void launchLevelOne(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPvc());
        }catch(
                IOException e){
            e.printStackTrace();
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchLevel2(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPvc());
        }catch(
                IOException e){
            e.printStackTrace();
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchLevel3(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPvc());
        }catch(
                IOException e){
            e.printStackTrace();
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchLevel4(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPvc());
        }catch(
                IOException e){
            e.printStackTrace();
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }
}

