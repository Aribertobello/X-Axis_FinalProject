package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

import static com.example.bulletbattleground.fileManagement.FileManager.createLevel;

public class PVCLevelSelectorController {

    public void launchPVCLevel1(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.1.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel2(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.2.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel3(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.3.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel4(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.4.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel5(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.5.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel6(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.6.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel7(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.7.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel8(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.8.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel9(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.9.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVCLevel10(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.10.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }
}

