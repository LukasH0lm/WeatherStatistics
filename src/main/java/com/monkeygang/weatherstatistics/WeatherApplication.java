package com.monkeygang.weatherstatistics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        System.out.println(
                """
            <-. (`-')             <-. (`-')_ <-.(`-')  (`-')  _                      (`-')  _ <-. (`-')_           \s
               \\(OO )_      .->      \\( OO) ) __( OO)  ( OO).-/     .->       .->    (OO ).-/    \\( OO) )    .->   \s
            ,--./  ,-.)(`-')----. ,--./ ,--/ '-'. ,--.(,------. ,--.'  ,-. ,---(`-') / ,---.  ,--./ ,--/  ,---(`-')\s
            |   `.'   |( OO).-.  '|   \\ |  | |  .'   / |  .---'(`-')'.'  /'  .-(OO ) | \\ /`.\\ |   \\ |  | '  .-(OO )\s
            |  |'.'|  |( _) | |  ||  . '|  |)|      /)(|  '--. (OO \\    / |  | .-, \\ '-'|_.' ||  . '|  |)|  | .-, \\\s
            |  |   |  | \\|  |)|  ||  |\\    | |  .   '  |  .--'  |  /   /) |  | '.(_/(|  .-.  ||  |\\    | |  | '.(_/\s
            |  |   |  |  '  '-'  '|  | \\   | |  |\\   \\ |  `---. `-/   /`  |  '-'  |  |  | |  ||  | \\   | |  '-'  | \s
            `--'   `--'   `-----' `--'  `--' `--' '--' `------'   `--'     `-----'   `--' `--'`--'  `--'  `-----'  \s"""
        );

        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("Weather-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}