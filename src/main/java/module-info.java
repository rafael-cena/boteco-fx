module com.example.botecofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.botecofx to javafx.fxml;
    exports com.example.botecofx;
}