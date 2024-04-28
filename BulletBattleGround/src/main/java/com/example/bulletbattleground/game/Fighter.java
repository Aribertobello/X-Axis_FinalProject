package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.ClassSelectorController;
import com.example.bulletbattleground.game.Loadout;
import com.example.bulletbattleground.game.levels.StandardLevel;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.gameObjects.projectiles.Rocket;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Fighter extends Rectangle {

    @Getter
    @Setter
    protected Loadout loadout;

    @Getter
    @Setter
    protected int maxHealth;

    @Getter
    @Setter
    protected int health;

    @Getter
    protected Coordinate coordinate = new Coordinate(0, 0);

    @Getter
    @Setter
    protected int teamNb;
    @Getter
    boolean highlighted;
    @Getter @Setter
    private Mapp map;


    /**
     * Creates a Fighter  instance, this is what can shoot bullets in the game
     * @param coordinateX coordinate x of the center of the fighter in the map
     * @param coordinateY coordinate y of the center of the fighter in the map
     * @param type type of loadout the fighter will have : 1=light 2=medium 3=heavy
     */
    public Fighter( int type,int health, int coordinateX, int coordinateY) {
        super(40, 40);
        loadout = new Loadout(type);
        coordinate.setX(coordinateX);
        coordinate.setY(coordinateY);
        this.teamNb = 1;
        this.setLayoutX(coordinateX - 20);
        this.setLayoutY(coordinateY - 20);
        highlighted = false;
        setHealth(health);
        setMaxHealth(health);
        this.setOnMouseClicked(event -> highlight());
        hitBox();
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
        this.setLayoutX(coordinate.getX()-20);
        this.setLayoutY(coordinate.getY()-20);
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
     */
    public void launchProjectile(Projectile projectile, Vector velocity) {
        Coordinate launchCoordinate;
        if(teamNb!=1 || this instanceof Computer){
            launchCoordinate = this.coordinate.move(new Vector(-20,-20));
        } else {
            launchCoordinate = this.coordinate.move(new Vector(20,-20));
        }
        projectile.release(velocity, new Coordinate(launchCoordinate.getX(), launchCoordinate.getY()));
        if (map.getActiveProjectile() == null) {
            map.getChildren().add(projectile);
            map.setActiveProjectile(projectile);
        } else {
            map.getChildren().remove(((Mapp) this.getParent()).getActiveProjectile());
            map.getChildren().add(projectile);
            map.setActiveProjectile(projectile);
        }
        map.setBuffer(0);
        if (projectile instanceof Rocket) {
            Vector a = new Vector(0,4.9);
            double angle = projectile.velocity().angle();
            Vector v = projectile.velocity();
            double vx = v.getX();
            double ax = a.getX();
            double ay = a.getY();
            double tan = Math.tan(Math.PI*(angle/180));
            double dropZone = this.getCoordinate().getX()+20+(2*vx*vx*tan*(ax*tan-ay)/(ay*ay));
            ((Rocket)projectile).setDropZone(dropZone);
            if (((Rocket) projectile).getDropZone() >= 1915) {
                ((Rocket) projectile).setDropZone(1915);
            }
            if (((Rocket) projectile).getDropZone() <= 5) {
                ((Rocket) projectile).setDropZone(5);
            }
        }
    }

    public void highlight(){

        Level level = BattleGround.activeGame.getLevel();
        if(level.getSelectedFighter()!=null) level.getSelectedFighter().unhiglight();
        level.setSelectedFighter(this);
        level.setOrigin(teamNb == 1 ? coordinate.move(new Vector(20,-20)) :  coordinate.move(new Vector(-20,-20)));
        highlighted = true;
        this.setStroke(teamNb == 1 ? Color.CYAN :  Color.DARKRED );
    }

    public void unhiglight(){
        this.setStroke(Color.TRANSPARENT );
        highlighted = false;
    }
}
