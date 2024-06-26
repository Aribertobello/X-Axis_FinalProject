package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.DescriptionBoxController;
import com.example.bulletbattleground.controllers.GameSceneController;
import com.example.bulletbattleground.controllers.TurnVariablesController;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.gameObjects.projectiles.Grenade;
import com.example.bulletbattleground.gameObjects.projectiles.SmokeGrenade;
import com.example.bulletbattleground.utility.Arrow;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.GameUI;
import com.example.bulletbattleground.utility.MovingBody;
import com.example.bulletbattleground.utility.Vector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Level extends AnchorPane implements GameUI {

    public Arrow arrow;
    //Level properties-----------------
    @Getter @Setter public Mapp map;
    @Getter @Setter protected Coordinate origin;
    @Getter @Setter protected Fighter selectedFighter;
    @Getter @Setter protected int type;
    @Getter @Setter protected int index;
    @Getter @Setter String description = "";
    @Getter @Setter private double LastAngel = 0.0;
    protected boolean dragging = false;
    protected Line trajectoryLine = new Line();
    private Line AngleDisp;
    public ArrayList<Fighter> team1 = new ArrayList<>();
    public ArrayList<Fighter> team2 = new ArrayList<>();
    //------------------------------------------------------------------------

    // Level controllers--------
    @FXML @Getter @Setter protected Pane headsUpDisplay;
    @FXML @Getter protected AnchorPane container;
    @Getter private Label descriptionLabel;
    private Label activeProjectileLabel;
    private Label GrenadeLabel;
    private Label SmokeLabel;
    private Label KELabel;
    private Label VeloLabel;
    private Label AccLabel;
    private Label healthLabel;
    private Label angleLabel;
    private Label MomLabel;
    private ProgressBar healthProgressbar;
    private Label turnStatusLabel;
    private Label timeLeftLabel;
    private ProgressBar timerBar;
    public Pane turnStatusBox;
    private ProgressBar VeloBar;
    private Label EnemyCoor;
    private Label AllyCoor;
    private Label BltAmount;
    private Label GTimer;
    private Label STimer;
    public ImageView GImg;
    public ImageView BImg;
    private Arc angleArc;
    public int centerX = 1065;
    public int centerY = 943;
    public int radiusX = 20;
    public int radiusY = 20;
    public int startAngle = 0;
    public int length = 0;
    public double lineWidth = 1.5;
    public int straightAngle = 180;
    public double descriptionOpacity = 0.25;
  //  @Getter
 //private Label descriptionLabel;
    private VBox descriptionBox;

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
        if(map.update(dt,time)[0]){
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
        angleArc = new Arc(centerX,centerY,radiusX,radiusY,startAngle,length);
        angleArc.setStroke(Color.RED);
        angleArc.setFill(Color.TRANSPARENT);
        angleArc.setStrokeWidth(lineWidth);
        container.getChildren().add(angleArc);
        angleArc.toFront();
        AllyCoor = controller.getAllyCoor();
        EnemyCoor = controller.getEnemyCoor();
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
        fighter.setTeamNb(teamNb);
        if (teamNb==1) team1.add(fighter);
        else team2.add(fighter);
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
        setFightersCoordinates();
        setLoadoutImageHUD();
        setInfoHud();
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
    private void setInfoHud(){
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
    private void setFightersCoordinates(){
        if (selectedFighter != null) {
            if (selectedFighter.teamNb == 1 && AllyCoor != null) {
                AllyCoor.setText("Ally Coordinates: " + selectedFighter.getCoordinate().getX() + " " + selectedFighter.getCoordinate().getY());
            } else if (selectedFighter.teamNb == 2 && EnemyCoor != null) {
                EnemyCoor.setText("Enemy Coordinates: " + selectedFighter.getCoordinate().getX() + " " + selectedFighter.getCoordinate().getY());
            }
        }
    }
    private void setLoadoutImageHUD(){
        if(selectedFighter != null) {
            if (selectedFighter.loadout.type == 1) {
                BImg.setImage(new Image("file:Files/img/smallBullet.png"));
         }
        }
    }

    private void setVelocityHUD() {
        if(map.activeProjectile != null) {
            VeloLabel.setText("Velocity X and Y: " + map.getActiveProjectile().velocity());
            Vector velocity = map.getActiveProjectile().velocity();
            double MaxVelocity = map.getActiveProjectile().getTerminalVelocity();
            double progress = velocity.magnitude() / MaxVelocity;
            double red = MaxVelocity * (1 - progress);
            String barStyle = "-fx-accent: rgb(255," + (int) red + ", " + (int) red + ");";
            VeloBar.setStyle(barStyle);
            VeloBar.setProgress(progress);
        }
    }

    private void setSmokeGrenadeLabelHUD() {
        if(selectedFighter != null){
            SmokeLabel.setText("Number of Smoke left: " + selectedFighter.loadout.smokeGrenades.size());
        }
        if (map.activeProjectile instanceof SmokeGrenade) {

            STimer.setText("Smoke Screen Timer: " + Math.round(((SmokeGrenade) map.activeProjectile).getFuseTimer()) + " Seconds");
        }
    }

    private void setGrenadeLabelHUD() {
        if(selectedFighter != null) {
            GrenadeLabel.setText("Number of Grenades left: " + selectedFighter.loadout.grenades.size());
        }
        if( map.activeProjectile instanceof Grenade){
            GTimer.setText("Grenade Timer: " + Math.round(((Grenade)map.activeProjectile).getFuseTimer()) + " Seconds");
        } else {
            GTimer.setText("Grenade Timer: No Grenades shot!");
        }
    }


    private void setHealthHUD() {
        if (selectedFighter != null) {
            if (selectedFighter.teamNb == 1) {
                healthLabel.setText("Player Health: " + selectedFighter.getHealth());
                healthProgressbar.setProgress((double) selectedFighter.getHealth() / (double) selectedFighter.getMaxHealth());
                healthProgressbar.setStyle("-fx-accent: red; -fx-progress-bar-indeterminate-fill: red;");
            } else if (selectedFighter.teamNb == 2) {
                healthLabel.setText("AI Health: " + selectedFighter.getHealth());
                healthProgressbar.setProgress((double) selectedFighter.getHealth() / (double) selectedFighter.getMaxHealth());
                healthProgressbar.setStyle("-fx-accent: green; -fx-progress-bar-indeterminate-fill: green;");
            }
        }
    }

    private void setKELabelHUD() {
        if(map != null && map.activeProjectile != null){
            KELabel.setText("Kinetic energy: "+ Math.round(map.getActiveProjectile().kE()));
        }
    }

    public void setAngleHUD( Vector direction){
        double angle = straightAngle - direction.angle();
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
        container.setOpacity(descriptionOpacity);
        this.getChildren().add(descriptionBox);
    }
}

