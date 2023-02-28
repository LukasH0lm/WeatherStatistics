module com.monkeygang.weatherstatistics {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.monkeygang.weatherstatistics to javafx.fxml;
    exports com.monkeygang.weatherstatistics;
}