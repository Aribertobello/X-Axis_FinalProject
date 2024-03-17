package com.example.bulletbattleground.gameObjects.projectiles;

import com.example.bulletbattleground.game.Projectile;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.HitBox;
import com.example.bulletbattleground.utility.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

public class Rocket extends Projectile {
    @Getter
    @Setter
    int nausea;
    @Getter
    @Setter
    double dropZone;
    public Rocket(){
        this.nausea = 3;
        this.getChildren().add(new Circle(2, Color.DARKGRAY));
        this.getChildren().add(new Polygon());
        this.damage = 3;
        this.lift = new Vector(0,-2.0);
        this.forces.add(lift);
        this.mass = 0.5;
    }
    @Override
    public HitBox hitBox(){
        return new HitBox(this);
    }
    @Override
    public void move(double time) {
        if(coordinate.getY()<100){
            setCoordinate(new Coordinate(dropZone,101));
            setVelocity(new Vector(0,50));
            forces.clear();
        }else if(coordinate.getX()!=dropZone){
            setVelocity(new Vector(0, -50));
            forces.clear();
        }
        this.coordinate.setX( (acceleration().getX()/2)*time*time + velocityX*time + coordinate.getX() );
        this.coordinate.setY( (acceleration().getY()/2)*time*time + velocityY*time + coordinate.getY() );
        this.setVelocityX( acceleration().getX()*time + velocityX );
        this.setVelocityY( acceleration().getY()*time + velocityY );
        allign();

        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
    }
    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.getChildren().get(0).setLayoutX(coordinate.getX());
        this.getChildren().get(0).setLayoutY(coordinate.getY());
        this.coordinate = coordinate;
    }
    public void allign() {
        if(velocity().magnitude()!=0) {
            double x0= getChildren().get(0).getLayoutX()+velocity().scale(3).getX(), y0 = getChildren().get(0).getLayoutY()+velocity().scale(3).getY();
            double x1 = x0+velocity().scale(3).getX(), y1 = y0+velocity().scale(3).getY();;
            double x2 = x0+velocity().scale(3).rotate(270).getX(), y2 = y0+velocity().scale(3).rotate(270).getY();
            double x3 = x2-velocity().scale(25).getX(), y3 = y2-velocity().scale(25).getY();
            double x4 = x3+velocity().scale(6).rotate(90).getX(), y4 = y3+velocity().scale(6).rotate(90).getY();
            double x5 = x4+velocity().scale(25).getX(), y5 = y4+velocity().scale(25).getY();
            ((Polygon) this.getChildren().get(1)).getPoints().setAll(x1,y1,x2,y2,x3,y3,x4,y4,x5,y5);
        }
    }
}
