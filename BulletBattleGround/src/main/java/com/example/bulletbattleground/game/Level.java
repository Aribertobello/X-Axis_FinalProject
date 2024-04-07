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




    protected void update(double dt) {
        map.setPrefWidth(((Stage) this.getScene().getWindow()).getWidth());
        map.update(dt);
            updateHUD();

    }
    public Level(){


    }
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
    }


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


    }


}
