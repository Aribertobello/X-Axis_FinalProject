package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PVELevelSelectorController {

    public void launchPVELevel1(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.1.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel2(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.2.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel3(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.3.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel4(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.4.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel5(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.5.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel6(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.6.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel7(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.7.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel8(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.8.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel9(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.9.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchPVELevel10(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVE.lvl.10.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();

    }
}
