module com.example.botecofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.botecofx to javafx.fxml;
    exports com.example.botecofx;
}