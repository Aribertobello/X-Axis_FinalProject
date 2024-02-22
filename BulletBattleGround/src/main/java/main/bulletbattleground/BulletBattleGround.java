package main.bulletbattleground;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD:Final-Project/src/main/java/main/finalproject/HelloApplication.java


=======
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
>>>>>>> main:BulletBattleGround/src/main/java/main/bulletbattleground/HelloApplication.java
        stage.setTitle("Hello!");
        stage.setScene(new Scene(new HBox()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}