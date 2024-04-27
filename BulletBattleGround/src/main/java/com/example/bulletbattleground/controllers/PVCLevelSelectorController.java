package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

import static com.example.bulletbattleground.fileManagement.FileManager.createLevel;

public class PVCLevelSelectorController {
    //TODO Back Button
    public void launchPVCLevel1(ActionEvent event) throws IOException {

        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.1.txt"));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
        BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
    }

    public void launchPVCLevel2(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.2.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel3(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.3.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel4(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.4.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel5(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.5.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel6(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.6.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel7(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.7.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel8(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.8.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel9(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.9.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }

    public void launchPVCLevel10(ActionEvent event) {
        try{
            BattleGround.activeGame = new Game(FileManager.createLevel("Files/txt/PVC.lvl.10.txt"));
            BattleGround.mainStage.setScene(new Scene(BattleGround.classSelectorLoader().load()));
        }catch(Exception e){
            throw new ExceptionInInitializerError("Failed to Initialize The level, level and map Files were poorly formatted or level and map file paths are incorrect");
        }
    }
}

