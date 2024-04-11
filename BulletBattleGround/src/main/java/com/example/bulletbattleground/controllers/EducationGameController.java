package com.example.bulletbattleground.controllers;

import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationGameController extends GameSceneController{

    @Getter
    @Setter
    public Group charts;
    @Getter
    @Setter
    public LineChart xVTChart;
    @Getter
    @Setter
    public LineChart yVTChart;
    @Getter
    @Setter
    public LineChart aVTChart;
    @Getter
    @Setter
    public LineChart rVTChart;
    @Getter
    @Setter
    public LineChart vxVTChart;
    @Getter
    @Setter
    public LineChart vyVTChart;
}
