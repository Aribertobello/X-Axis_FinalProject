package com.example.bulletbattleground;

import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class BattleGround extends Application {

    public static FXMLLoader mainMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));}
    public static FXMLLoader signInPageLoader(){ return new FXMLLoader(BattleGround.class.getResource("SignInPageScene.fxml"));}
    public static FXMLLoader subMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("submenuScene.fxml"));}
    public static FXMLLoader levelSelectorLoader = new FXMLLoader(BattleGround.class.getResource("LevelSelector.fxml"));
    public static FXMLLoader classSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("ClassSelector.fxml"));}
    public static FXMLLoader gameLoader(){ return new FXMLLoader(BattleGround.class.getResource("GameScene.fxml"));}
    static protected ArrayList<String> pVeLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> pVcLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> mapsArrayList = new ArrayList<>();
    static protected String userDataLocation = "";
    static public Game activeGame;

    static {
        try {
            activeGame = new Game(FileManager.defaultLevelPvc());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
        Scene MainMenuscene = new Scene(signInPageLoader().load());
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