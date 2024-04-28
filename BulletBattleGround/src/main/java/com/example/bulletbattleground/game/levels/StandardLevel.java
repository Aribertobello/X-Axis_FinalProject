package com.example.bulletbattleground.game.levels;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.EducationGameController;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class StandardLevel extends Level {

    public ArrayList<Fighter> team1 = new ArrayList<>();
    public ArrayList<Fighter> team2 = new ArrayList<>();
    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();

    private String description = "";

    /**
     *
     * @param map
     * @param type
     */
    public StandardLevel(Mapp map, int type) throws IOException {
        super(map);
        this.type = type;
        switch(type){
            case 1:
                break;
            case 2,3:
                break;
        }
    }
    @Override
    public boolean[] levelStatus(Mapp map) {
        boolean gameWon = false;
        boolean gameEnd = false;
        switch (type){
            case 1 -> {
                if(!map.isHasLoot()){
                    gameEnd = true;
                    gameWon = true;
                } else if(team1.isEmpty()){
                    gameEnd = true;
                }
            }
            case 2 -> {
                if(team1.isEmpty()){
                    gameEnd = true;
                    gameWon = false;
                } else if (team2.isEmpty()){
                    gameEnd = true;
                    gameWon = true;
                }
            }
            case 3 -> {
                if(team1.isEmpty() || team2.isEmpty()){
                    gameEnd = true;
                    gameWon = true;
                }
            }
        }
        return new boolean[]{gameEnd,gameWon};
    }

    public void addLoot(Loot loot) {
        map.loot = loot;
        map.getChildren().add(map.getLoot());
        map.setHasLoot(true);
    }

    Ally ally;
    @Override
    public void addFighter(Fighter fighter, int teamNb){
        map.addFighter(fighter);
        if(teamNb==1){
            team1.add(fighter);
        } else {
            Image ally_Image_Inverted = new Image("file:Files/img/Light_Class_Img_Inverted.png");
            fighter.setFill(new ImagePattern(ally_Image_Inverted));
            team2.add(fighter);
        }
        fighter.setTeamNb(teamNb);
    }
    @Override
    public void removeFighter(Fighter fighter){
        if(fighter.getTeamNb()==1){
            team1.remove(fighter);
        } else {
            team2.remove(fighter);
        }
    }
}
