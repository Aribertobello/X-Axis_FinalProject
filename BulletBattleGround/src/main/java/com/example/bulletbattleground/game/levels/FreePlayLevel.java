package com.example.bulletbattleground.game.levels;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.EducationGameController;
import com.example.bulletbattleground.controllers.FreePlayController;
import com.example.bulletbattleground.controllers.GameSceneController;
import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class FreePlayLevel extends Level {
    //TODO Check controller

    public ImageView allyImageView;
    public ImageView smokeScreenImageView;
    public ImageView computerImageView;
    public ImageView wallImageView;
    public ImageView spaceShipImageView;
    public Pane freePlayUI;



    public FreePlayLevel(Mapp map) throws IOException {
        super(map);
        map.setBounds(new double[]{5000,5000});
        type = 0;
        freePlayGame();
    }

    public boolean[] update(double dt,double time) {
       return super.update( dt, time);
    }

    public void freePlayGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(BattleGround.class.getResource("FreePlayScene.fxml"));
        freePlayUI = loader.load();
        freePlayUI.setLayoutX(BattleGround.screenWidth-250);
        container.getChildren().remove(turnStatusBox);
        container.getChildren().add(freePlayUI);
        FreePlayController controller = loader.getController();
        allyImageView = controller.getAllyImageView();
        smokeScreenImageView = controller.getSmokeScreenImageView();
        computerImageView = controller.getComputerImageView();
        wallImageView = controller.getWallImageView();
        spaceShipImageView = controller.getSpaceShipImageView();
    }
}
