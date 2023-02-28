package com.monkeygang.weatherstatistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class CSVParser {

    public static void displayWeather() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = new Scanner(new File(Objects.requireNonNull(WeatherApplication.class.getResource("data.csv")).toURI()));
        scanner.useDelimiter(";|\\n");
        // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); //ignores a bunch of bozo characters
        System.out.println("csv version: " + scanner.next());

        //skip the first 15 lines, as they are empty
        for (int i = 0; i < 15; i++){
            scanner.next();
        }

        for (int i = 0; i < 16; i++){
            System.out.println("Header: " + scanner.next()); //prints the headers
        }

        for(int i = 0; i < (520 * 16);i++){
            scanner.next(); //skips the first 255 * 16 lines of data, as they don't have all the data
        }

        for (int i = 0; i < 3; i++) { //prints the first 3 lines of data

            System.out.println("Date: " + scanner.next());
            System.out.println("StationName: " + scanner.next());
            System.out.println("StationID: " + scanner.next());
            System.out.println("Coordinates: " + scanner.next());
            System.out.println("Height: " + scanner.next());
            System.out.println("SetupDate: " + scanner.next());
            System.out.println("Rain: " + scanner.next());
            System.out.println("RainMinutes: " + scanner.next());
            System.out.println("avgTemp: " + scanner.next());
            System.out.println("maxTemp: " + scanner.next());
            System.out.println("minTemp: " + scanner.next());
            System.out.println("Sun: " + scanner.next());
            System.out.println("avgWind: " + scanner.next());
            System.out.println("maxWindGust: " + scanner.next());
            System.out.println("skyHeight: " + scanner.next());
            System.out.println("cloudCover: " + scanner.next());


        }

        LinkedList<WeatherStation> weatherStations = new LinkedList<>();

        String date;
        String stationName;
        int stationID;
        String coordinates;
        double height;
        Date setupDate = new Date();

        boolean isDuplicate = false;


        double rain = 0;
        double rainMinutes = 0;
        double avgTemp = 0;
        double maxTemp = 0;
        double minTemp = 0;
        int sun = 0;
        double avgWind = 0;
        double maxWindGust = 0;
        double skyHeight = 0;
        double cloudCover = 0;




        for (int i = 0; i < 3; i++) { //creates 3 weather data objects with a station object

            date = scanner.next();
            stationName = scanner.next();
            stationID = Integer.parseInt(scanner.next());
            coordinates = scanner.next();
            height = Double.parseDouble(scanner.next().replace(",", "."));
            setupDate = new Date(scanner.next()); //this doesn't work at all, it shouldn't even run lol

            isDuplicate = false;

            for (WeatherStation weatherStation : weatherStations) {
                if (weatherStation.getStationID() == stationID) {
                    isDuplicate = true;
                    break;
                }

            }

            if (!isDuplicate){
                weatherStations.add(new WeatherStation(stationName, stationID, coordinates, height, setupDate));
            }


            rain = Double.parseDouble(scanner.next());
            rainMinutes = Double.parseDouble(scanner.next());
            avgTemp = Double.parseDouble(scanner.next().replace(",", "."));
            maxTemp = Double.parseDouble(scanner.next().replace(",", "."));
            minTemp = Double.parseDouble(scanner.next().replace(",", "."));
            sun = Integer.parseInt(scanner.next());
            avgWind = Double.parseDouble(scanner.next().replace(",", "."));
            maxWindGust = Double.parseDouble(scanner.next().replace(",", "."));
            skyHeight = Double.parseDouble(scanner.next().replace(",", "."));
            cloudCover = Double.parseDouble(scanner.next().replace(",", "."));

            for (WeatherStation weatherStation : weatherStations) {
                if (weatherStation.getStationID() == stationID) {
                    weatherStation.addWeatherData(new WeatherData(weatherStation, date, rain, rainMinutes, avgTemp, maxTemp, minTemp, sun, avgWind, maxWindGust, skyHeight, cloudCover));
                    break;
                }

            }


        }

        for (WeatherStation weatherStation : weatherStations) {
            System.out.println();
            System.out.println("Station: ");
            System.out.println(weatherStation.getStationName() + " " + weatherStation.getStationID() + " " + weatherStation.getCoordinates() + " " + weatherStation.getHeight() + " " + weatherStation.getSetupDate());
            System.out.println();
            System.out.println("Weather Data: ");

            int i = 1;

            for (WeatherData weatherData : weatherStation.getWeatherData()){
                System.out.println();
                System.out.println("Weather Data " + i++ + ": ");
                System.out.println();
                System.out.println("date: " + weatherData.getDate());
                System.out.println("rain: " + weatherData.getRain());
                System.out.println("rainMinutes: " + weatherData.getRainMinutes());
                System.out.println("avgTemp: " + weatherData.getAvgTemp());
                System.out.println("maxTemp: " + weatherData.getMaxTemp());
                System.out.println("minTemp: " + weatherData.getMinTemp());
                System.out.println("sun: " + weatherData.getSun());
                System.out.println("avgWind: " + weatherData.getAvgWind());
                System.out.println("maxWindGust: " + weatherData.getMaxWindGust());
                System.out.println("skyHeight: " + weatherData.getSkyHeight());
                System.out.println("cloudCover: " + weatherData.getCloudCover());
            }
        }


    }


}
