package com.monkeygang.weatherstatistics.Controller;

import com.monkeygang.weatherstatistics.BuisnessLogic.ChartBuilder;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;


import java.sql.SQLException;
import java.time.LocalDate;

public class WeatherController {

    public WeatherController() {

    }

    @FXML
    public void initialize() throws SQLException {
        //sets the default value of the choice boxes to "data" and "station"
        //adds listeners to the choice boxes
        dataChoice1.getItems().addAll("Rain", "Temperature", "Sunshine", "Wind", "sky height", "Cloud cover");
        dataChoice2.getItems().addAll("Rain", "Temperature", "Sunshine", "Wind", "sky height", "Cloud cover");
        dataChoice1.setValue("Rain");
        dataChoice2.setValue("Temperature");
        addListener(dataChoice1);
        addListener(dataChoice2);

        stationChoice1.getItems().addAll("Skagen Fyr", "Isenvad", "Billund Lufthavn", "Store Jyndevad", "Røsnæs fyr", "Hammer Odde Fyr");
        stationChoice2.getItems().addAll("Skagen Fyr", "Isenvad", "Billund Lufthavn", "Store Jyndevad", "Røsnæs fyr", "Hammer Odde Fyr");
        stationChoice1.setValue("Skagen Fyr"); //testing with underscore, should be "Skagen Fyr"
        stationChoice2.setValue("Isenvad");
        addListener(stationChoice1);
        addListener(stationChoice2);

        //sets the default value of the date pickers to the first working week of 2023
        //date range is inclusive of border values
        startDatePicker.setValue(LocalDate.of(2023, 1, 2));
        endDatePicker.setValue(LocalDate.of(2023, 1, 8));

        //you could probably change the input parameter of addListener() to node or some shit and then just call addListener() on all the choice boxes and date pickers
        //but I'm too lazy to do that

        startDatePicker.setOnAction(event -> {
            try {
                update();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        endDatePicker.setOnAction(event -> {
            try {
                update();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        //for some reason when you pick the same station for both choice boxes one of their dates gets offset

        update();

    }

    public void update() throws SQLException {
        borderpane.setLeft(ChartBuilder.buildBarChart(dataChoice1.getValue(), dataChoice2.getValue(), stationChoice1.getValue(), stationChoice2.getValue(), startDatePicker.getValue(), endDatePicker.getValue()));
    }

    public void addListener(ChoiceBox<String> choiceBox) {
        choiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    try {
                        update();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });


    }


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
