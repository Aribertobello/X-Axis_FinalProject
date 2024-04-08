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

    static protected ArrayList<String> pVeLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> pVcLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> mapsArrayList = new ArrayList<>();
    static protected String userDataLocation = "";//TODO Put User Data File Here
    static public Game activeGame;

    static {
        try {
            activeGame = new Game(FileManager.defaultLevelPvc());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static protected User user = new User();

    @Override
    public void start(Stage stage) throws IOException {
        Scene MainMenuscene = new Scene(signInPageLoader().load());
        stage.setTitle("Bullet BattleGround");
        stage.setScene(MainMenuscene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



    public static FXMLLoader mainMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));}
    public static FXMLLoader signInPageLoader(){ return new FXMLLoader(BattleGround.class.getResource("SignInPageScene.fxml"));}
    public static FXMLLoader subMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("submenuScene.fxml"));}
    public static FXMLLoader pveLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVELevelSelector.fxml"));}
    public static FXMLLoader pvcLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVCLevelSelector.fxml"));}
    public static FXMLLoader pvpLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVPLevelSelector.fxml"));}

    public static FXMLLoader classSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("ClassSelector.fxml"));}
    public static FXMLLoader gameLoader(){ return new FXMLLoader(BattleGround.class.getResource("GameScene.fxml"));}

}