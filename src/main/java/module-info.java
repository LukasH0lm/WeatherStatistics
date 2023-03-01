module com.monkeygang.weatherstatistics {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.monkeygang.weatherstatistics to javafx.fxml;
    exports com.monkeygang.weatherstatistics;
    exports com.monkeygang.weatherstatistics.BuisnessLogic;
    opens com.monkeygang.weatherstatistics.BuisnessLogic to javafx.fxml;
    exports com.monkeygang.weatherstatistics.ControlObjects;
    opens com.monkeygang.weatherstatistics.ControlObjects to javafx.fxml;
    exports com.monkeygang.weatherstatistics.Controller;
    opens com.monkeygang.weatherstatistics.Controller to javafx.fxml;
}