package com.example.bulletbattleground.gameObjects.fighters;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Fighter;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.game.levels.StandardLevel;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Rocket;
import com.example.bulletbattleground.gameObjects.projectiles.Spear;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Computer extends Fighter {

    public Computer(int type, int health, int coordinateX, int coordinateY) {
        super(type, health, coordinateX, coordinateY);
    }

    public void calculateLaunchAngle() {
        //TODO MAKE COMPUTER LAUNCH PROJECTILE ON ITS OWN

        Mapp map = (Mapp) getParent();
        Coordinate target = ((StandardLevel)BattleGround.activeGame.getLevel()).team1.get(0).getCoordinate();
        double uncertainty = 0.025;
        int indexOfthis  =  map.getPeople().indexOf(this);

        Label calcLabel = new Label();
        BattleGround.activeGame.getLevel().getChildren().add(calcLabel);
        calcLabel.setLayoutX(600);
        calcLabel.setLayoutY(600);
        for (double v = Projectile.MIN_LAUNCH_VELOCITY+90; v<Projectile.TERMINAL_VELOCITY ; v++){
            double angle = 240;
            for (; angle > 120; angle-- ){
                Mapp dummyMap = (Mapp)map.clone();
                Fighter fighter = dummyMap.getPeople().get(indexOfthis);
                fighter.launchProjectile(fighter.getLoadout().getMainWeapon(),new Vector(angle).scale(v));
                double dt = 0.01;
                for (double t = 0;t < 5 ;t+=dt){
                    dummyMap.update(dt,t);
                    if (dummyMap.activeProjectile != null) {
                        calcLabel.setText("Target: "+target+" , SimProjectile: "+ dummyMap.activeProjectile.getCoordinate());
                        System.out.println("Target: "+target+" , SimProjectile: "+ dummyMap.activeProjectile.getCoordinate());
                    }
                    if(dummyMap.activeProjectile!=null && (Math.abs(dummyMap.activeProjectile.getCoordinate().getX()-target.getX())<=20 && (Math.abs(dummyMap.activeProjectile.getCoordinate().getY()-target.getY())<=20))){
                        System.out.println("found");
                    }
                    if (dummyMap.activeProjectile!=null && dummyMap.activeProjectile.getCoordinate().equals(target)){
                        System.out.println();
                    }
                }
            }
        }
    }
}
