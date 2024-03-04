package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.Loot;
import com.example.bulletbattleground.utility.Arrow;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Level extends AnchorPane {



    protected Mapp map;
    protected HBox headsUpDisplay = new HBox(new Label("HUD"));
    protected Arrow trajectoryArrow;
    protected Coordinate origin;
    protected Fighter selectedFighter;
    protected String type;
    protected void update(){
        map.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        headsUpDisplay.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        map.update();
    }
    protected void displayLoadout(Fighter selectedFighter){}
    public Level(){
        this.getChildren().addAll(headsUpDisplay);
        headsUpDisplay.setAlignment(Pos.CENTER);
        headsUpDisplay.setStyle("-fx-border-color: black");
        headsUpDisplay.setMaxHeight(200);
        headsUpDisplay.setPrefHeight(200);
        headsUpDisplay.setFillHeight(false);
        headsUpDisplay.setStyle("-fx-background-color: lightblue; -fx-border-color: black");
        AnchorPane.setBottomAnchor(headsUpDisplay,5.0);
    }
    public Level(Mapp map,String type){
        this.type = type;
        if(this.type.equalsIgnoreCase("pve")){
            map.loot = new Loot(1303,700);
            map.getChildren().add(map.loot);
        }if(this.type.equalsIgnoreCase("pvp")){
        }
        this.map = map;
        this.getChildren().addAll(this.map,headsUpDisplay);
        headsUpDisplay.setAlignment(Pos.CENTER);
        headsUpDisplay.setMaxHeight(200);
        headsUpDisplay.setPrefHeight(200);
        headsUpDisplay.setFillHeight(false);
        headsUpDisplay.setStyle("-fx-background-color: white; -fx-border-color: black");
        AnchorPane.setBottomAnchor(headsUpDisplay,5.0);
    }
}