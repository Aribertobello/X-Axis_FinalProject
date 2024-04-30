package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Getter @Setter
public class Grenade extends Projectile {

    private final Image explosionEffect1 = new Image("file:Files/img/1.PNG");

    private final Image explosionEffect2 = new Image("file:Files/img/2.PNG");

    private final Image explosionEffect3 = new Image("file:Files/img/3.PNG");

    private final Image explosionEffect4 = new Image("file:Files/img/4.PNG");

    private final Image explosionEffect5 = new Image("file:Files/img/5.PNG");

    private final Image explosionEffect6 = new Image("file:Files/img/6.PNG");

    private final Image explosionEffect7 = new Image("file:Files/img/7.PNG");

    private final Image explosionEffect8 = new Image("file:Files/img/8.PNG");

    private final double ExplosionRadius = 90;

    private final int impactCollisionDamage = 1;

    private final int explosionDamage = 5;

    @Getter
    private double fuseTimer;

    private int index = 0;

    protected Circle grenade = new Circle(10, Color.PALEGREEN);

    protected Circle explosionCircle = new Circle(ExplosionRadius, Color.PALEGREEN);

    protected final Image[] explosionEffects = {explosionEffect2, explosionEffect3, explosionEffect4, explosionEffect5, explosionEffect6, explosionEffect7, explosionEffect8};

    protected Timeline animationTimeLine;


    public Grenade(){
        fuseTimer = 15.0;
        Image grenadeImg = new Image("file:Files/img/grenade.png");
        grenade.setFill(new ImagePattern(grenadeImg));
        this.lift = new Vector(0, 0);
        this.getChildren().add(grenade);
        this.forces.add(lift);
        setMass(0.5);
        setTerminalVelocity(95);
        this.setDamage(impactCollisionDamage); //at least it does a little damage only if the grenades hits the player
    }
    @Override
    public void bounce(HitBox hitBox) {
        //TODO
    }

    @Override
    public void move(double time) {
        super.move(time);
            fuseTimer -= time;
        explode();
    }

    public void explode() {

        if (fuseTimer <= 0.0 ) {
            fuseTimer = Math.sqrt (-1); //Makes sure that the explode method is only called once, or else it gets called every 1ms
            playExplosionSound();
            explosionCircle.setLayoutX(getChildren().get(0).getLayoutX());
            explosionCircle.setLayoutY(getChildren().get(0).getLayoutY());
            this.getChildren().remove(0);
            this.getChildren().add(explosionCircle);
            this.setDamage(explosionDamage);

            explosionCircle.setFill(new ImagePattern(explosionEffect1)); //Make sure that the explosion circle shows up as the 1st image of explosion or else it would be a green Circle showing up instead

            animationTimeLine = new Timeline(new KeyFrame(Duration.seconds(0.06), event -> explosionAnimation()));
            animationTimeLine.setCycleCount(Timeline.INDEFINITE);
            animationTimeLine.play();
        }
    }
    private void playExplosionSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Explosion.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-20.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void explosionAnimation() {
        explosionCircle.setFill(new ImagePattern(explosionEffects[index]));
        if (index < 6) {
            index++;
        } else {
            animationTimeLine.stop();
            getChildren().remove(explosionCircle);
            BattleGround.activeGame.getLevel().map.removeActiveProjectile();
        }

    }

    //TODO Choose if we keep this or not: it's just a method to detect collision for debugging purposes
   /* private void checkPlayerDetection() {
            Bounds fighterBounds = this.getBoundsInParent();
            if (explosionCircle.intersects(fighterBounds)) {
                System.out.println("DETECTED: "  + this.getDamage());
        }
    }*/

    @Override
    public String toString() {
        return "Grenade";
    }
}
