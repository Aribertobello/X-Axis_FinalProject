package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.DescriptionBoxController;
import com.example.bulletbattleground.controllers.EducationGameController;
import com.example.bulletbattleground.controllers.GameSceneController;
import com.example.bulletbattleground.controllers.TurnVariablesController;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.gameObjects.projectiles.SmokeGrenade;
import com.example.bulletbattleground.gameObjects.projectiles.Rocket;
import com.example.bulletbattleground.gameObjects.projectiles.Spear;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.GameUI;
import com.example.bulletbattleground.utility.Vector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Level extends AnchorPane implements GameUI {

    public Arrow arrow;
    //Level properties-----------------
    protected boolean dragging = false;
    @Getter
    @Setter
    public Mapp map;
    protected Line trajectoryLine = new Line();//TODO
    @Getter
    @Setter
    protected Coordinate origin;
    @Getter
    @Setter
    protected Fighter selectedFighter;
    @Getter
    @Setter
    protected int type;
    @Getter
    @Setter
    protected int index;
    @Getter
    @Setter
    String description = "";
    public ArrayList<Fighter> team1 = new ArrayList<>();
    public ArrayList<Fighter> team2 = new ArrayList<>();
    //------------------------------------------------------------------------

    // Level controllers--------
    @FXML
    @Getter
    @Setter
    protected Pane headsUpDisplay;
    @FXML
    @Getter
    protected AnchorPane container;
    private Label activeProjectileLabel;
    private Label GrenadeLabel;
    private Label SmokeLabel;
    private Label KELabel;
    private ProgressBar healthProgressbar;
    private Label VeloLabel;
    private Label AccLabel;
    private Label healthLabel;
    private Label angleLabel;
    private Label MomLabel;
    private Label turnStatusLabel;
    private Label timeLeftLabel;
    private ProgressBar timerBar;
    public Pane turnStatusBox;
    private ProgressBar VeloBar;
    private Line AngleDisp;
    @Getter
    @Setter
    private double LastAngel = 0.0;
    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    private Label BltAmount;
    private Label GTimer;
    private Label STimer;
    public ImageView GImg;
    public ImageView BImg;
    private Arc angleArc;
    @Getter
    private Label descriptionLabel;
    private VBox descriptionBox;

    /**
     *
     * @param dt
     */

    //constructor---------------------------------
    /**
     *
     * @param map
     */
    public Level(Mapp map) throws IOException {
        this.map = map;
        createHUD();
        container.getChildren().add(this.map);
        map.toBack();
        this.headsUpDisplay.setPrefWidth(BattleGround.screenWidth);
        this.getChildren().add(trajectoryLine);
        arrow = new Arrow();
        descriptionBox();
        map.setPrefWidth(BattleGround.screenWidth);
    }

    //------------------------------------------------

    public boolean[] update(double dt,double time) {
        updateHUD();
        if(map.update(dt)){
            return levelStatus(map);
        }
        return new boolean[]{false,false};
    }

    public boolean[] levelStatus(Mapp map) {
        return new boolean[]{false,false};
    }

    public void createHUD() throws IOException {
        FXMLLoader hudLoader = new FXMLLoader(BattleGround.class.getResource("GameScene.fxml"));
        FXMLLoader turnLoader = new FXMLLoader(BattleGround.class.getResource("TurnVariables.fxml"));
        this.getChildren().add(hudLoader.load());
        turnLoader.load();
        GameSceneController controller = hudLoader.getController();
        TurnVariablesController controller1 = turnLoader.getController();
        container = controller.getContainer();
        headsUpDisplay = controller.getHeadsUpDisplay();
        headsUpDisplay.setLayoutY(BattleGround.screenHeight-210);
        angleLabel = controller.getAngleLabel();
        KELabel = controller.getKELabel();
        healthLabel = controller.getHealthLabel();
        healthProgressbar = controller.getHealthProgressbar();
        activeProjectileLabel = controller.getActiveProjectileLabel();
        turnStatusLabel = controller1.getTurnStatusLabel();
        timeLeftLabel = controller1.getTimeLeftLabel();
        timerBar = controller1.getTimerBar();
        turnStatusBox = controller1.getTurnStatusBox();
        turnStatusBox.setLayoutX(0);
        turnStatusBox.setLayoutY(40);
        container.getChildren().add(turnStatusBox);
        GrenadeLabel = controller.getGrenadeLabel();
        VeloLabel = controller.getVeloLabel();
        AccLabel = controller.getAccLabel();
        MomLabel = controller.getMomLabel();
        AngleDisp = controller.getAngleDisp();
        VeloBar = controller.getVeloBar();
        BltAmount = controller.getBltAmount();
        GTimer = controller.getGTimer();
        GImg = controller.getGImg();
        BImg = controller.getBImg();
        SmokeLabel = controller.getSmokeLabel();
        STimer = controller.getSTimer();
        angleArc = new Arc(1065,943,20,20,0,0);
        angleArc.setStroke(Color.RED);
        angleArc.setFill(Color.TRANSPARENT);
        angleArc.setStrokeWidth(1.5);
        container.getChildren().add(angleArc);
        angleArc.toFront();
    }

    public void resetTrajectoryLine() {

        trajectoryLine.setStartX(0);
        trajectoryLine.setStartY(0);
        trajectoryLine.setEndX(0);
        trajectoryLine.setEndY(0);
        dragging = false;
    }

    public void addFighter(Fighter fighter, int teamNb){
        map.addFighter(fighter);
    }

    public void displaceTrajectoryLine(double startX, double startY, double endX, double endY) {
        trajectoryLine.setStartX(startX);
        trajectoryLine.setStartY(startY);
        trajectoryLine.setEndX(endX);
        trajectoryLine.setEndY(endY);
    }

    public void updateHUD(){
        setKELabelHUD();
        setGrenadeLabelHUD();
        setSmokeGrenadeLabelHUD();
        setHealthHUD();
        setVelocityHUD();
        setLoadoutHUD();
        if(trajectoryLine != null && angleLabel != null && selectedFighter != null) {
            Vector direction = new Vector(trajectoryLine.getEndX() - trajectoryLine.getStartX(), trajectoryLine.getEndY() - trajectoryLine.getStartY());
            setAngleHUD(direction);
        }
        if(map.activeProjectile != null){
            activeProjectileLabel.setText("Projectile Coordinates: "+ map.getActiveProjectile().getCoordinate());
            AccLabel.setText("Acceleration: "+ map.getActiveProjectile().acceleration());
            MomLabel.setText("Momentum: " + Math.round(map.getActiveProjectile().momentum()));
        }
    }

    private void setLoadoutHUD() {
        if(trajectoryLine != null && angleLabel != null && selectedFighter != null) {
            if (selectedFighter.loadout.type == 1) {
                BltAmount.setText("Number of Bullets left: ∞");
            } else if (selectedFighter.loadout.type == 2) {
                BltAmount.setText("Number of Spears left: ∞");
            } else if (selectedFighter.loadout.type == 3) {
                BltAmount.setText("Number of Rockets left: ∞");
            }
        }

    }

    private void setVelocityHUD() {
        if(map.activeProjectile != null) {
            VeloLabel.setText("Velocity X and Y: " + map.getActiveProjectile().velocity());
            Vector velocity = map.getActiveProjectile().velocity();
            double MaxVelocity = map.getActiveProjectile().getTerminalVelocity();
            double progress = velocity.magnitude() / MaxVelocity;
            double red = 255 * (1 - progress);
            String barStyle = "-fx-accent: rgb(255," + (int) red + ", " + (int) red + ");";
            VeloBar.setStyle(barStyle);
            VeloBar.setProgress(progress);
        }
    }

    private void setSmokeGrenadeLabelHUD() {
        if (map.activeProjectile instanceof SmokeGrenade) {
            SmokeLabel.setText("Number of Smoke left: " + selectedFighter.loadout.smokeGrenades.size());
            STimer.setText("Smoke Screen Timer: " + ((SmokeGrenade) map.activeProjectile).getFuseTimer());
        }
    }

    private void setGrenadeLabelHUD() {
        if( map.activeProjectile instanceof Grenade){
            GTimer.setText("Grenade Timer: " + Math.round(((Grenade)map.activeProjectile).getFuseTimer()) + " Seconds");
            GrenadeLabel.setText("Number of Grenades left: " + selectedFighter.loadout.grenades.size());
        } else {
            GTimer.setText("Grenade Timer: No Grenades shot!");
        }
    }



    private void setHealthHUD() {
        if(selectedFighter!=null) {
            healthProgressbar.setProgress((double) selectedFighter.getHealth() / (double) selectedFighter.getMaxHealth());
            healthProgressbar.setStyle("-fx-accent: red; -fx-progress-bar-indeterminate-fill: red;");
            healthLabel.setText("Player Health: " + selectedFighter.getHealth());
        }
    }

    private void setKELabelHUD() {
        if(map != null && map.activeProjectile != null){
            KELabel.setText("Kinetic energy: "+ Math.round(map.getActiveProjectile().kE()));
        }
    }

    public void setAngleHUD( Vector direction){
        double angle = 180 - direction.angle();
        if(!Double.isNaN(angle)){
            AngleDisp.setRotate(-angle);
            AngleDisp.setStroke(Color.WHITE);
            angleArc.setLength(angle);
            angleLabel.setText("Angle: " + Math.round(angle));
        }
    }


    public void updateTurnBox(double time, double timeLimit, int playerTurn) {
        switch(playerTurn){
            case 1  -> {timerBar.setStyle("-fx-accent: cyan;");
                turnStatusLabel.setText("It is Player 1's Turn");}
            case 2 -> {timerBar.setStyle("-fx-accent: red;");
                turnStatusLabel.setText("It is Player 2's Turn");}
            default -> {timerBar.setStyle("-fx-accent: gray;");
                turnStatusLabel.setText("Projectile Moving");}
        }
        timeLeftLabel.setText(String.valueOf(Math.round(10*(timeLimit-time))/10.0));
        timerBar.setProgress(1-(time/timeLimit));
    }

    public void removeFighter(Fighter fighter) {
        selectedFighter.unhiglight();
        selectedFighter = null;
        map.removeFighter(fighter);
        setOrigin(null);
    }

    public void descriptionBox() throws IOException {
        FXMLLoader descriptionBoxLoader = new FXMLLoader(BattleGround.class.getResource("DescriptionBox.fxml"));
        descriptionBox = descriptionBoxLoader.load();
        DescriptionBoxController controller = descriptionBoxLoader.getController();
        descriptionLabel = controller.descriptionLabel;
        descriptionLabel.setText(description);
    }

    public void displayDescription() {
        container.setOpacity(0.25);
        this.getChildren().add(descriptionBox);
    }

    public class Arrow extends Polyline {
        public Arrow(){
            super();
            this.setStroke(Color.WHITE);
        }
        public void update(Fighter fighter, double dt, Coordinate coordinate, Vector direction){

            Projectile projectile;
            this.getPoints().clear();
            this.getPoints().addAll(coordinate.getX(),coordinate.getY());
            map.getChildren().remove(this);
            switch(fighter.loadout.type){
                case 1 -> projectile = new Bullet();
                case 2 -> projectile = new Spear();
                default -> projectile = new Bullet();
            }
            projectile.setVelocity(direction);
            projectile.setCoordinate(coordinate);

            for(double t = 0 ; t < 0.5; t += dt){
                if(fighter.loadout.type==3){
                    projectile.forces.clear();
                    projectile.forces.add(new Vector(0,4.9).multiply(projectile.getMass()));
                }
                projectile.move(dt);
                this.getPoints().addAll(projectile.getCoordinate().getX(),projectile.getCoordinate().getY());
                map.addForces(projectile);
            }
            map.getChildren().add(this);
        }
    }
}

