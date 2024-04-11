package com.example.bulletbattleground.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    private void handleExit(){
        Platform.exit();
    }
    @FXML
    private Menu settingsButton;
    @FXML
    private Menu pauseButton;
    @FXML
    private Label MomLabel;
    @FXML
    private void handlePause() {

    }
    @FXML
    public void initialize(){
        exitButton.setOnAction(e -> handleExit());
        pauseButton.setOnAction(e -> handlePause());
    }
}
