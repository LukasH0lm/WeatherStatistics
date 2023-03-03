package com.monkeygang.weatherstatistics.ControlObjects;


import java.sql.Timestamp;

public class Measurement {
    WeatherStation station;
    Timestamp date;
    double rain; //what measure unit?
    double rainMinutes;
    double minTemp;
    double avgTemp;
    double maxTemp;
    int sunshine; //what measure unit?
    double avgWindSpeed; //what measure unit?
    double maxWindSpeed; //what measure unit?
    double skyHeight; //what measure unit?
    double cloudCover; //what measure unit?

    //currently, both station and weatherData has a connection to each other through their constructors.
    // Is this good practice?

    //some variables have different names in different places
    //such as maxWindSpeed and maxWindGust
    //we should talk about naming conventions
    public Measurement(WeatherStation station, Timestamp date, double rain, double rainMinutes, double minTemperature, double avgTemp, double maxTemp, int sunshine, double avgWindSpeed, double maxWindSpeed, double skyHeight, double cloudCover) {
        this.station = station;
        this.date = date; //should prop be Date, although we need to discuss how to do it
        this.rain = rain;
        this.rainMinutes = rainMinutes;
        this.minTemp = minTemperature;
        this.avgTemp = avgTemp;
        this.maxTemp = maxTemp;
        this.sunshine = sunshine;
        this.avgWindSpeed = avgWindSpeed;
        this.maxWindSpeed = maxWindSpeed;
        this.skyHeight = skyHeight;
        this.cloudCover = cloudCover;
    }

    //didn't make any set methods as we don't want to change the data
    public WeatherStation getStation() {
        return station;
    }

    public Timestamp getDate() {
        return date;
    }

    public double getRain() {
        return rain;
    }

    public double getRainMinutes() {
        return rainMinutes;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public int getSun() {
        return sunshine;
    }

    public double getAvgWind() {
        return avgWindSpeed;
    }

    public double getMaxWindGust() {
        return maxWindSpeed;
    }

    public double getSkyHeight() {
        return skyHeight;
    }

    public double getCloudCover() {
        return cloudCover;
    }

}
