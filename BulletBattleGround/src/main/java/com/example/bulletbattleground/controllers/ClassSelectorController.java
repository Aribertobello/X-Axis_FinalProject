package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ClassSelectorController extends subMenuController {

    public Button lightClassBtn;
    public Button mediumClassBtn;
    public Button heavyClassBtn;

    public int light = 1;
    public int medium = 2;
    public int heavy = 3;

    public static boolean pvpClicked;
    public static boolean pveClicked;
    public static boolean pvcClicked;

    public boolean loadoutSelected;

    public void lightClassSelected(ActionEvent event) throws IOException {
        loadoutSelected = true;
        for (int i = 0; i < BattleGround.activeGame.getLevel().team1.size(); i++) {
            BattleGround.activeGame.getLevel().team1.get(i).setLoadout(new Loadout(1));
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }

    public void mediumClassSelected(ActionEvent event) throws IOException {
        loadoutSelected = true;
        for (int i = 0; i < BattleGround.activeGame.getLevel().team1.size(); i++) {
            BattleGround.activeGame.getLevel().team1.get(i).setLoadout(new Loadout(2));
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }


    public void heavyClassSelected(ActionEvent event) throws IOException {
        loadoutSelected = true;
        for (int i = 0; i < BattleGround.activeGame.getLevel().team1.size(); i++) {
            BattleGround.activeGame.getLevel().team1.get(i).setLoadout(new Loadout(3));
        }
        BattleGround.newScene(BattleGround.activeGame);
        BattleGround.fullscreen();
        BattleGround.activeGame.run();
    }
}
