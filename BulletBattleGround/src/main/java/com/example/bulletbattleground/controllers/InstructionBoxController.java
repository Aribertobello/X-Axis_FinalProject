package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class InstructionBoxController {
    //TODO Add more info

    public Button backBtn1;
    public Button backBtn2;

    public void back(ActionEvent event) {
        BattleGround.activeGame.focus();
        BattleGround.activeGame.getLevel().getChildren().remove(BattleGround.activeGame.getLevel().getChildren().size()-1);
        BattleGround.activeGame.pauseGame();
    }
}
