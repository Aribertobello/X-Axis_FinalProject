package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class subMenuController{

    public  Button exitBtn ;
    public  Button mainMenuBtn ;
    public Button player_environ_btn;
    public Button player_player_btn;
    public Button player_computer_btn;

    /**
     * @param event Action event for a button to create new scene for player vs computer Game mode.
     */
    public void player_computer_btn_clicked(ActionEvent event) throws IOException {
        ClassSelectorController.pvcClicked = true;
        BattleGround.newScene(new Scene(BattleGround.pvcLevelSelectorLoader().load()));
    }
    /**
     * @param event Action event for a button to create new scene for player vs environment game mode.
     */
    public void player_environ_btn_clicked(ActionEvent event) throws IOException {
        ClassSelectorController.pveClicked = true;
        BattleGround.newScene(new Scene(BattleGround.pveLevelSelectorLoader().load()));
    }
    /**
     *@param event Action handler for a button to create new scene for player vs player game mode.
     */
    public void player_player_btn_clicked(ActionEvent event) throws IOException {
        ClassSelectorController.pvpClicked = true;
        BattleGround.newScene(new Scene(BattleGround.pvpLevelSelectorLoader().load()));
    }

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        BattleGround.prevScene();
    }

    @FXML
    void ExitGame(ActionEvent event) {
        Platform.exit();
    }
}
