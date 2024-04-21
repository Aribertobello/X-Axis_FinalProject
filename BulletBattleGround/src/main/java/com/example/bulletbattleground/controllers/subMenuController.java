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

public class subMenuController {

    public  Button exitBtn ;
    public  Button mainMenuBtn ;
    public Button player_environ_btn;//TODO
    public Button player_player_btn;
    public Button player_computer_btn;

    /**
     * @param event Action event for a button to create new scene for player vs computer Game mode.
     */
    public void player_computer_btn_clicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(BattleGround.pvcLevelSelectorLoader().load()));
        stage.show();
    }
    /**
     * @param event Action event for a button to create new scene for player vs environment game mode.
     */
    public void player_environ_btn_clicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(BattleGround.pveLevelSelectorLoader().load()));
        stage.show();
    }
    /**
     *@param event Action handler for a button to create new scene for player vs player game mode.
     */
    public void player_player_btn_clicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(BattleGround.pvpLevelSelectorLoader().load()));
        stage.show();
    }

    @FXML
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
}
