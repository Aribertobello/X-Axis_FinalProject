package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;
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
    private Label STimer;
    @FXML
    private ImageView GImg;
    @FXML
    private ImageView BImg;
    public int light = 1;
    public int medium = 2;
    public int heavy = 3;

    @FXML
    private void handlePause() {
        BattleGround.activeGame.pauseGame();
    }

    @FXML
    private void handleExit(){
        BattleGround.prevScene();
    }

    @FXML
    public void initialize() {
        exitButton.setOnAction(e -> handleExit());
        pauseButton.setOnAction(e -> handlePause());
        if (GImg != null && BImg != null) {
            GImg.setImage(new Image("file:grenade.png"));
            if (FileManager.loadoutType == light) {
                BImg.setImage(new Image("file:smallBullet.png"));
            } else if (FileManager.loadoutType == medium) {
                BImg.setImage(new Image("file:spear.png"));
            } else if (FileManager.loadoutType == heavy) {
                BImg.setImage(new Image("file:rocket.png"));
            }
        }
    }
}
