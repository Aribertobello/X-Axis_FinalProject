package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import java.io.IOException;


public class MainMenuController {

    /**
     *@param event Action event for a button to create new scene called submenuScene where a player chooses his game mode.
     */
    public void switchToSubmenu(ActionEvent event) throws IOException {
        Scene submenuScene = new Scene(BattleGround.subMenuLoader().load());
        BattleGround.center();
        BattleGround.newScene(submenuScene);
    }

    /**
     *@param event Action Event to Exit the game by clicking a button from the sub menu.
     */
    @FXML
    void exitGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     *@param actionEvent Action event for a button to create new scene for the free play game mode.
     */
    @FXML
    public void freePlayButton(ActionEvent actionEvent) {
        try{
            BattleGround.activeGame = new Game(FileManager.freePlayLevel());
        }catch(IOException e){
            e.printStackTrace();
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }
    @FXML
    public void selectEduMap(ActionEvent actionEvent) throws IOException {
            BattleGround.mainStage.setScene(new Scene(BattleGround.mapSelectorLoader().load()));
    }
}
