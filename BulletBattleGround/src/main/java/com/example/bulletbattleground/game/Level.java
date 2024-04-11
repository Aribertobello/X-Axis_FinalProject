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
    private Label GrenadeLabel;
    private Label SmokeLabel;
    @FXML
    private Label KELabel;

    @FXML
    private Label blankLabel;

    @FXML
    private ProgressBar healthProgressbar;
    @FXML
    private Label VeloLabel;
    @FXML
    private Label AccLabel;


    @FXML
    private Label healthLabel;

    @FXML
    private MenuBar topMenu;
    @FXML
    private Label angleLabel;
    @FXML
    private Label MomLabel;
    @FXML
    private Label MagLabel;
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
    private double LastAngel = 0.0;
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
    public void LinkElements() throws IOException {
        FXMLLoader loader = new FXMLLoader(BattleGround.class.getResource("GameScene.fxml"));
        this.getChildren().add(loader.load());
        GameSceneController controller = loader.getController();
        container = controller.getContainer();
        headsUpDisplay = controller.getHeadsUpDisplay();
        angleLabel = controller.getAngleLabel();
        KELabel = controller.getKELabel();
        healthLabel = controller.getHealthLabel();
        healthProgressbar = controller.getHealthProgressbar();
        activeProjectileLabel = controller.getActiveProjectileLabel();
        GrenadeLabel = controller.getGrenadeLabel();
        VeloLabel = controller.getVeloLabel();
        AccLabel = controller.getAccLabel();
        MomLabel = controller.getMomLabel();

    }

    /**
     *
     * @param selectedFighter
     */
    protected void displayLoadout(Fighter selectedFighter) {
        //TODO
    }

    public void updateHUD(){
        if(trajectoryLine != null && angleLabel != null && selectedFighter != null) {
            Vector direction = new Vector(trajectoryLine.getEndX() - trajectoryLine.getStartX(), trajectoryLine.getEndY() - trajectoryLine.getStartY());
            double angle = 180 - direction.angle();
            if(!Double.isNaN(angle)) {
                if (angle != LastAngel) {
                    angleLabel.setText("Angle: " + Math.round(angle));
                    LastAngel = angle;
                } else if(angleLabel!=null){
                    angleLabel.setText("Angle: " + Math.round(LastAngel));
                }
                }
            GrenadeLabel.setText("Number of Grenades left: " + selectedFighter.loadout.grenades.size());
        }

        if(map != null && map.activeProjectile != null){
            KELabel.setText("Kinetic energy: "+ Math.round(map.getActiveProjectile().kE()));
        }
        if(healthProgressbar != null && selectedFighter != null){
            int previousHealth = (int) (healthProgressbar.getProgress() * 15); // Assuming the progress bar is based on a scale of 0 to 1
            int currentHealth = selectedFighter.getHealth();
            if (currentHealth < previousHealth) {
                double healthChange = (previousHealth - currentHealth) / 100.0; // Convert to decimal
                double newProgress = Math.max(0, healthProgressbar.getProgress() - healthChange); // Ensure progress doesn't go below 0
                healthProgressbar.setProgress(newProgress);
            } else if (currentHealth > previousHealth) {
                double healthChange = (currentHealth - previousHealth) / 100.0; // Convert to decimal
                double newProgress = Math.min(1, healthProgressbar.getProgress() + healthChange); // Ensure progress doesn't go above 1
                healthProgressbar.setProgress(newProgress);
            }
            healthLabel.setText("Player Health: " + currentHealth);
            healthProgressbar.setStyle("-fx-accent: red; -fx-progress-bar-indeterminate-fill: red;");
        }
        if(map.activeProjectile != null){
            activeProjectileLabel.setText("Projectile Coordinates: "+ map.getActiveProjectile().getCoordinate());
            VeloLabel.setText("Velocity X and Y: "+ map.getActiveProjectile().velocity());
            AccLabel.setText("Acceleration: "+ map.getActiveProjectile().acceleration());
            MomLabel.setText("Momentum: " + Math.round(map.getActiveProjectile().momentum()));
        }


    }
}
