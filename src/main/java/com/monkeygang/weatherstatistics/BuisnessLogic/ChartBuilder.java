package com.monkeygang.weatherstatistics.BuisnessLogic;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.time.LocalDate;

public class ChartBuilder {


    public static BarChart buildBarChart(String data1, String data2, String station1, String station2, LocalDate startDate, LocalDate endDate) throws SQLException {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("value");

        BarChart barChart = new BarChart(xAxis, yAxis);

        barChart.setTitle("Weather statistics");

        XYChart.Series tempSeries = new XYChart.Series();
        tempSeries.setName(station1);

        XYChart.Series tempSeries2 = new XYChart.Series();
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
        }else{
            daysBetween = end.getDay() - start.getDay();

        }


        // add one day to start and end date to include the first and last day
        //there could be something wrong with this idk
        startDate = startDate.minusDays(1);
        endDate = endDate.plusDays(1);

        Timestamp startTimestamp = Timestamp.valueOf(startDate.atStartOfDay());
        Timestamp endTimestamp = Timestamp.valueOf(endDate.atTime(23, 59, 59));


        PreparedStatement ps = null;
        String columnName = "";
        //could use strategy pattern here

        if (data1.equals("Temperature")){
            ps = con.prepareStatement(
                    "SELECT WeatherStation.name, measurement.date_time, temperature_data.mid_temp " +
                            "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " +
                            "INNER JOIN temperature_data ON measurement.id = temperature_data.measurement_id " +
                            "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ");

            columnName = "mid_temp";


        }else if (data1.equals("Rain")){
            ps = con.prepareStatement(
                    "SELECT WeatherStation.name, measurement.date_time, rain_data.rain " +
                            "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " +
                            "INNER JOIN rain_data ON measurement.id = rain_data.measurement_id " +
                            "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ");

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

        if (data2.equals("Temperature")){
            ps2 = con.prepareStatement(
                    "SELECT WeatherStation.name, measurement.date_time, temperature_data.mid_temp " +
                            "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " +
                            "INNER JOIN temperature_data ON measurement.id = temperature_data.measurement_id " +
                            "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ");

            columnName2 = "mid_temp";

        }else if (data2.equals("Rain")){
            ps2 = con.prepareStatement(
                    "SELECT WeatherStation.name, measurement.date_time, rain_data.rain " +
                            "FROM measurement INNER JOIN WeatherStation ON measurement.station_id = WeatherStation.id " +
                            "INNER JOIN rain_data ON measurement.id = rain_data.measurement_id " +
                            "WHERE WeatherStation.name = ? AND measurement.date_time BETWEEN ? AND ? ");

            columnName2 = "rain";

        }


        assert ps2 != null;
        ps2.setString(1, station2);
        ps2.setTimestamp(2, startTimestamp);
        ps2.setTimestamp(3, endTimestamp);
        ResultSet rs2 = ps2.executeQuery();



        while(rs.next()) {

            System.out.println("temp1: " + rs.getDouble(columnName));

            tempSeries.getData().add(new XYChart.Data(rs.getTimestamp("date_time").toString(),rs.getDouble(columnName) ));


        }

        while(rs2.next()) {

            System.out.println("temp1: " + rs2.getDouble(columnName2));

            tempSeries2.getData().add(new XYChart.Data(rs2.getTimestamp("date_time").toString(),rs2.getDouble(columnName2) ));


        }

        BarChart barChartWeather = new BarChart(xAxis, yAxis);
        barChartWeather.getData().add(tempSeries);
        barChartWeather.getData().add(tempSeries2);



        barChartWeather.setAnimated(true);

        return barChartWeather;

    }


}
