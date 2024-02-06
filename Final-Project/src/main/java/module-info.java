module main.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.finalproject to javafx.fxml;
    exports main.finalproject;
}