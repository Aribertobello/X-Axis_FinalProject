package com.example.bulletbattleground.fileManagement;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.fileManagement.FileManager;
import com.example.bulletbattleground.game.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;


public class User {

    @FXML
    private Button LoginButton;

    @FXML
    private Button signInButton;

    @FXML
    private HBox AccountHbox;

    @FXML
    private TextField PassWordTextField;

    @FXML
    private TextField UserNameTextField;


    protected int pVeLevelsCompleted = 5;
    protected int pVeMaxLevelIndex = 11;
    protected int pVCLevelsCompleted = 5;
    protected int pVCMaxLevelIndex = 5;




    protected String password;
    protected String username;
    protected boolean loggedin;
    protected boolean isSignedIn = false;

    private FileManager fileManager;

    /**
     * Constructor for the User class. Initializes a new instance of FileManager.
     *
     * @throws FileNotFoundException If the specified file path is not found.
     */
    public User() {
        try {
            fileManager = new FileManager("C:\\Users\\aribe\\IdeaProjects\\X-Axis_FinalProject\\Data.txt");
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

        if (true/*FileManager.loadUserData(username,password)*/) { //<---TODO remove true from if statement and remove brackets to make login work
            loggedin = true;
        }

        if (username.isEmpty() || password.isEmpty()) {
            loggedin = false;
        }

        if (true)/*<---TODO change back to normal login*/ {
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

            if (isSignedIn = true) {
                FileManager.saveUserdata(username, password);
                Scene mainMenuScene = new Scene(BattleGround.mainMenuLoader().load());
                BattleGround.newScene(mainMenuScene);
            }

        }
    }

    public boolean isUnlocked(Level level) {
        if(level.getType() == 1) return pVeMaxLevelIndex > level.getIndex();
        return pVCMaxLevelIndex > level.getIndex();
    }
}
