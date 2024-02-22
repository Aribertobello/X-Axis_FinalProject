module com.example.bulletbattleground {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bulletbattleground to javafx.fxml;
    exports com.example.bulletbattleground;
}