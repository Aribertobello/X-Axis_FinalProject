package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class Level extends AnchorPane {

    @Getter
    @Setter
    protected boolean dragging = false;

    protected Mapp map;

    protected HBox headsUpDisplay = new HBox(new Label("HUD"));
    // Label angleLabel = new Label("Angle: " + AngleCalculation);

    protected Line trajectoryLine = new Line();//TODO

    protected Coordinate origin;

    protected Ally selectedFighter;

    protected String type;

    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();

    /**
     *
     * @param dt
     */
    protected void update(double dt) {
        map.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        headsUpDisplay.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        map.update(dt);
    }

    /**
     *
     */
    public Level() {
        this.getChildren().addAll(headsUpDisplay);
        headsUpDisplay.setAlignment(Pos.CENTER);
        headsUpDisplay.setStyle("-fx-border-color: black");
        headsUpDisplay.setMaxHeight(200);
        headsUpDisplay.setPrefHeight(200);
        headsUpDisplay.setFillHeight(false);
        headsUpDisplay.setStyle("-fx-background-color: lightblue; -fx-border-color: black");
        AnchorPane.setBottomAnchor(headsUpDisplay, 5.0);
    }

    /**
     *
     * @param map
     * @param type
     */
    public Level(Mapp map, String type) {
        this.type = type;

        if (this.type.equalsIgnoreCase("pve")) {
            map.loot = new Loot(screenWidth - 341, 410);
            map.getChildren().add(map.loot);
        }

        if (this.type.equalsIgnoreCase("pvp")) {
            //TODO
        }

        this.map = map;
        this.getChildren().addAll(this.map, headsUpDisplay);
        this.getChildren().add(trajectoryLine);// TODO arrow
        headsUpDisplay.setMaxHeight(200);
        headsUpDisplay.setPrefHeight(200);
        headsUpDisplay.setFillHeight(false);
        headsUpDisplay.setMaxWidth(screenWidth);
        headsUpDisplay.setStyle("-fx-background-color: white; -fx-border-color: black");
        AnchorPane.setBottomAnchor(headsUpDisplay, 5.0);
    }

    /**
     *
     * @param selectedFighter
     */
    protected void displayLoadout(Fighter selectedFighter) {
        //TODO
    }

}
