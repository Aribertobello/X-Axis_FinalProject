package main.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Label label =new Label("SELECT YOUR DESIRED DIFFICULTY");

        RadioButton easyMode = new RadioButton("Easy");
        RadioButton mediumMode = new RadioButton("Medium");
        RadioButton hardMode = new RadioButton("Hard");

        easyMode.setOnAction(event -> {label.setText("GAME IS NOT FINISHED CANNOT SELECT DIFFICULTY, please leave the game");});
        hardMode.setOnAction(event -> {label.setText("GAME IS NOT FINISHED CANNOT SELECT DIFFICULTY, please leave the game");});
        mediumMode.setOnAction(event -> {label.setText("GAME IS NOT FINISHED CANNOT SELECT DIFFICULTY, please leave the game");});


        ToggleGroup difficultyGroup = new ToggleGroup();
        easyMode.setToggleGroup(difficultyGroup);
        mediumMode.setToggleGroup(difficultyGroup);
        hardMode.setToggleGroup(difficultyGroup);

        VBox VBox = new VBox(10,label);
        VBox.setAlignment(Pos.CENTER);
        VBox.getChildren().add(new HBox(10,easyMode,mediumMode,hardMode));



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(VBox, 1920, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}