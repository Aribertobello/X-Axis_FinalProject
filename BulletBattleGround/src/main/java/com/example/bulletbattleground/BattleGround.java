package com.example.bulletbattleground;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BattleGround extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));
        Scene MainMenuscene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(MainMenuscene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}