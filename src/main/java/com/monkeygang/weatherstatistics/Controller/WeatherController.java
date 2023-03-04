package com.monkeygang.weatherstatistics.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;

public class WeatherController {

    @FXML
    private BorderPane borderpane;

    @FXML
    private ChoiceBox<String> dataChoice1;

    @FXML
    private ChoiceBox<String> dataChoice2;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ChoiceBox<String> stationChoice1;

    @FXML
    private ChoiceBox<String> stationChoice2;

}
