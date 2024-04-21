package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.levels.StandardLevel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PVPLevelSelectorController {
    public Button level1Btn;
    public Button level2Btn;
    public Button level3Btn;
    public Button level4Btn;


    public void map1Selected(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
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
            BattleGround.activeGame = new Game(new StandardLevel(FileManager.createMap("Files/txt/PVC.map.lvl.1.txt"),3));
        }catch(
                Exception e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }
}

