package com.monkeygang.weatherstatistics.BuisnessLogic;


import javafx.scene.chart.*;

import java.sql.*;
import java.time.LocalDate;

public class ChartBuilder {


    XYChart.Series tempSeries;
    XYChart.Series tempSeries2;


    public XYChart.Series parseTempSeries(int seriesNumber){

        XYChart.Series tempSeriesToReturn = null;

       if(seriesNumber == 1){
         tempSeriesToReturn = tempSeries;
       } else if (seriesNumber == 2){

           tempSeriesToReturn = tempSeries2;
       }


        return tempSeriesToReturn;
    }

    public LineChart buildBarChart(String data1, String data2, String station1, String station2, LocalDate startDate, LocalDate endDate, boolean isInDays) throws SQLException {

        tempSeries = new XYChart.Series();

        tempSeries2 = new XYChart.Series();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("value");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        lineChart.setTitle("Weather statistics");

        tempSeries.setName(station1);

        tempSeries2.setName(station2);

        /*
        XYChart.Series rainSeries = new XYChart.Series();
        tempSeries.setName(data2);

        XYChart.Series rainSeries2 = new XYChart.Series();
        tempSeries2.setName(data2);
        */

        Connection con = ConnectionSingleton.getInstance().getConnection();

        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);

        int daysBetween = 0;

        if (end.getDay() < start.getDay()) {
            daysBetween = (end.getDay() + 30) - start.getDay();
        } else {
            daysBetween = end.getDay() - start.getDay();

        }


        // add one day to start and end date to include the first and last day
        //there could be something wrong with this idk
        startDate = startDate.minusDays(1);
        endDate = endDate.plusDays(0);

        Timestamp startTimestamp = Timestamp.valueOf(startDate.atStartOfDay());
        Timestamp endTimestamp = Timestamp.valueOf(endDate.atTime(23, 59, 59));


        PreparedStatement ps = null;
        String columnName = "";
        //could use strategy pattern here

        if (data1.equals("Temperature")) {
            ps = con.prepareStatement("SELECT WeatherStation.name, measurement.date_time, temperature_data.mid_temp " + "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " + "INNER JOIN temperature_data ON measurement.id = temperature_data.measurement_id " + "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ORDER BY date_time ");

            columnName = "mid_temp";


        } else if (data1.equals("Rain")) {
            ps = con.prepareStatement("SELECT WeatherStation.name, measurement.date_time, rain_data.rain " + "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " + "INNER JOIN rain_data ON measurement.id = rain_data.measurement_id " + "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ORDER BY date_time");

            columnName = "rain";

        }


        assert ps != null;
        ps.setString(1, station1);
        ps.setTimestamp(2, startTimestamp);
        ps.setTimestamp(3, endTimestamp);
        ResultSet rs = ps.executeQuery();


        PreparedStatement ps2 = null;

        String columnName2 = "";

        //could use strategy pattern here

        if (data2.equals("Temperature")) {
            ps2 = con.prepareStatement("SELECT WeatherStation.name, measurement.date_time, temperature_data.mid_temp " + "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " + "INNER JOIN temperature_data ON measurement.id = temperature_data.measurement_id " + "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ORDER BY date_time");

            columnName2 = "mid_temp";

        } else if (data2.equals("Rain")) {
            ps2 = con.prepareStatement("SELECT WeatherStation.name, measurement.date_time, rain_data.rain " + "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " + "INNER JOIN rain_data ON measurement.id = rain_data.measurement_id " + "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ORDER BY date_time");

            columnName2 = "rain";

        }


        assert ps2 != null;
        ps2.setString(1, station2);
        ps2.setTimestamp(2, startTimestamp);
        ps2.setTimestamp(3, endTimestamp);
        ResultSet rs2 = ps2.executeQuery();


        int currentDay = 0;
        double tempTotal = 0;
        int amountOfTimestamps = 0;

        while (rs.next()) {

            System.out.println("temp1: " + rs.getDouble(columnName));

            if (isInDays) {
                if (currentDay == 0) {
                    currentDay = rs.getTimestamp("date_time").getDay();
                }


                if (currentDay != rs.getTimestamp("date_time").getDay()) {
                    currentDay = rs.getTimestamp("date_time").getDay();
                    tempSeries.getData().add(new XYChart.Data(rs.getTimestamp("date_time").toString().split(" ")[0], tempTotal / amountOfTimestamps));
                    tempTotal = 0;
                    amountOfTimestamps = 0;
                    currentDay = 0;
                } else {

                    tempTotal += rs.getDouble(columnName);
                    amountOfTimestamps++;


                }
            } else {
                tempSeries.getData().add(new XYChart.Data(rs.getTimestamp("date_time").toString(), rs.getDouble(columnName)));
            }




        }

        while (rs2.next()) {

            System.out.println("temp1: " + rs2.getDouble(columnName2));

            if (isInDays) {
                if (currentDay == 0) {
                    currentDay = rs2.getTimestamp("date_time").getDay();
                }


                if (currentDay != rs2.getTimestamp("date_time").getDay()) {
                    currentDay = rs2.getTimestamp("date_time").getDay();
                    tempSeries2.getData().add(new XYChart.Data(rs2.getTimestamp("date_time").toString().split(" ")[0], tempTotal / amountOfTimestamps));
                    tempTotal = 0;
                    amountOfTimestamps = 0;
                } else {

                    tempTotal += rs2.getDouble(columnName2);
                    amountOfTimestamps++;


                }
            } else {
                tempSeries2.getData().add(new XYChart.Data(rs2.getTimestamp("date_time").toString(), rs2.getDouble(columnName2)));
            }
        }


        if (isInDays) {
            xAxis.setLabel("days");

        } else {
            xAxis.setLabel("dateTime");

        }


        LineChart lineChartWeather = new LineChart(xAxis, yAxis);
        lineChartWeather.getData().add(tempSeries);
        lineChartWeather.getData().add(tempSeries2);





        lineChartWeather.setCreateSymbols(false);
        lineChartWeather.setAnimated(true);


        return lineChartWeather;

    }


}
