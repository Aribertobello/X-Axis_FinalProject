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

public class GameSceneController {
    @FXML
    @Getter
    @Setter
    private Pane headsUpDisplay;
    @Getter
    @Setter
    @FXML
    private AnchorPane container;
    @Getter
    @Setter
    @FXML
    private Label activeProjectileLabel;
    @Getter
    @Setter
    @FXML
    private Label KELabel;
    @Getter
    @Setter
    @FXML
    private Label blankLabel;
    @Getter
    @Setter
    @FXML
    private ProgressBar healthProgressbar;
    @Getter
    @Setter
    @FXML
    private Label healthLabel;
    @Getter
    @Setter
    @FXML
    private MenuBar topMenu;
    @Getter
    @Setter
    @FXML
    private Label angleLabel;
    @Getter
    @Setter
    @FXML
    private Menu newGameButton;
    @Getter
    @Setter
    @FXML
    private Menu exitButton;
    @FXML
    private void handleExit(){
        Platform.exit();
    }
    @Getter
    @Setter
    @FXML
    private Menu settingsButton;
    @Getter
    @Setter
    @FXML
    private Menu pauseButton;
    @FXML
    private void handlePause() {

    }
    @FXML
    public void initialize(){
        exitButton.setOnAction(e -> handleExit());
        pauseButton.setOnAction(e -> handlePause());
    }
}
