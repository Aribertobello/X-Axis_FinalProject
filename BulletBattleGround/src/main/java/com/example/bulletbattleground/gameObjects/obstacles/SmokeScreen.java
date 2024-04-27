package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SmokeScreen extends Obstacle {

    protected int radius;

    public SmokeScreen(double radius, double coordinateX, double coordinateY) {
        setCoordinate(new Coordinate(coordinateX, coordinateY));
        ispenetrable = true;
        this.radius = (int) (radius);
        Circle ball = new Circle(coordinateX, coordinateY, radius, Color.GREY);
        Label label = new Label("SMOKE SCREEN");
        label.setLayoutX(coordinateX - radius);
        label.setLayoutY(coordinateY);
        this.getChildren().addAll(ball, label);
    }

    @Override
    public void move(double dt) {
        super.move(dt);
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
        //TODO
    }
    @Override
    public void allign() {
        this.getChildren().get(0).setLayoutX(getCoordinate().getX());
        this.getChildren().get(0).setLayoutX(getCoordinate().getY());
    }
}
