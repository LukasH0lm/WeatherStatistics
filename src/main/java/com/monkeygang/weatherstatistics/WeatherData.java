package com.monkeygang.weatherstatistics;


public class WeatherData {
    WeatherStation station;
    String date;
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
    public WeatherData(WeatherStation station, String date, double rain, double rainMinutes, double minTemperature, double avgTemp, double maxTemp, int sunshine, double avgWindSpeed, double maxWindSpeed, double skyHeight, double cloudCover) {
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

    public WeatherStation getStation() {
        return station;
    }

    public void setStation(WeatherStation station) {
        this.station = station;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getRainMinutes() {
        return rainMinutes;
    }

    public void setRainMinutes(double rainMinutes) {
        this.rainMinutes = rainMinutes;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemperature) {
        this.minTemp = minTemperature;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getSun() {
        return sunshine;
    }

    public void setSunshine(int sunshine) {
        this.sunshine = sunshine;
    }

    public double getAvgWind() {
        return avgWindSpeed;
    }

    public void setAvgWind(double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public double getMaxWindGust() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public double getSkyHeight() {
        return skyHeight;
    }

    public void setSkyHeight(double skyHeight) {
        this.skyHeight = skyHeight;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }
}
