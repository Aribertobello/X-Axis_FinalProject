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
import java.io.IOException;

public class EduLevel extends Level {


    //Edu Controllers--------------------------------------
    public Group charts;
    public LineChart xVTChart;
    public LineChart yVTChart;
    public LineChart aVTChart;
    public LineChart rVTChart;
    public LineChart vxVTChart;
    public LineChart vyVTChart;

    // series
    private XYChart.Series rSeries = new XYChart.Series();
    private XYChart.Series xSeries = new XYChart.Series();
    private XYChart.Series ySeries = new XYChart.Series();
    private XYChart.Series aSeries = new XYChart.Series();
    private XYChart.Series vxSeries = new XYChart.Series();
    private XYChart.Series vySeries = new XYChart.Series();
    //--------------------------------------------------------------

    /**
     * @param map
     */
    public EduLevel(Mapp map) throws IOException {
        super(map);
        createEducationUI();
    }

    public boolean[] update(double dt,double time) {
        boolean[] result = super.update(dt,time);
        updateCharts(time);
        return result;
    }


    public void createEducationUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(BattleGround.class.getResource("EducationModeScene.fxml"));
        loader.load();
        EducationGameController controller = loader.getController();
        charts = controller.getCharts();
        container.getChildren().add(charts);
        rVTChart = controller.getRVTChart();
        rVTChart.getData().add(rSeries);
        xVTChart = controller.getXVTChart();
        xVTChart.getData().add(xSeries);
        yVTChart = controller.getYVTChart();
        yVTChart.getData().add(ySeries);
        aVTChart = controller.getAVTChart();
        aVTChart.getData().add(aSeries);
        vxVTChart = controller.getVxVTChart();
        vxVTChart.getData().add(vxSeries);
        vyVTChart = controller.getVyVTChart();
        vyVTChart.getData().add(vySeries);
    }

    public void updateCharts(double time){
        //TODO Add increment
        if(map.activeProjectile!=null){

            Projectile proj = map.activeProjectile;
            Vector distance = proj.getCoordinate().distanceVector(new Coordinate(map.getEarth().getCenterX(),map.getEarth().getCenterY()));
            double r = distance.magnitude();
            double x = distance.getX();
            double y = distance.getY();
            double a = proj.acceleration().magnitude();
            double vx = proj.getVelocityX();
            double vy = proj.getVelocityY();

            rSeries.getData().add(new XYChart.Data(time, r));
            xSeries.getData().add(new XYChart.Data(time, x));
            ySeries.getData().add(new XYChart.Data(time, y));
            aSeries.getData().add(new XYChart.Data(time, a));
            vxSeries.getData().add(new XYChart.Data(time, vx));
            vySeries.getData().add(new XYChart.Data(time, vy));

        }
    }
}
