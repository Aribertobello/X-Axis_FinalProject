package com.example.bulletbattleground;

import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class BattleGround extends Application {
    static protected ArrayList<String> pVeLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> pVcLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> mapsArrayList = new ArrayList<>();
    static protected String userDataLocation = "";
    static protected Game activeGame = new Game(FileManager.defaultLevelPvc());
    static protected User user = new User();

    /**
     * The entry point for the JavaFX application. Initializes the primary stage
     * and loads the sign-in page scene.
     *
     * @param stage The primary stage of the application.
     * @throws IOException If an error occurs while loading the sign-in page scene.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("SignInPageScene.fxml"));
        Scene MainMenuscene = new Scene(fxmlLoader.load());
        stage.setTitle("Bullet BattleGround");
        stage.setScene(MainMenuscene);
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}