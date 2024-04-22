package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Bullet extends Projectile {
    private boolean hasPlayedSound = false;

    /**
     * Constructs a new Bullet object.
     * Creates the Bullet object using a circle shape and an image.
     * The bullet has a default damage value of 3, upward lift force, and a set mass.
     */
    public Bullet() {
        Circle small_bullet = new Circle(10);
        small_bullet.setRotate(90);
        Image smallBulletImg = new Image("file:Files/img/smallBullet.png");
        small_bullet.setFill(new ImagePattern(smallBulletImg));
        this.getChildren().add(small_bullet);
        this.damage = 3;
        this.lift = new Vector(0, -2.0);
        this.forces.add(lift);
        setTerminalVelocity(95);
        setMass(0.5);

    }
    private void playGunshotSound() {
          try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("GunShot.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
              clip.addLineListener(event -> {
                  if (event.getType() == LineEvent.Type.STOP) {
                      clip.close(); // Close the clip after it finishes playing
                  }
              });
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bounce(HitBox hitBox) {
        System.out.println(velocity().angle());
        System.out.println(velocity());
    }

    @Override
    public void move(double time) {
        if(!hasPlayedSound){
        playGunshotSound();
        hasPlayedSound = true;
        }
        super.move(time);
        this.getChildren().get(0).setRotate(velocity().angle());
    }

    @Override
    public String toString() {
        return "bullet : "+getCoordinate()+velocity().magnitude();
    }

}
