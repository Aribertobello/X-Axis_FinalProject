package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Level;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DescriptionBoxController {
    public Label descriptionLabel;
    public Button playBtn;
    public VBox descriptionBox;

    public void playGame(ActionEvent event) {

        ((Level)descriptionBox.getParent()).getChildren().remove(descriptionBox);
        BattleGround.activeGame.getLevel().getContainer().setOpacity(1);
        BattleGround.activeGame.play();
        BattleGround.activeGame.gameStart = true;
    }
    public void initialize(){
        descriptionBox.setLayoutX(BattleGround.screenWidth/3);
        descriptionBox.setLayoutY(BattleGround.screenHeight/4);
    }
}
