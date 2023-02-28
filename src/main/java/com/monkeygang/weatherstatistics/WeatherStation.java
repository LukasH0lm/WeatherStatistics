package com.monkeygang.weatherstatistics;

import java.util.Date;
import java.util.LinkedList;

public class WeatherStation {

    String stationName;
    int stationID;
    String coordinates;
    double height;
    Date setupDate;

    LinkedList<WeatherData> weatherData = new LinkedList<>();
    //currently, both station and weatherData has a connection to each other through their constructors.
    // Is this good practice?
    public WeatherStation(String stationName, int stationID, String coordinates, double height, Date setupDate) {
        this.stationName = stationName;
        this.stationID = stationID;
        this.coordinates = coordinates; // should prop be double[]
        this.height = height;
        this.setupDate = setupDate;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    public void addWeatherData(WeatherData weatherData) {
        this.weatherData.add(weatherData);
    }

    public LinkedList<WeatherData> getWeatherData() {
        return this.weatherData;
    }

}
