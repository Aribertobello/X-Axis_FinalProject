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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

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

    /**
     * @param event Action event for a button to create new scene for player vs computer Game mode.
     */

    public void player_computer_btn_clicked(ActionEvent event)  {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BattleGround.activeGame = new Game(FileManager.defaultLevelPvc());
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }
    /**
     * @param event Action event for a button to create new scene for player vs environment game mode.
     */
    public void player_environ_btn_clicked(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BattleGround.activeGame = new Game(FileManager.defaultLevelPve());
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }

    /**
     *@param e Action handler for a button to create new scene for player vs player game mode.
     */
    public void player_player_btn_clicked(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        BattleGround.activeGame = new Game(FileManager.defaultLevelPvp());
        //double screenWidth = Screen.getPrimary().getBounds().getWidth();
        //System.out.println("Screen width: " + screenWidth + " pixels");
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(false);
        stage.show();
        BattleGround.activeGame.run();
    }

    /**
     *@param event Action event for a button to create new scene called submenuScene where a player chooses his game mode.
     */
    public void switchToSubmenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("submenuScene.fxml"));
        Scene submenuScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(submenuScene);
        stage.show();
    }

    /**
     *@param event Action Event for a button to go back to the Main menu from the submenu by loading the FXML file.
     */
    public void switchToMainMenu(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         Scene mainMenuScene = new Scene(fxmlLoader.load());
         stage.setScene(mainMenuScene);
         stage.show();
    }

    /**
     *@param event Action Event to Exit the game by clicking a button from the sub menu.
     */
    @FXML
    void ExitGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     *@param actionEvent Action event for a button to create new scene for the free play game mode.
     */
    @FXML
    public void freePlayButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        BattleGround.activeGame = new Game(FileManager.defaultLevelPve());
        stage.setScene(BattleGround.activeGame);
        stage.setMaximized(true);
        stage.show();
        BattleGround.activeGame.run();
    }
}
