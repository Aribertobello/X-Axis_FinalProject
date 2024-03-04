package com.example.bulletbattleground;

import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class BattleGround extends Application {
    static protected ArrayList<String> pVeLevelsArrayList = new ArrayList<String>();
    static protected ArrayList<String> pVcLevelsArrayList = new ArrayList<String>();
    static protected ArrayList<String> mapsArrayList = new ArrayList<String>();
    static protected String UserDataLocation = "";
    static protected Game activeGame = new Game(FileManager.defaultLevelPvp());
    static protected User user = new User();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));
        Scene MainMenuscene = new Scene(fxmlLoader.load());
        stage.setTitle("Bullet BattleGround");
        stage.setScene(MainMenuscene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}