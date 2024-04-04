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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

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





    Rectangle playerturnSquare;
    Label activeProjectileTimer;
    Label turnTimer;
    Label activeTurn;
    Label player1Turn;




    @Getter
    @Setter
    protected boolean dragging = false;

    public Mapp map;

    protected Line trajectoryLine = new Line();//TODO
    protected ArrayList<Fighter> team1 = new ArrayList<>();
    protected ArrayList<Fighter> team2 = new ArrayList<>();

    protected Coordinate origin;

    protected Ally selectedFighter;
    protected int difficulty;

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
        this.map = map;
        if (this.type == 0) {
            addLoot();
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
        //

        container.getChildren().add(this.map);
        map.toBack();
        this.getChildren().add(trajectoryLine);// TODO arrow
        headsUpDisplay.setMaxHeight(200);
        headsUpDisplay.setPrefHeight(200);
        headsUpDisplay.setMaxWidth(screenWidth);
    }

    public void addLoot() {
        map.loot = new Loot(screenWidth - 341, 410);
        map.getChildren().add(map.loot);
    }
    public void addFighter(Fighter fighter, int teamNb){
        map.addFighter(fighter);
        if(teamNb==1){
            team1.add(fighter);
        } else {
            team2.add(fighter);
        }

    }


    protected void displayLoadout(Fighter selectedFighter) {
        //TODO
    }

    public void resetTrajectoryLine() {
        trajectoryLine.setStartX(0);
        trajectoryLine.setStartY(0);
        trajectoryLine.setEndX(0);
        trajectoryLine.setEndY(0);
        dragging = false;
    }

    public void changeTrajectoryLine(double startX, double startY, double endX, double endY) {
        trajectoryLine.setStartX(startX);
        trajectoryLine.setStartY(startY);
        trajectoryLine.setEndX(endX);
        trajectoryLine.setEndY(endY);
    }
}
