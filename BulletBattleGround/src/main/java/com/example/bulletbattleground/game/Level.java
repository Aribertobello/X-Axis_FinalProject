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

    //Level properties--------------------------------
    protected boolean dragging = false;
    public Mapp map;
    protected Line trajectoryLine = new Line();//TODO
    protected Coordinate origin;
    protected Ally selectedFighter;
    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    //------------------------------------------------------------------------

    // Level controllers--------
    private Pane headsUpDisplay;
    protected AnchorPane container;
    private Label activeProjectileLabel;
    private Label KELabel;
    private Label blankLabel;
    private ProgressBar healthProgressbar;
    private Label healthLabel;
    private MenuBar topMenu;
    private Label angleLabel;
    private Menu newGameButton;
    private Menu exitButton;
    private Menu settingsButton;
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
        this.headsUpDisplay.setPrefWidth(screenWidth);
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
        turnStatusBox.setLayoutX(screenWidth-140);
        container.getChildren().add(turnStatusBox);
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
            System.out.println(angleLabel);
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

    public void updateTurnBox(double timeLeft, double timeLimit, int playerTurn) {
        switch(playerTurn){
                case 1  -> timerBar.setStyle("-fx-accent: cyan;");
                case 2 -> timerBar.setStyle("-fx-accent: red;");
                default -> timerBar.setStyle("-fx-accent: gray;");
        }
        timerBar.setProgress(1-(timeLeft/timeLimit));
    }
}