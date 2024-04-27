package com.example.bulletbattleground.gameObjects.obstacles;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Obstacle;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SpaceShip extends Obstacle {
    private boolean hasPlayedSound = false;


    public static final double DEFAULT_HEIGHT = 80;
    public static final double DEFAULT_WIDTH = 40;
    public static final double SPACESHIP_MASS = 190000;

    public SpaceShip(int speed, int coordinateX, int coordinateY) {

        double x1 = coordinateX - DEFAULT_WIDTH / 2, y1 = coordinateY - DEFAULT_HEIGHT / 2 - 25;
        double x2 = coordinateX + DEFAULT_WIDTH / 2, y2 = coordinateY - DEFAULT_HEIGHT / 2;
        double x3 = coordinateX + DEFAULT_WIDTH / 2, y3 = coordinateY + DEFAULT_HEIGHT / 2 - 10;
        double x4 = coordinateX + DEFAULT_WIDTH / 2 + 10, y4 = coordinateY + DEFAULT_HEIGHT / 2;
        double x5 = coordinateX - DEFAULT_WIDTH / 2, y5 = coordinateY + DEFAULT_HEIGHT / 2;

        Polygon ship = new Polygon(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        ship.setFill(Color.WHITESMOKE);
        ship.setStroke(Color.BLUE);
        this.getChildren().add(ship);
        setVelocityY(speed);
        setCoordinate(new Coordinate(coordinateX, coordinateY));
        setMass(SPACESHIP_MASS);
    }
    private void playSpaceShipSound() {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SpaceShip.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                // Set the volume to -10.0f (lower volume by 10 decibels)
                gainControl.setValue(-15.0f);
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                // Start the sound
                clip.start();
                if(BattleGround.activeGame.gameOverBox != null) {
                    clip.stop();
                }
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
           }
  }

    @Override
    public void move(double dt) {

        if (getCoordinate().getY() <= -160 || getCoordinate().getY() > 1200/*TODO max width of screen parameter*/) {
            Random random = new Random();
            getCoordinate().setX(random.nextInt((int) ((Mapp) this.getParent()).getWidth() / 4, (int) (2 * ((Mapp) this.getParent()).getWidth() / 3)));
            setVelocityY(-getVelocityY() * (random.nextInt(5, 51)) / Math.abs(getVelocityY()));
            setVelocityX(0);
            allign();
        }
        super.move(dt);
    }

    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }

    public void allign(){
        if (getVelocityY() > 0.0) {
            double x1 = getCoordinate().getX() + DEFAULT_WIDTH / 2, y1 = getCoordinate().getY() + DEFAULT_HEIGHT / 2 + 25;
            double x2 = getCoordinate().getX() - DEFAULT_WIDTH / 2, y2 = getCoordinate().getY() + DEFAULT_HEIGHT / 2;
            double x3 = getCoordinate().getX() - DEFAULT_WIDTH / 2, y3 = getCoordinate().getY() - DEFAULT_HEIGHT / 2 + 10;
            double x4 = getCoordinate().getX() - DEFAULT_WIDTH / 2 - 10, y4 = getCoordinate().getY() - DEFAULT_HEIGHT / 2;
            double x5 = getCoordinate().getX() + DEFAULT_WIDTH / 2, y5 = getCoordinate().getY() - DEFAULT_HEIGHT / 2;
            (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        } else {
            double x1 = getCoordinate().getX() - DEFAULT_WIDTH / 2, y1 = getCoordinate().getY() - DEFAULT_HEIGHT / 2 - 25;
            double x2 = getCoordinate().getX() + DEFAULT_WIDTH / 2, y2 = getCoordinate().getY() - DEFAULT_HEIGHT / 2;
            double x3 = getCoordinate().getX() + DEFAULT_WIDTH / 2, y3 = getCoordinate().getY() + DEFAULT_HEIGHT / 2 - 10;
            double x4 = getCoordinate().getX() + DEFAULT_WIDTH / 2 + 10, y4 = getCoordinate().getY() + DEFAULT_HEIGHT / 2;
            double x5 = getCoordinate().getX() - DEFAULT_WIDTH / 2, y5 = getCoordinate().getY() + DEFAULT_HEIGHT / 2;
            (((Polygon) (getChildren()).get(0))).getPoints().setAll(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
        }
    }
    @Override
    public HitBox hitBox(){
        hitBox = new HitBox(this);
        return hitBox;
    }
}
