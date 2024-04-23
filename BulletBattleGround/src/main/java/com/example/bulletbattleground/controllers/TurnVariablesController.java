package com.example.bulletbattleground.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TurnVariablesController {
    //TODO CLASS COMPLETE
    @FXML
    public Label turnStatusLabel;
    @FXML
    public Label timeLeftLabel;
    @FXML
    public ProgressBar timerBar;
    @FXML
    public Pane turnStatusBox;
}