package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class Level extends AnchorPane {

    @FXML
    @Getter
    @Setter
    private Pane headsUpDisplay;

    @FXML
    private AnchorPane container;

    @FXML
    private Label activeProjectileLabel;

    @FXML
    private Label angleLabel;

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
    private Menu exitButton;

    @FXML
    private Menu settingsButton;

    @FXML
    private Menu pauseButton;

    @Getter
    @Setter
    protected boolean dragging = false;

    public Mapp map;

    protected Line trajectoryLine = new Line();//TODO

    protected Coordinate origin;

    protected Ally selectedFighter;

    protected int type;

    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();


    protected boolean[] update(double dt) {
        map.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        headsUpDisplay.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        if(map.update(dt)){
            switch(type) {
                case 0:
                    if(map.loot==null) return new boolean[]{true, true};
                    if(map.people.isEmpty()){
                        return new boolean[]{true, false};
                    }
                    break;
                case 1:
                    if(map.people.size()==1){
                        return new boolean[]{true, false};
                    }
                    //TODO gameWon or !GameWon
                    break;
                case 2:
                    if(map.people.size()==1){
                        return new boolean[]{true, false};
                    }
                    break;
            }
        }
        return new boolean[]{false,false};
    }
    public Level(){}
    public Level(Mapp map, int type) throws IOException {

        container = BattleGround.gameLoader().load();
        this.getChildren().add(container);
        headsUpDisplay = (Pane) (container.getChildren().get(0));
        this.type = type;
        if (this.type == 0) {
            map.loot = new Loot(screenWidth - 341, 410);
            map.getChildren().add(map.loot);
        }
        this.map = map;
        container.getChildren().add(this.map);
        map.toBack();
        this.getChildren().add(trajectoryLine);// TODO arrow
        headsUpDisplay.setMaxHeight(200);
        headsUpDisplay.setPrefHeight(200);
        headsUpDisplay.setMaxWidth(screenWidth);
    }

    protected void displayLoadout(Fighter selectedFighter) {
        //TODO
    }
}
