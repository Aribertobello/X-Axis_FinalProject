package com.example.bulletbattleground.fileManagement;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Game;
import com.example.bulletbattleground.game.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


public class User {

    @FXML
    private TextField PassWordTextField;

    @FXML
    private TextField UserNameTextField;
    public HashMap <String, Integer> pVeUserProgress = new HashMap <String, Integer>();
    protected String password;
    @Getter  protected String username;
    protected boolean loggedin;
    protected boolean isSignedIn = false;
    private FileManager fileManager;
    public int userLevelIndexPVEProgress = 0;


    /**
     * Constructor for the User class. Initializes a new instance of FileManager.
     *
     * @throws FileNotFoundException If the specified file path is not found.
     */
    public User() {
        try {
            fileManager = new FileManager("Files/txt/save.txt");
            pVeUserProgress = FileManager.loadPVEProgress();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the login functionality.
     *
     * @param event The ActionEvent that triggered the login attempt.
     * @throws IOException If an error occurs while loading the main menu scene.
     */
    @FXML
    void Login(ActionEvent event) throws IOException {

        username = UserNameTextField.getText();
        password = PassWordTextField.getText();

        if (FileManager.loadUserData(username,password)) {
            loggedin = true;
            updateProgress(username, getProgress(username));
            BattleGround.username = username;

        }

        if (username.isEmpty() || password.isEmpty()) {
            loggedin = false;
        }

        if (loggedin){
            {
                Scene mainMenuScene = new Scene(BattleGround.mainMenuLoader().load());
                BattleGround.newScene(mainMenuScene);
            }
        }

    }

    /**
     * Handles the sign-up functionality.
     *
     * @param event The ActionEvent that triggered the sign-up attempt.
     * @throws IOException If an error occurs while loading the main menu scene.
     */
    @FXML
    void SignUp(ActionEvent event) throws IOException {

        username = UserNameTextField.getText();
        password = PassWordTextField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            isSignedIn = false;
        } else {
            FileManager.saveUserdata(username,password);
            isSignedIn = true;
            if (isSignedIn) {
                Scene mainMenuScene = new Scene(BattleGround.mainMenuLoader().load());
                BattleGround.newScene(mainMenuScene);
            }

        }
    }

    public int getProgress(String username) {
        return pVeUserProgress.getOrDefault(username, 1);
    }

    public void updateProgress(String username, int progress) {
        pVeUserProgress.put(username, progress);
        FileManager.savePVEProgress(pVeUserProgress);
    }

    public boolean isUnlocked(Level level) {
        userLevelIndexPVEProgress = getProgress(BattleGround.username);

        if (level.getType() == 1) {
        return userLevelIndexPVEProgress >= level.getIndex(); // Check if user has completed enough levels...returns true or false
    }
//        else if (level.getType() == 2){
//        return TODO add the pvc thing
//        }

//        else{
//        return userProgress >= level.getIndex(); // Check if user has unlocked the level based on progress
//            }
        else{
            return true;
        }
        
    }
}
