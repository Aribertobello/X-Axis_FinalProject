package com.example.bulletbattleground;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    @FXML
     void Login(ActionEvent event)  throws IOException{
        username = UserNameTextField.getText();
        password = PassWordTextField.getText();


        if(usernameTest.equals(username)&&passwordTest.equals(password)){
            loggedin=true;
        }
         if(username.isEmpty()||password.isEmpty()){
            loggedin=false;
        }

         if (loggedin == true){
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
    void SignIn(ActionEvent event) throws IOException{
        boolean isSignedIn= false;
        username = UserNameTextField.getText();
        password = PassWordTextField.getText();

        if(username.isEmpty()||password.isEmpty()){
            isSignedIn=false;
        }
        else {
             //System.out.println(username+password);
            if (isSignedIn = true) {
                FXMLLoader fxmlLoader = new FXMLLoader(BattleGround.class.getResource("mainMenuScene.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene mainMenuScene = new Scene(fxmlLoader.load());
                stage.setScene(mainMenuScene);
                stage.show();
            }
        }
    }


}
