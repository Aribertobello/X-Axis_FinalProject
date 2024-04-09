package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.EducationGameController;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

public class Level extends AnchorPane {

    // Level controllers
    private Pane headsUpDisplay;
    private AnchorPane container;
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

    //Edu Controllers
    public LineChart xVTChart;
    public LineChart yVTChart;
    public LineChart aVTChart;
    public LineChart rVTChart;
    public LineChart vxVTChart;
    public LineChart vyVTChart;

    // series
    private XYChart.Series rseries = new XYChart.Series();


    Rectangle playerturnSquare;
    Label activeProjectileTimer;
    Label turnTimer;
    Label activeTurn;
    Label player1Turn;


    protected boolean dragging = false;
    public Mapp map;
    protected Line trajectoryLine = new Line();//TODO
    protected ArrayList<Fighter> team1 = new ArrayList<>();
    protected ArrayList<Fighter> team2 = new ArrayList<>();
    protected Coordinate origin;
    protected Ally selectedFighter;
    protected int difficulty;
    protected int type;
    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();


    /**
     *
     * @param map
     * @param type
     */
    public Level(Mapp map, int type, int gameType) throws IOException {
        switch(gameType){
            case -1:
                educationGame();
                break;
            case 0:
                freePlayGame();
                break;
            case 1:
                regularGame();
                break;
        }
        this.type = type;
        this.map = map;
        if (this.type == 0) {
            addLoot();
        }

        //------------------TODO in fxml
        playerturnSquare = new Rectangle(50,50, Color.WHITE);
        activeProjectileTimer = new Label("");
        activeProjectileTimer.setTextFill(Color.WHITE);
        turnTimer = new Label("");
        turnTimer.setTextFill(Color.WHITE);
        activeTurn = new Label("WOW");
        activeTurn.setTextFill(Color.WHITE);
        player1Turn = new Label("");
        player1Turn.setTextFill(Color.WHITE);
        VBox turnVariables = new VBox(10,playerturnSquare,turnTimer,activeProjectileTimer,activeTurn,player1Turn);
        turnVariables.setLayoutX(screenWidth-60);
        turnVariables.setLayoutY(90);
        container.getChildren().add(turnVariables);
        //----------------------------------------------------------


        container.getChildren().add(this.map);
        map.toBack();
        this.headsUpDisplay.setPrefWidth(screenWidth);
        this.getChildren().add(trajectoryLine);// TODO arrow
    }

    protected boolean[] update(double dt,double time) {
        updateHUD();
        updateCharts(dt);
        if(map.update(dt)){
            switch(type) {
                case 0:
                    if(map.loot==null) return new boolean[]{true, true};
                    if(map.people.isEmpty()){
                        return new boolean[]{true, false};
                    }
                    break;
                case 1:
                    if(map.people.size()==1){
                        return new boolean[]{true, false};
                    }
                    //TODO gameWon or !GameWon
                    break;
                case 2:
                    if(map.people.size()==1){
                        int i = 0;//TODO PVP
                        return new boolean[]{true, false};
                    }
                    break;
            }
        }
        return new boolean[]{false,false};
    }

    public void regularGame() throws IOException {
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
    public void educationGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(BattleGround.class.getResource("EducationModeScene.fxml"));
        this.getChildren().add(loader.load());
        EducationGameController controller = loader.getController();
        container = controller.getContainer();
        headsUpDisplay = controller.getHeadsUpDisplay();
        angleLabel = controller.getAngleLabel();
        KELabel = controller.getKELabel();
        healthLabel = controller.getHealthLabel();
        healthProgressbar = controller.getHealthProgressbar();
        activeProjectileLabel = controller.getActiveProjectileLabel();
        rVTChart = controller.getRVTChart();
        //rseries.getData().add(new XYChart.Data(5, 10));
        //rseries.getData().add(new XYChart.Data(6, 10));
        //rseries.getData().add(new XYChart.Data(8, 10));
        //rseries.getData().add(new XYChart.Data(9, 10));
        rVTChart.getData().add(rseries);
        xVTChart = controller.getXVTChart();
        yVTChart = controller.getYVTChart();
        aVTChart = controller.getAVTChart();
        vxVTChart = controller.getRVTChart();
        vyVTChart = controller.getVyVTChart();
    }
    public void freePlayGame() throws IOException {
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

    public void addLoot() {
        map.loot = new Loot(screenWidth - 341, 410);
        map.getChildren().add(map.loot);
    }

    public void addFighter(Fighter fighter, int teamNb){
        map.addFighter(fighter);
        if(teamNb==1){
            team1.add(fighter);
        } else {
            team2.add(fighter);
        }
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

    public void changeTrajectoryLine(double startX, double startY, double endX, double endY) {
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
    public void updateCharts(double time){
        //rVTChart.getData().add();
        if(map.activeProjectile!=null){
            Projectile proj = map.activeProjectile;
            Vector distance = proj.getCoordinate().distanceVector(new Coordinate(map.earth.getCenterX(),map.earth.getCenterY()));
            double r = distance.magnitude();
            double x = distance.getX();
            double y = distance.getY();
            double a = proj.acceleration().magnitude();
            double vx = proj.getVelocityX();
            double vy = proj.getVelocityY();

            //rseries.getData().add(new XYChart.Data(time, r));
            //rVTChart.setData();
        }
    }
}
