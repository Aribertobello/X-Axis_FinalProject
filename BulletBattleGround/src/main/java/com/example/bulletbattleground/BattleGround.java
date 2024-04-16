package com.example.bulletbattleground;

import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.fileManagement.User;
import com.example.bulletbattleground.game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class BattleGround extends Application {

    static protected ArrayList<String> pVeLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> pVcLevelsArrayList = new ArrayList<>();
    static protected ArrayList<String> mapsArrayList = new ArrayList<>();
    static protected String userDataLocation = "";//TODO Put User Data File Here
    static public Game activeGame;
    public static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    public static int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();


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

    public static FXMLLoader mainMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));}
    public static FXMLLoader signInPageLoader(){ return new FXMLLoader(BattleGround.class.getResource("SignInPageScene.fxml"));}
    public static FXMLLoader subMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("submenuScene.fxml"));}
    public static FXMLLoader pveLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVELevelSelector.fxml"));}
    public static FXMLLoader pvcLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVCLevelSelector.fxml"));}
    public static FXMLLoader pvpLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVPLevelSelector.fxml"));}
}