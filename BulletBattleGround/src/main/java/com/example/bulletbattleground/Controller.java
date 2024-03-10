package com.example.bulletbattleground;
import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    @FXML
    private Button player_computer_btn;
    private Button player_environ_btn;

    private Button player_player_btn;

    private Button educationModebtn;

    private Button freeplayBtn;

    private Button playbtn;

    private Button EXITBTN;

    private Button MAINMENUBTN;


    public void player_computer_btn_clicked(ActionEvent event)  {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }
    public void player_environ_btn_clicked(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        BattleGround.activeGame = new Game(FileManager.defaultLevelPve());
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void player_player_btn_clicked(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    public void switchToSubmenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("submenuScene.fxml"));
        Scene submenuScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(submenuScene);
        stage.show();
    }
    public void switchToMainMenu(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         Scene mainMenuScene = new Scene(fxmlLoader.load());
         stage.setScene(mainMenuScene);
         stage.show();
    }

    @FXML
    void ExitGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
