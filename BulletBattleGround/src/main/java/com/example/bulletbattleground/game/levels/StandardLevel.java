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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class StandardLevel extends Level {

    public Rectangle playerturnSquare;
    public Label activeProjectileTimer;
    public Label turnTimer;
    public Label activeTurn;
    public Label player1Turn;
    public ArrayList<Fighter> team1 = new ArrayList<>();
    public ArrayList<Fighter> team2 = new ArrayList<>();
    public int type;
    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();

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
                addLoot();
                break;
            case 2,3:
                break;
        }
        //------------------TODO in fxml
        playerturnSquare = new Rectangle(50,50, Color.WHITE);
        activeProjectileTimer = new Label("");
        activeProjectileTimer.setTextFill(Color.WHITE);
        turnTimer = new Label("");
        turnTimer.setTextFill(Color.WHITE);
        activeTurn = new Label("WOW");
        activeTurn.setTextFill(Color.WHITE);
        player1Turn = new Label("");
        player1Turn.setTextFill(Color.WHITE);
        VBox turnVariables = new VBox(10,playerturnSquare,turnTimer,activeProjectileTimer,activeTurn,player1Turn);
        turnVariables.setLayoutX(screenWidth-60);
        turnVariables.setLayoutY(90);
        container.getChildren().add(turnVariables);
        //----------------------------------------------------------
    }
    @Override
    public boolean[] update(double dt, double time) {
        updateHUD();
        if(map.update(dt)){
            switch(type) {
                case 1:
                    if(map.getLoot() ==null) return new boolean[]{true, true};
                    if(map.getPeople().isEmpty()){
                        return new boolean[]{true, false};
                    }
                    break;
                case 2:
                    if(map.getPeople().size()==1){
                        return new boolean[]{true, false};
                    }
                    //TODO gameWon or !GameWon
                    break;
                case 3:
                    if(map.getPeople().size()==1){
                        int i = 0;//TODO PVP
                        return new boolean[]{true, false};
                    }
                    break;
            }
        }
        return new boolean[]{false,false};
    }

    public void addLoot() {
        map.loot = new Loot(screenWidth - 341, 410);
        map.getChildren().add(map.getLoot());
    }
    @Override
    public void addFighter(Fighter fighter, int teamNb){
        map.addFighter(fighter);
        if(teamNb==1){
            team1.add(fighter);
        } else {
            team2.add(fighter);
        }
    }
}
