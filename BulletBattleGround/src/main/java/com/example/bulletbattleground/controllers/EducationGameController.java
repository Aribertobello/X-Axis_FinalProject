package com.example.bulletbattleground.controllers;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class EducationGameController extends GameSceneController{
    //TODO CLASS COMPLETE
    public LineChart chart1;
    public ChoiceBox<XYChart.Series> chart1ChoiceBox;
    public LineChart chart2;
    public ChoiceBox<XYChart.Series> chart2ChoiceBox;
    public LineChart chart3;
    public ChoiceBox<XYChart.Series> chart3ChoiceBox;
    public HBox chartBox;
    ArrayList<ChoiceBox> choiceBoxes = new ArrayList<>();
    ArrayList<LineChart> charts= new ArrayList<>();


    public void initialize(){
        charts= new ArrayList<>();
        charts.add(chart1);
        charts.add(chart2);
        charts.add(chart3);
        choiceBoxes = new ArrayList<>();
        choiceBoxes.add(chart1ChoiceBox);
        choiceBoxes.add(chart2ChoiceBox);
        choiceBoxes.add(chart3ChoiceBox);
        for (int i = 0; i < charts.size(); i++) {
            AtomicInteger I = new AtomicInteger(i);
            choiceBoxes.get(i).setOnAction(event ->
            {
                charts.get(I.get()).getData().clear();
                charts.get(I.get()).getData().add(choiceBoxes.get(I.get()).getSelectionModel().getSelectedItem());
            });
        }
    }
}
