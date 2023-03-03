package com.monkeygang.weatherstatistics.BuisnessLogic;

import com.monkeygang.weatherstatistics.ControlObjects.Measurement;
import com.monkeygang.weatherstatistics.ControlObjects.WeatherStation;
import com.monkeygang.weatherstatistics.WeatherApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class CSVParser {

    public static void displayWeather() throws FileNotFoundException, URISyntaxException, SQLException {
        Scanner scanner = new Scanner(new File(Objects.requireNonNull(WeatherApplication.class.getResource("data.csv")).toURI())); //reads the csv file
        scanner.useDelimiter("[;\\n]"); //changed the regex to avoid single character alteration, should still work tho
        // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); //ignores a bunch of bozo characters
        //System.out.println("csv version: " + scanner.next()); //removed, coulb be added again csv-11

        //skip the first 15 lines, as they are empty // not needed because of the removed header
        /*for (int i = 0; i < 15; i++){
            scanner.next();
        }*/


        //for some reason the program recognizes the first 16 lines as headers now

        /*for (int i = 0; i < 16; i++){
            System.out.println("Header: " + scanner.next()); //prints the headers
        }*/


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

        Timestamp date; // Timestamp is a date and time
        String stationName;
        int stationID;
        String coordinates;
        double height;
        Date setupDate;

        boolean isDuplicate;


        double rain;
        double rainMinutes;
        double avgTemp;
        double maxTemp;
        double minTemp;
        int sun;
        double avgWind;
        double maxWindGust;
        double skyHeight;
        double cloudCover;




        for (int i = 0; i < 3; i++) { //creates 3 weather data objects with a station object


            date = Timestamp.valueOf(scanner.next());
            stationName = scanner.next();
            stationID = Integer.parseInt(scanner.next());
            coordinates = scanner.next();
            height = Double.parseDouble(scanner.next().replace(",", "."));
            setupDate = DateFormatter.formatDate(scanner.next());

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
                    weatherStation.addWeatherData(new Measurement(weatherStation, date, rain, rainMinutes, minTemp, avgTemp, maxTemp, sun, avgWind, maxWindGust, skyHeight, cloudCover));
                    break;
                }

            }


        }

        //sends objects to database

        WeatherStationDaoImpl weatherStationDao = new WeatherStationDaoImpl();
        WeatherDataDaoImpl weatherDataDao = new WeatherDataDaoImpl();

        for (WeatherStation weatherStation : weatherStations) {
            weatherStationDao.addWeatherStation(weatherStation);

            for (Measurement measurement : weatherStation.getWeatherData()){
                weatherDataDao.addWeatherData(measurement);
            }

        }


        for (WeatherStation weatherStation : weatherStations) {
            System.out.println();
            System.out.println("Station: ");
            System.out.println("name: " + weatherStation.getStationName());
            System.out.println("id: " + weatherStation.getStationID());
            System.out.println("coordinates: " + weatherStation.getCoordinates());
            System.out.println("height: " + weatherStation.getHeight());
            System.out.println("setupDate: " + weatherStation.getSetupDate());
            System.out.println();
            System.out.println("Weather Data: ");


            int i = 1;

            for (Measurement measurement : weatherStation.getWeatherData()){
                System.out.println();
                System.out.println("Weather Data " + i++ + ": ");
                System.out.println();
                System.out.println("date: " + measurement.getDate());
                System.out.println("rain: " + measurement.getRain());
                System.out.println("rainMinutes: " + measurement.getRainMinutes());
                System.out.println("avgTemp: " + measurement.getAvgTemp());
                System.out.println("maxTemp: " + measurement.getMaxTemp());
                System.out.println("minTemp: " + measurement.getMinTemp());
                System.out.println("sun: " + measurement.getSun());
                System.out.println("avgWind: " + measurement.getAvgWind());
                System.out.println("maxWindGust: " + measurement.getMaxWindGust());
                System.out.println("skyHeight: " + measurement.getSkyHeight());
                System.out.println("cloudCover: " + measurement.getCloudCover());
            }
        }


    }


}
