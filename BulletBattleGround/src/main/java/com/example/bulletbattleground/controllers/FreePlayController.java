package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreePlayController {
    
    public ImageView allyImageView;
    public ImageView smokeScreenImageView;
    public ImageView computerImageView;
    public ImageView wallImageView;
    public ImageView spaceShipImageView;
    public Pane freePlayUI;
    Fighter fighter;
    ImageView imageView;
    private Coordinate coordinate;
    private double translateX, translateY;
    private Coordinate returnCoordinate;

    public void allyDragStart(MouseEvent event) {
        fighter = new Ally(0,0,1);
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }
    public void compDragStart(MouseEvent event) {
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }
    public void wallDragStart(MouseEvent event) {
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }
    public void spaceShipDragStart(MouseEvent event) {
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }
    public void smokeScreenDragStart(MouseEvent event) {
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }

    public void dragging(MouseEvent event) {
        coordinate.setX(event.getSceneX());
        coordinate.setY(event.getSceneY());
        translateX = -returnCoordinate.getX() + coordinate.getX();
        translateY = -returnCoordinate.getY() + coordinate.getY();
        double offsetX = event.getSceneX() - coordinate.getX();
        double offsetY = event.getSceneY() - coordinate.getY();
        double newTranslateX = translateX + offsetX;
        double newTranslateY = translateY + offsetY;

        imageView.setTranslateX(newTranslateX);
        imageView.setTranslateY(newTranslateY);
    }

    public void dragEnd(MouseEvent event) {
        fighter.setCoordinate(coordinate);
        Level level = ((Level)((Node)event.getSource()).getParent().getParent().getParent());
        level.addFighter(fighter,0);

        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        BattleGround.activeGame.handleFighterClick();
    }

    public void initialize(){
        allyImageView.setImage(new Image("file:Light_Class_Img.png"));
    }
}
