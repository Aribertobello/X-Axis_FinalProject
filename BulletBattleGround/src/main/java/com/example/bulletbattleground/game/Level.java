package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.EducationGameController;
import com.example.bulletbattleground.controllers.GameSceneController;
import com.example.bulletbattleground.controllers.TurnVariablesController;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.GameUI;
import com.example.bulletbattleground.utility.Vector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Level extends AnchorPane implements GameUI {

    //Level properties-----------------
    protected boolean dragging = false;
    public Mapp map;
    protected Line trajectoryLine = new Line();//TODO
    @Getter
    @Setter
    protected Coordinate origin;
    @Getter
    @Setter
    protected Fighter selectedFighter;
    //------------------------------------------------------------------------

    // Level controllers--------
    @FXML
    @Getter
    @Setter
    protected Pane headsUpDisplay;
    @FXML
    protected AnchorPane container;
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
    private Label turnStatusLabel;
    private Label timeLeftLabel;
    private ProgressBar timerBar;
    public Pane turnStatusBox;
    //-----------------------


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
        this.getChildren().add(trajectoryLine);// TODO arrow
    }
    //------------------------------------------------

    public boolean[] update(double dt,double time) {

        updateHUD();
        return new boolean[]{map.update(dt)};
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
        turnStatusLabel = controller1.getTurnStatusLabel();
        timeLeftLabel = controller1.getTimeLeftLabel();
        timerBar = controller1.getTimerBar();
        turnStatusBox = controller1.getTurnStatusBox();
        turnStatusBox.setLayoutX(0);
        turnStatusBox.setLayoutY(40);
        container.getChildren().add(turnStatusBox);
        GrenadeLabel = controller.getGrenadeLabel();
    }

    /**
     *
     * @param selectedFighter
     */
    protected void displayLoadout(Fighter selectedFighter) {
        //TODO
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

    public void updateHUD () {

        if (trajectoryLine != null && angleLabel != null) {

            Vector direction = new Vector(trajectoryLine.getEndX() - trajectoryLine.getStartX(), trajectoryLine.getEndY() - trajectoryLine.getStartY());
            double angle = 180 - direction.angle();
            angleLabel.setText("Angle: " + angle);
        }
        if(map != null && map.activeProjectile != null){
            KELabel.setText("Kinetic energy: "+ Math.round(map.getActiveProjectile().kE()));
        }
        if(healthProgressbar != null && selectedFighter != null) {
            GrenadeLabel.setText("Number of Grenades left: " + selectedFighter.loadout.grenades.size());
            healthLabel.setText("Player Health: " + selectedFighter.getHealth());
            healthProgressbar.setProgress(selectedFighter.getHealth());
            healthProgressbar.setStyle("-fx-accent: red; -fx-progress-bar-indeterminate-fill: red;");
        }
        if(map.activeProjectile != null){
            activeProjectileLabel.setText("Projectile: "+ map.getActiveProjectile().getCoordinate());
        }
        if (map != null && map.activeProjectile != null) {

            KELabel.setText("Kinetic energy: " + map.getActiveProjectile().kE());
            System.out.println(KELabel);
        }
        if (healthProgressbar != null) {

            healthProgressbar.setProgress(20);
            healthProgressbar.setStyle("-fx-accent: red; -fx-progress-bar-indeterminate-fill: red;");
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
        map.removeFighter(fighter);
        setOrigin(null);
    }
}
