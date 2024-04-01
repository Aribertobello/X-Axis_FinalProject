package com.example.bulletbattleground.controllers;
import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuController {

    @FXML
    private Button player_computer_btn;

    @FXML
    private Button player_environ_btn;

    @FXML
    private Button player_player_btn;

    private Button educationModebtn;

    @FXML
    private Button freeplayBtn;

    private Button playbtn;

    private Button EXITBTN;

    private Button MAINMENUBTN;


    public void player_computer_btn_clicked(ActionEvent event)  {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPvc());
        }catch(IOException e){
            e.printStackTrace();
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void player_environ_btn_clicked(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPve());
        }catch(IOException e){
         e.printStackTrace();
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void player_player_btn_clicked(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPve());
        }catch(IOException e){
            e.printStackTrace();
        }
        //double screenWidth = Screen.getPrimary().getBounds().getWidth();
        //System.out.println("Screen width: " + screenWidth + " pixels");
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void switchToSubmenu(ActionEvent event) throws IOException {
        Scene submenuScene = new Scene(BattleGround.subMenuLoader().load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(submenuScene);
        stage.show();
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         Scene mainMenuScene = new Scene(BattleGround.mainMenuLoader().load());
         stage.setScene(mainMenuScene);
         stage.show();
    }

    @FXML
    void ExitGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void freePlayButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try{
            BattleGround.activeGame = new Game(FileManager.defaultLevelPve());
        }catch(IOException e){
            e.printStackTrace();
        }
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void launchLevelOne(ActionEvent event) {
    }
}
