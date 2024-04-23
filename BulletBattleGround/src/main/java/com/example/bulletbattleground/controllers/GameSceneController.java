package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class GameSceneController {

    @FXML
    private Pane headsUpDisplay;
    @FXML
    private AnchorPane container;
    @FXML
    private Label activeProjectileLabel;
    @FXML
    private Label KELabel;
    @FXML
    private Label blankLabel;
    @FXML
    private ProgressBar healthProgressbar;
    @FXML
    private Label healthLabel;
    @FXML
    private MenuBar topMenu;
    @FXML
    private Label angleLabel;
    @FXML
    private Menu newGameButton;
    @FXML
    private Menu exitButton;
    @FXML
    private Label GrenadeLabel;
    @FXML
    private Label SmokeLabel;
    @FXML
    private Label VeloLabel;
    @FXML
    private Label AccLabel;
    @FXML
    private Menu settingsButton;
    @FXML
    private Menu pauseButton;

    @FXML
    private Label MomLabel;
    @FXML
    private Line Xaxis;
    @FXML
    private Line AngleDisp;
    @FXML
    private ProgressBar VeloBar;
    @FXML
    private Line ArrLinee;
    @FXML
    private Line ArrLine;
    @FXML
    private Label BltAmount;
    @FXML
    private Label GTimer;
    @FXML
    private ImageView GImg;
    @FXML
    private ImageView BImg;

    @FXML
    private void handlePause() {
        BattleGround.activeGame.pauseGame();
    }

    @FXML
    private void handleExit(){
        BattleGround.prevScene();
    }

    @FXML
    public void initialize(){
        exitButton.setOnAction(e -> handleExit());
        pauseButton.setOnAction(e -> handlePause());
        if(GImg != null && BImg != null) {
            GImg.setImage(new Image("file:grenade.png"));
            BImg.setImage(new Image("file:smallBullet.png"));
        }
    }

    public void displayHowToPlay(ActionEvent event) {
        TabPane instructionBox;
        try {
            instructionBox = BattleGround.instructionBoxLoader().load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BattleGround.activeGame.unfocus();
        BattleGround.activeGame.getLevel().getChildren().add(instructionBox);
        BattleGround.activeGame.pauseGame();
    }
}
