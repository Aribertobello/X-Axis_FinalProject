package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Rocket;
import com.example.bulletbattleground.gameObjects.projectiles.Spear;
import com.example.bulletbattleground.utility.*;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class  Projectile extends MovingBody {

    public static final double TERMINAL_VELOCITY = 300;
    public static final double MIN_LAUNCH_VELOCITY = 10.0;

    @Getter
    double terminalVelocity;
    @Setter
    @Getter
    protected int damage;
    @Setter
    @Getter
    protected Vector lift;
    @Setter
    protected boolean forcesDisplayed = false;
    protected ArrayList<Arrow> forceArrows = new ArrayList<>();


    public void move(double time) {
        if(this.velocity().magnitude()>terminalVelocity){
            this.setVelocity( velocity().scale(terminalVelocity-0.5));
        }
        double x = ((acceleration().getX() / 2) * time * time + getVelocityX() * time + getCoordinate().getX());
        double y  = ((acceleration().getY() / 2) * time * time + getVelocityY() * time + getCoordinate().getY());
        this.setVelocityX(acceleration().getX() * time + getVelocityX());
        this.setVelocityY(acceleration().getY() * time + getVelocityY());
        setCoordinate(new Coordinate(x,y));
        if(forcesDisplayed){
            displayForces();
        }
    }

    /**
     * initiates the movement of this body within the system
     * @param velocity the launch velocity
     * @param coordinate the starting coordinate
     * @param Forces the forces acting on this
     */
    public void release(Vector velocity, Coordinate coordinate, Vector... Forces) {
        setVelocity(velocity);
        setCoordinate(coordinate);
        forces.clear();
        forces.add(lift);
        forces.addAll(Arrays.asList(Forces));
        if(this instanceof Bullet){
            playGunshotSound();
        }
        if(this instanceof Spear) {
            playSpearSound();
        }
        if(this instanceof Rocket) {
            playRocketSound();
        }
    }
    private void playGunshotSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Files/sound/GunShot.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-20.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    private void playRocketSound() {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Files/sound/Rocket.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-15.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    private void playSpearSound() {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Files/sound/Spear.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-10.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param vector
     */
    public void setVelocity(Vector vector) {
        setVelocityX(vector.getX());
        setVelocityY(vector.getY());
    }
    public void setTerminalVelocity(double terminalVelocity) {
        if(this.terminalVelocity<TERMINAL_VELOCITY){
            this.terminalVelocity = terminalVelocity;
        } else throw new IllegalArgumentException("terminal Velocity must be positive and musn't be greater than the TERMINAL_VELOCITY final field in class Projectile");
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
        super.setCoordinate(coordinate);
    }

    public HitBox hitBox(){
        hitBox = new HitBox(this);
        return hitBox;
    }
    public void displayForces(){
        int max =forceArrows.size();
        if(forcesDisplayed){
            for(int i = 0 ; i < max ; i++){
                this.getChildren().remove(forceArrows.get(forceArrows.size()-1));
                forceArrows.remove(forceArrows.get(forceArrows.size()-1));
            }
        }
        for( int i = 0 ; i < forces.size() ; i++){
            forceArrows.add(forces.get(i).toArrow(this.getCoordinate()));
            this.getChildren().add(forceArrows.get(i));
        }
    }
}
