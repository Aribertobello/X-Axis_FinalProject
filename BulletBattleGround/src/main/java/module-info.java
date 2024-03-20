module com.example.bulletbattleground {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens com.example.bulletbattleground to javafx.fxml;
    exports com.example.bulletbattleground;
    exports com.example.bulletbattleground.game;
    opens com.example.bulletbattleground.game to javafx.fxml;
    exports com.example.bulletbattleground.utility;
    opens com.example.bulletbattleground.utility to javafx.fxml;
    exports com.example.bulletbattleground.fileManagement;
    opens com.example.bulletbattleground.fileManagement to javafx.fxml;
    exports com.example.bulletbattleground.gameObjects.fighters;
    opens com.example.bulletbattleground.gameObjects.fighters to javafx.fxml;
    exports com.example.bulletbattleground.gameObjects.projectiles;
    opens com.example.bulletbattleground.gameObjects.projectiles to javafx.fxml;
    exports com.example.bulletbattleground.gameObjects.obstacles;
    opens com.example.bulletbattleground.gameObjects.obstacles to javafx.fxml;
    exports com.example.bulletbattleground.gameObjects.Loot;
    opens com.example.bulletbattleground.gameObjects.Loot to javafx.fxml;
}