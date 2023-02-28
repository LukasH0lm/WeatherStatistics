package com.monkeygang.weatherstatistics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException { //remove URISyntaxException

        CSVParser.displayWeather();

        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("Weather-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}