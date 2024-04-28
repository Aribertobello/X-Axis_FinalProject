package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverBoxController {
    //TODO CLASS COMPLETE
    public Label gameOverLabel;
    public Label congratulationsLabel;
    public Button exitBtn;
    public VBox gameOverBox;

    public void backToLevelSelector(ActionEvent event) {
        BattleGround.activeGame.exitGame();

    }
}
