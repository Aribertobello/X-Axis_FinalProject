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
    private Button educationModebtn;

    @FXML
    private Button freeplayBtn;

    @FXML
    private Button playbtn;

    public void switchToSubmenu(ActionEvent event) throws IOException {
        Scene submenuScene = new Scene(BattleGround.subMenuLoader().load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(submenuScene);
        stage.show();
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
}
