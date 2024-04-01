package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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

    protected String type;

    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();





    protected void update(double dt) {
        map.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        headsUpDisplay.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        map.update(dt);
    }
    public Level(){

    }
    public Level(Mapp map, String type) throws IOException {

        container = BattleGround.gameLoader().load();
        this.getChildren().add(container);
        headsUpDisplay = (Pane) (container.getChildren().get(0));
        this.type = type;
        if (this.type.equalsIgnoreCase("pve")) {
            map.loot = new Loot(screenWidth - 341, 410);
            map.getChildren().add(map.loot);
        }
        if (this.type.equalsIgnoreCase("pvp")) {
            //TODO
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
