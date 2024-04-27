package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.GameSceneController;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.projectiles.Bullet;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class Level extends AnchorPane {

    @FXML
    @Getter
    @Setter
    private Pane headsUpDisplay;

    @FXML
    private AnchorPane container;

    @FXML
    private Label activeProjectileLabel;


    @FXML
    private Label KELabel;

    @FXML
    private Label blankLabel;

    @FXML
    private ProgressBar healthProgressbar;


    @FXML
    private Label healthLabel;

    @FXML
    private MenuBar topMenu;
    @FXML
    private Label angleLabel;


    @FXML
    private Menu newGameButton;

    @FXML
    private Menu exitButton;
    @FXML
    private void handleExit(){
        Platform.exit();
    }
    @FXML
    private Menu settingsButton;

    @FXML
    private Menu pauseButton;
    @FXML
    private void handlePause() {

    }

    @Getter
    @Setter
    protected boolean dragging = false;

    public Mapp map;
    protected Line trajectoryLine = new Line();//TODO

    protected Coordinate origin;

    protected Ally selectedFighter;

    protected String type;

    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();

    /**
     *
     * @param dt
     */
    protected void update(double dt) {
        map.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        map.update(dt);
            updateHUD();

    }
    public Level(){


    }
    /**
     *
     * @param map
     * @param type
     */
    public Level(Mapp map, String type) throws IOException {
        LinkElements();
        this.type = type;
        if (this.type.equalsIgnoreCase("pve")) {
            map.loot = new Loot(screenWidth - 341, 410);
            map.getChildren().add(map.loot);
        }
        if (this.type.equalsIgnoreCase("pvp")) {
            //TODO
        }
        this.map = map;
        container.getChildren().add(this.map);
        map.toBack();
        this.getChildren().add(trajectoryLine);// TODO arrow
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

    @FXML
    private void handleExit(){
        Platform.exit();
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
        angleLabel = controller.getAngleLabel();
        KELabel = controller.getKELabel();
        healthLabel = controller.getHealthLabel();
        healthProgressbar = controller.getHealthProgressbar();
        activeProjectileLabel = controller.getActiveProjectileLabel();
    }

    /**
     *
     * @param selectedFighter
     */
    protected void displayLoadout(Fighter selectedFighter) {
        //TODO
    }

    public void updateHUD(){
        if(trajectoryLine != null && angleLabel != null) {
            Vector direction = new Vector(trajectoryLine.getEndX() - trajectoryLine.getStartX(), trajectoryLine.getEndY() - trajectoryLine.getStartY());
            double angle = 180 - direction.angle();
            angleLabel.setText("Angle: " + angle);
            System.out.println(angleLabel);
        }
        if(map != null && map.activeProjectile != null){
            KELabel.setText("Kinetic energy: "+ map.getActiveProjectile().kE());
            System.out.println(KELabel);
        }
        if(healthProgressbar != null){
            healthProgressbar.setProgress(20);
            healthProgressbar.setStyle("-fx-accent: red; -fx-progress-bar-indeterminate-fill: red;");
        }
        if(map.activeProjectile != null){
            activeProjectileLabel.setText("Projectile Coordinates: "+ map.getActiveProjectile().getCoordinate());
            VeloLabel.setText("Velocity X and Y: "+ map.getActiveProjectile().velocity());
            Vector velocity = map.getActiveProjectile().velocity();
            double MaxVelocity = 200000;
            double progress = velocity.magnitude() / MaxVelocity;
            double red = 255 * (1-progress);
            String barStyle = "-fx-accent: rgb(255," + (int)red + ", " + (int)red + ");";
            VeloBar.setStyle(barStyle);
            VeloBar.setProgress(progress);
            AccLabel.setText("Acceleration: "+ map.getActiveProjectile().acceleration());
            MomLabel.setText("Momentum: " + Math.round(map.getActiveProjectile().momentum()));
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
        playBtn = controller.playBtn;
        descriptionLabel = controller.descriptionLabel;
        descriptionLabel.setText(description);
    }

    public void displayDescription() {
        container.setOpacity(0.25);
        this.getChildren().add(descriptionBox);
    }

    public class Arrow extends Polyline {
        Coordinate ultimateCoord = null;
        Coordinate penUltimateCoord = null;
        public Arrow(){
            super();
            this.setStroke(Color.WHITE);
        }
        public void updateDrag(Fighter fighter, double dt, Coordinate coordinate, Vector direction){

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

            for(double T = 0 ; T < 10; T += 2*dt){
                    if(fighter.loadout.type==3){
                        projectile.forces.clear();
                        projectile.forces.add(new Vector(0,4.9).multiply(projectile.getMass()));
                    }
                    projectile.move(2*dt);
                    map.addForces(projectile);
                this.getPoints().addAll(projectile.getCoordinate().getX(),projectile.getCoordinate().getY());
                penUltimateCoord = ultimateCoord;
                ultimateCoord = projectile.getCoordinate();

            }
            map.getChildren().add(this);
            addTip();
        }
        public void addTip(){
            if(!this.getPoints().isEmpty()){
                Vector slopeVector = ultimateCoord.distanceVector(penUltimateCoord).scale(5);
                getPoints().addAll(ultimateCoord.move(slopeVector.rotate(90)).getX(), ultimateCoord.move(slopeVector.rotate(90)).getY());
                getPoints().addAll(ultimateCoord.move(slopeVector.rotate(-90)).getX(), ultimateCoord.move(slopeVector.rotate(-90)).getY());
                getPoints().addAll(ultimateCoord.move(slopeVector.multiply(2)).getX(), ultimateCoord.move(slopeVector.multiply(2)).getY());
                getPoints().addAll(ultimateCoord.move(slopeVector.rotate(90)).getX(), ultimateCoord.move(slopeVector.rotate(90)).getY());
            }
        }
    }
}
