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
import com.example.bulletbattleground.gameObjects.fighters.Computer;
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
    //TODO CLASS COMPLETE

    /**
     * @param map
     * @param type
     */
    public StandardLevel(Mapp map, int type) throws IOException {
        super(map);
        this.type = type;
    }

    @Override
    public boolean[] levelStatus(Mapp map) {
        boolean gameWon = false;
        boolean gameEnd = false;
        switch (type) {
            case 1 -> {
                if (!map.isHasLoot()) {
                    gameEnd = true;
                    gameWon = true;
                } else if (team1.isEmpty()) {
                    gameEnd = true;
                }
            }
            case 2 -> {
                if (team1.isEmpty()) {
                    gameEnd = true;
                    gameWon = false;
                } else if (team2.isEmpty()) {
                    gameEnd = true;
                    gameWon = true;
                }
            }
            case 3 -> {
                if (team1.isEmpty() || team2.isEmpty()) {
                    gameEnd = true;
                    gameWon = true;
                }
            }
        }
        return new boolean[]{gameEnd, gameWon};
    }

    public void addLoot(Loot loot) {
        map.loot = loot;
        map.getChildren().add(map.getLoot());
        map.setHasLoot(true);
    }

    @Override
    public void addFighter(Fighter fighter, int teamNb) {
        map.addFighter(fighter);
        if (teamNb == 1) {
            team1.add(fighter);
        } else {
            Image ally_Image_Inverted = new Image("file:Files/img/Light_Class_Img_Inverted.png");
            fighter.setFill(new ImagePattern(ally_Image_Inverted));
            team2.add(fighter);
        }
        fighter.setTeamNb(teamNb);
    }

    public void toPVP () {
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        ArrayList<Integer> loadoutTypes = new ArrayList<>();
        for (Fighter fighter : team2) {
            if (fighter instanceof Computer) {
                coordinates.add(fighter.getCoordinate());
                loadoutTypes.add(fighter.getLoadout().getType());
                indexes.add(team2.indexOf(fighter));
            }
        }
        for (int i = 0; i < indexes.size(); i++) {
            removeFighter(team2.get(indexes.get(i)));
        }
        for (int i = 0; i < coordinates.size(); i++) {
            addFighter(new Ally(loadoutTypes.get(i), 25, (int) coordinates.get(i).getX(), (int) coordinates.get(i).getY()), 2);
        }
        for(Fighter fighter : team1){
            fighter.setHealth(20);
            fighter.setMaxHealth(20);
        }
        for(Fighter fighter : team2){
            fighter.setHealth(20);
            fighter.setMaxHealth(20);
        }
        setType(3);
    }

    @Override
    public void removeFighter (Fighter fighter){
        if (fighter.getTeamNb() == 1) {
            team1.remove(fighter);
        } else {
            team2.remove(fighter);
        }
        map.removeFighter(fighter);
    }
}
