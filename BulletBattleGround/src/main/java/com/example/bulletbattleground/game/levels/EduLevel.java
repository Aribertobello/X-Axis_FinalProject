package com.example.bulletbattleground.game.levels;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.EducationGameController;
import com.example.bulletbattleground.game.*;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class EduLevel extends FreePlayLevel {

    //Edu properties------------------------------------
    public double dataIncrement = 0;
    public static double INCREMENT_TIME = 0.25;
    public double chartsTime = 0;
    //---------------------------------------



    //Edu Controllers--------------------------------------
    public ChoiceBox<XYChart.Series> chart1ChoiceBox;
    public ChoiceBox<XYChart.Series> chart2ChoiceBox;
    public ChoiceBox<XYChart.Series> chart3ChoiceBox;
    public HBox chartBox;

    // series
    private XYChart.Series rSeries = new XYChart.Series<>();
    private XYChart.Series xSeries = new XYChart.Series();
    private XYChart.Series ySeries = new XYChart.Series();
    private XYChart.Series vSeries = new XYChart.Series();
    private XYChart.Series vxSeries = new XYChart.Series();
    private XYChart.Series vySeries = new XYChart.Series();
    private XYChart.Series aSeries = new XYChart.Series();
    private XYChart.Series axSeries = new XYChart.Series();
    private XYChart.Series aySeries = new XYChart.Series();
    private XYChart.Series kESeries = new XYChart.Series();
    private XYChart.Series pESeries = new XYChart.Series();
    private XYChart.Series tESeries = new XYChart.Series();
    private XYChart.Series vAngleSeries = new XYChart.Series();
    private XYChart.Series aAngleSeries = new XYChart.Series();
    //--------------------------------------------------------------

    /**
     * @param map
     */
    public EduLevel(Mapp map) throws IOException {
        super(map);
        double mapBounds = 4000;
        type = -1;
        getMap().setBounds(new double[]{mapBounds,mapBounds/2});
        createEducationUI();
        initializeSeries();
    }

    public boolean[] update(double dt,double time) {
        if (map.getActiveProjectile() != null) {
            map.getActiveProjectile().setForcesDisplayed(true);
        }
        boolean[] result = super.update(dt,time);
        updateCharts(dt,time);
        return result;
    }

    public void initializeSeries(){

        rSeries.setName("distance R vs Time");
        xSeries.setName("Coordinate X vs Time");
        ySeries.setName("Coordinate Y vs Time");
        vSeries.setName("Velocity vs Time");
        vxSeries.setName("Velocity X vs Time");
        vySeries.setName("Velocity Y vs Time");
        aSeries.setName("Acceleration vs Time");
        axSeries.setName("Acceleration X vs Time");
        aySeries.setName("Acceleration Y vs Time");
        kESeries.setName("Kinetic Energy vs Time");
        pESeries.setName("Potential Energy vs Time");
        tESeries.setName("Total Energy vs Time");
        vAngleSeries.setName("Angle of velocity vs Time");
        aAngleSeries.setName("Angle of Acceleration vs Time");
    }


    public void createEducationUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(BattleGround.class.getResource("EducationModeScene.fxml"));
        loader.load();
        EducationGameController controller = loader.getController();
        chartBox = controller.getChartBox();
        chartBox.setLayoutY(40);
        container.getChildren().add(chartBox);
        chart1ChoiceBox = controller.getChart1ChoiceBox();
        chart2ChoiceBox = controller.getChart2ChoiceBox();
        chart3ChoiceBox = controller.getChart3ChoiceBox();
        for(ChoiceBox choiceBox : controller.getChoiceBoxes()) {
            choiceBox.getItems().add(rSeries);
            choiceBox.getItems().add(xSeries);
            choiceBox.getItems().add(ySeries);
            choiceBox.getItems().add(vSeries);
            choiceBox.getItems().add(vxSeries);
            choiceBox.getItems().add(vySeries);
            choiceBox.getItems().add(aSeries);
            choiceBox.getItems().add(axSeries);
            choiceBox.getItems().add(aySeries);
            choiceBox.getItems().add(kESeries);
            choiceBox.getItems().add(pESeries);
            choiceBox.getItems().add(tESeries);
            choiceBox.getItems().add(vAngleSeries);
            choiceBox.getItems().add(aAngleSeries);
        }
    }

    public void updateCharts(double dt, double time){

        dataIncrement += dt;
        chartsTime += dt;
        //TODO Add increment
        if(map.getActiveProjectile()!=null) {
            if (dataIncrement >= INCREMENT_TIME) {
                Projectile proj = map.activeProjectile;
                Vector distance;
                if(map.getType()==1){
                    distance = proj.getCoordinate().distanceVector(new Coordinate(((Circle)map.getEarth()).getCenterX(), ((Circle)map.getEarth()).getCenterY()));
                } else  distance = proj.getCoordinate().distanceVector(selectedFighter.getCoordinate());

                double r = distance.magnitude();
                double x = distance.getX();
                double y = distance.getY();
                double a = proj.acceleration().magnitude();
                double ax = proj.acceleration().getX();
                double ay = proj.acceleration().getY();
                double v = proj.velocity().magnitude();
                double vx = proj.getVelocityX();
                double vy = proj.getVelocityY();
                double kE = proj.kE();
                double pE = proj.getMass() * a * r;
                double tE = pE + kE;
                double vAngle = proj.velocity().angle();
                double aAngle = proj.acceleration().angle();

                rSeries.getData().add(new XYChart.Data(chartsTime, r));
                xSeries.getData().add(new XYChart.Data(chartsTime, x));
                ySeries.getData().add(new XYChart.Data(chartsTime, y));
                vSeries.getData().add(new XYChart.Data(chartsTime, v));
                vxSeries.getData().add(new XYChart.Data(chartsTime, vx));
                vySeries.getData().add(new XYChart.Data(chartsTime, vy));
                aSeries.getData().add(new XYChart.Data(chartsTime, a));
                axSeries.getData().add(new XYChart.Data(chartsTime, ax));
                aySeries.getData().add(new XYChart.Data(chartsTime, ay));
                kESeries.getData().add(new XYChart.Data(chartsTime, kE));
                pESeries.getData().add(new XYChart.Data(chartsTime, pE));
                tESeries.getData().add(new XYChart.Data(chartsTime, tE));
                vAngleSeries.getData().add(new XYChart.Data(chartsTime, vAngle));
                aAngleSeries.getData().add(new XYChart.Data(chartsTime, aAngle));

                dataIncrement = 0;
            }
        } else {
            chartsTime = 0;
            rSeries.getData().clear();
            xSeries.getData().clear();
            ySeries.getData().clear();
            vSeries.getData().clear();
            vxSeries.getData().clear();
            vySeries.getData().clear();
            aSeries.getData().clear();
            axSeries.getData().clear();
            aySeries.getData().clear();
            kESeries.getData().clear();
            pESeries.getData().clear();
            tESeries.getData().clear();
            vAngleSeries.getData().clear();
            aAngleSeries.getData().clear();
        }
    }
}
