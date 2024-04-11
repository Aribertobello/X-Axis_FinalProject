package com.example.bulletbattleground.game;

import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.gameObjects.projectiles.Rocket;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

public class Fighter extends Rectangle {

    @Getter
    @Setter
    protected Loadout loadout;

    @Getter
    @Setter
    protected int health;

    @Getter
    protected Coordinate coordinate = new Coordinate(0, 0);

    /**
     * Creates a Fighter  instance, this is what can shoot bullets in the game
     * @param coordinateX coordinate x of the center of the fighter in the map
     * @param coordinateY coordinate y of the center of the fighter in the map
     * @param type type of loadout the fighter will have : 1=light 2=medium 3=heavy
     */
    public Fighter(int coordinateX, int coordinateY, int type) {
        super(40, 40);

        if (type == 1) {
            loadout = new Loadout("light");
        }

        if (type == 2) {
            loadout = new Loadout("medium");
        }

        if (type == 3) {
            loadout = new Loadout("heavy");
        }
        coordinate.setX(coordinateX);
        coordinate.setY(coordinateY);
        this.setLayoutX(coordinateX - 20);
        this.setLayoutY(coordinateY - 20);
        hitBox();
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
        this.setLayoutX(coordinate.getX());
        this.setLayoutY(coordinate.getY());
    }
    /**
     * updates the hitbox of the fighter
     * @return a new hitbox object based on the current location of the fighter
     */
    public HitBox hitBox() { //This is a clone method????
        return new HitBox(this);
    }

    /**
     * Launches Projectile according to parameters
     * @param projectile the projectile which will be fired
     * @param velocity the velocity vector of the launch for this projecile
     * @param coordinate the coordinates from which to begin the launch
     */
    public void launchProjectile(Projectile projectile, Vector velocity, Coordinate coordinate) {
    }
}
