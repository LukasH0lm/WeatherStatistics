package com.monkeygang.weatherstatistics.ControlObjects;

import java.util.Date;
import java.util.LinkedList;

public class WeatherStation {

    String stationName;
    int stationID;
    String coordinates;
    double height;
    Date setupDate;

    LinkedList<Measurement> weatherData = new LinkedList<>();
    //currently, both station and weatherData has a connection to each other through their constructors.
    // Is this good practice?
    public WeatherStation(String stationName, int stationID, String coordinates, double height, Date setupDate) {
        this.stationName = stationName;
        this.stationID = stationID;
        this.coordinates = coordinates; // should prop be double[]
        this.height = height;
        this.setupDate = setupDate;
    }

    //didn't make any set methods as we don't want to change the data

    public String getStationName() {
        return stationName;
    }


    public int getStationID() {
        return stationID;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public double getHeight() {
        return height;
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public String getSetupDateString() {
        return setupDate.toString();
    }

    public void addWeatherData(Measurement measurement) {
        this.weatherData.add(measurement);
    }

    public LinkedList<Measurement> getWeatherData() {
        return this.weatherData;
    }

}
