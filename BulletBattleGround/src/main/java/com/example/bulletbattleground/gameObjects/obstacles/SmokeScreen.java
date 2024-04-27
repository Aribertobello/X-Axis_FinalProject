package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class SmokeScreen extends Obstacle {

    protected double radius;
    protected double coordinateX;
    protected double coordinateY;
    protected double upTime1;
    protected double upTime2;
    protected double upTime3; //The other upTimes are used to make sure the methods are called once
    protected Timeline animationTimeLine;
    Circle ball = new Circle();
    private final Image smokeEffect4 = new Image("file:s4.png");
    private final Image smokeEffect5 = new Image("file:s5.png");
    private int index = 0;
    double angle = 0.0;

    protected final Image[] appearingsSmokeEffects = {smokeEffect4, smokeEffect5};

    /**
     * Sets up the smoke screen by by creating the values of the circle in another method
     * @param radius The radius of the smoke screen
     * @param coordinateX The x position of the smoke screen
     * @param coordinateY The Y position of the smoke screen
     */
    public SmokeScreen(double radius, double coordinateX, double coordinateY) {
        setCoordinate(new Coordinate(coordinateX, coordinateY));
        ispenetrable = true;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.radius = radius;
        upTime1 = 15.0;
        upTime2 = 15.0;
        upTime3 = 15.0;
    }

    /**
     * Loads user data from the file managed by this FileManager and checks if the provided username and password match the stored credentials from the player.
     *
     */
    @Override
    public void move(double dt) {
        super.move(dt);
        upTime1 -= dt;
        upTime2 -= dt;
        upTime3 -= dt;

        if(upTime1 >= 10.0){
            ball.setRadius(radius);
            radius += 0.3;
        }

        if (upTime1 <= 0.0){
            removeSmokeScreen();
        }

        if (upTime2 <= 7.0){
            createDisappearSmokeAnimationTimeline();
        }

        if (upTime3 <= 4.0){
            ball.setRadius(radius);
            radius -= 0.5;
        }

        smokeAnimationRotate();
    }

    public void createDisappearSmokeAnimationTimeline() {
        upTime2 = Math.sqrt(-1); //Calls the method only once
        animationTimeLine = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> disappearingSmokeAnimation()));
        animationTimeLine.setCycleCount(Timeline.INDEFINITE);
        animationTimeLine.play();
    }

    public void smokeAnimationRotate() {
        ball.setRotate(angle); //Makes a small little animation instead of a boring ass picture lol
        angle += 0.15;
    }

    public void createSmokeScreen(){
        ball.setRadius(radius);
        ball.setCenterX(coordinateX);
        ball.setCenterY(coordinateY);
        this.getChildren().add(ball);
        ball.setFill(new ImagePattern(smokeEffect4));
    }

    public void disappearingSmokeAnimation() {
        ball.setFill(new ImagePattern(appearingsSmokeEffects[index]));

        if (index < 2) {
            index++;
        } else {
            animationTimeLine.stop();
            BattleGround.activeGame.getLevel().map.removeActiveProjectile();
        }

    }

    private void removeSmokeScreen() {
        upTime1 = Math.sqrt(-1); //calls the method once
        this.getChildren().remove(0);
    }

    @Override
    public void bounce(HitBox hitBox) {
    }

    @Override
    public void allign() {
    }
}
