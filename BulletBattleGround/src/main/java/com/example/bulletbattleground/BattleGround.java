package com.example.bulletbattleground;

import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.fileManagement.User;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.levels.FreePlayLevel;
import com.example.bulletbattleground.game.levels.StandardLevel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class BattleGround extends Application {

    static public Game activeGame;
    public static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    public static int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    public static Stack<Scene> workFlowStack = new Stack<>();
    public static Stage mainStage;
    public static String username;
    public static User user = new User();


    /**
     * The entry point for the JavaFX application. Initializes the primary stage
     * and loads the sign-in page scene.
     *
     * @param stage The primary stage of the application.
     * @throws IOException If an error occurs while loading the sign-in page scene.
     */
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        activeGame = null;
        Scene signInScene = new Scene(signInPageLoader().load());
        stage.setTitle("Bullet BattleGround");
        newScene(signInScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void newScene(Scene newScene){
        workFlowStack.push(newScene);
        mainStage.setScene(workFlowStack.peek());
    }

    public static void prevScene(){
        if(mainStage.getScene() == activeGame){
            activeGame = null;
        }
        workFlowStack.pop();
        mainStage.setScene(workFlowStack.peek());
        center();
    }

    public static void center(){
        mainStage.setFullScreen(false);
        mainStage.setMaximized(false);

        double centerX = (screenWidth - mainStage.getWidth()) / 2;
        double centerY = (screenHeight - mainStage.getHeight()) / 2;

        mainStage.setX(centerX);
        mainStage.setY(centerY);
    }

    public static void fullscreen(){
        mainStage.setMaximized(true);
        mainStage.setFullScreen(true);
    }

    public static FXMLLoader mainMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));}
    public static FXMLLoader signInPageLoader(){ return new FXMLLoader(BattleGround.class.getResource("SignInPageScene.fxml"));}
    public static FXMLLoader subMenuLoader(){ return new FXMLLoader(BattleGround.class.getResource("submenuScene.fxml"));}
    public static FXMLLoader pveLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVELevelSelector.fxml"));}
    public static FXMLLoader pvcLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVCLevelSelector.fxml"));}
    public static FXMLLoader pvpLevelSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("PVPLevelSelector.fxml"));}
    public static FXMLLoader classSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("ClassSelector.fxml"));}
    public static FXMLLoader instructionBoxLoader(){ return new FXMLLoader(BattleGround.class.getResource("InstructionBox.fxml"));}
    public static FXMLLoader mapSelectorLoader(){ return new FXMLLoader(BattleGround.class.getResource("MapSelectorScene.fxml"));}

}