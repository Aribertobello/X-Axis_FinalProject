package com.example.bulletbattleground;

import com.example.bulletbattleground.fileManagement.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    protected int pVeLevelsCompleted;
    protected int pVeMaxLevelIndex;
    protected int pVCLevelsCompleted;
    protected int pVCMaxLevelIndex;
    protected String password;
    protected String username;
    protected String usernameTest= "Lucas";
    protected String passwordTest= "Lucas";
    protected boolean loggedin;
    protected boolean isSignedIn= false;

    private FileManager fileManager;
    public User() {
        try {
            fileManager = new FileManager("C:\\Users\\aribe\\IdeaProjects\\X-Axis_FinalProject\\BulletBattleGround\\Data.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Login(ActionEvent event)  throws IOException{
        username = UserNameTextField.getText();
        password = PassWordTextField.getText();

        if(FileManager.loadUserData(username,password)){
            loggedin=true;
        }

        if(username.isEmpty()||password.isEmpty()){
            loggedin=false;
        }

        if (true)/*<---TODO change back to normal login*/{
            {
                FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene mainMenuScene = new Scene(fxmlLoader.load());
                stage.setScene(mainMenuScene);
                stage.show();
            }
        }

    }

    @FXML
    void SignUp(ActionEvent event) throws IOException{
        username = UserNameTextField.getText();
        password = PassWordTextField.getText();

        if(username.isEmpty()||password.isEmpty()){
            isSignedIn=false;
        }
        else {
            if (isSignedIn = true) {
                FileManager.saveUserdata(username, password);
                FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene mainMenuScene = new Scene(fxmlLoader.load());
                stage.setScene(mainMenuScene);
                stage.show();
            }
        }
    }


}
