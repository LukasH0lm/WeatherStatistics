module com.monkeygang.weatherstatistics {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.monkeygang.weatherstatistics to javafx.fxml;
    exports com.monkeygang.weatherstatistics;
}