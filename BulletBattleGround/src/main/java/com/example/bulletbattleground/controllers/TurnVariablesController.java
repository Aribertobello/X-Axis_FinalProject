package com.example.bulletbattleground.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

public class TurnVariablesController {
    @FXML
    @Getter
    @Setter
    public Label turnStatusLabel;
    @FXML
    @Getter
    @Setter
    public Label timeLeftLabel;
    @FXML
    @Getter
    @Setter
    public ProgressBar timerBar;
    @FXML
    @Getter
    @Setter
    public Pane turnStatusBox;
}