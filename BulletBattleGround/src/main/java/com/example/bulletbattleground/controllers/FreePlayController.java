package com.example.bulletbattleground.controllers;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.gameObjects.obstacles.SmokeScreen;
import com.example.bulletbattleground.gameObjects.obstacles.SpaceShip;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.gameObjects.projectiles.SmokeGrenade;
import com.example.bulletbattleground.utility.Coordinate;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
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
    public Slider wallMassSlider;
    public Slider wallRotationSlider;
    public Slider wallWidthSlider;
    public Slider wallHeightSlider;
    public Slider radiusSlider;
    public ToggleGroup compClassGroup;
    public ToggleGroup allyClassGroup;
    public Slider spaceShipSpeedslider;
    Fighter fighter;
    int allyLoadoutNb = 1;
    int compLoadoutNb = 1;
    Obstacle obstacle;
    ImageView imageView;
    private Coordinate coordinate;
    private double translateX, translateY;
    private Coordinate returnCoordinate;

    public void allyDragStart(MouseEvent event) {
        fighter = new Ally(allyLoadoutNb,25,0,0);
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }
    public void compDragStart(MouseEvent event) {
        fighter = new Computer(compLoadoutNb,15,0,0);
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }
    public void wallDragStart(MouseEvent event) {
        obstacle = new Wall((int)wallHeightSlider.getValue(),(int)wallWidthSlider.getValue(),0, 0, (int)wallMassSlider.getValue());
        obstacle.rotate(wallRotationSlider.getValue());
        imageView = (ImageView) event.getSource();
        returnCoordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        coordinate = new Coordinate(event.getSceneX(),event.getSceneY());
        translateX = imageView.getTranslateX();
        translateY = imageView.getTranslateY();
    }
    public void spaceShipDragStart(MouseEvent event) {
        obstacle = new SpaceShip((int)spaceShipSpeedslider.getValue(),0,0);
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

    public void dragEndFighter(MouseEvent event) {
        fighter.setCoordinate(coordinate);
        Level level = ((Level)((Node)event.getSource()).getParent().getParent().getParent());
        level.addFighter(fighter,0);

        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        level.setSelectedFighter(fighter);
    }

    public void dragEndWall(MouseEvent event) {
        obstacle.setCoordinate(coordinate);
        Level level = ((Level) ((Node) event.getSource()).getParent().getParent().getParent());
        level.getMap().addObstacle(obstacle);

        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
    }

    public void dragEndSpaceShip(MouseEvent event) {
        obstacle.setCoordinate(coordinate);
        Level level = ((Level)((Node)event.getSource()).getParent().getParent().getParent());
        level.getMap().addObstacle(obstacle);

        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        level.setSelectedFighter(fighter);
    }

    public void dragEndSmokeScreen(MouseEvent event) {
        SmokeScreen s1 = new SmokeScreen((int)radiusSlider.getValue(), coordinate.getX(), coordinate.getY());
        s1.createSmokeScreen();
        Level level = ((Level) ((Node) event.getSource()).getParent().getParent().getParent());
        level.getMap().addObstacle(s1);

        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
    }

    public void initialize(){
        allyImageView.setImage(new Image("file:Files/img/Light_Class_Img.png"));
        computerImageView.setImage(new Image("file:Files/img/Light_Class_Img.png"));
        wallImageView.setImage(new Image("file:Files/img/WallTemporary.jpg"));
        spaceShipImageView.setImage(new Image("file:rocket.png"));
        smokeScreenImageView.setImage(new Image("file:s4.png"));
    }

    public void allyLightClassChosen(ActionEvent event) {
        allyLoadoutNb = 1;
    }

    public void allyMedClassChosen(ActionEvent event) {
        allyLoadoutNb = 2;
    }

    public void allyHeavyClassChosen(ActionEvent event) {
        allyLoadoutNb = 3;
    }
}
