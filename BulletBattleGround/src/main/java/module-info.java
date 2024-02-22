module main.bulletbattleground {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens main.bulletbattleground to javafx.fxml;
    exports main.bulletbattleground;
}