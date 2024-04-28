package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.levels.StandardLevel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PVPLevelSelectorController {
    //TODO IMplement cgeck
    public Button level1Btn;
    public Button level2Btn;
    public Button level3Btn;
    public Button level4Btn;


    public void map1Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.1.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);
        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map2Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.2.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map3Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.3.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map4Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.4.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map5Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.5.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map6Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.6.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map7Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.7.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map8Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.8.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map9Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.9.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void map10Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            StandardLevel level = (StandardLevel) FileManager.createLevel("Files/txt/PVC.lvl.10.txt");
            level.toPVP();
            BattleGround.activeGame = new Game(level);        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }
}

