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
        tempSeries.setName(data1);

        XYChart.Series tempSeries2 = new XYChart.Series();
        tempSeries.setName(data1);

        XYChart.Series rainSeries = new XYChart.Series();
        tempSeries.setName(data2);

        XYChart.Series rainSeries2 = new XYChart.Series();
        tempSeries2.setName(data2);

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
        startDate = startDate.minusDays(1);
        endDate = endDate.plusDays(1);

        Timestamp startTimestamp = Timestamp.valueOf(startDate.atStartOfDay());
        Timestamp endTimestamp = Timestamp.valueOf(endDate.atTime(23, 59, 59));

        //something about this chain of prepared statements is wrong
        //the final resultset only has one row

        PreparedStatement ps_stationid = con.prepareStatement("SELECT * FROM WeatherStation WHERE name = '" + station1 + "';");
        ResultSet rs_stationid = ps_stationid.executeQuery();

        System.out.println("station name: " + station1);
        rs_stationid.next();

        PreparedStatement ps_station1 = con.prepareStatement("SELECT * FROM measurement WHERE station_id = " + rs_stationid.getInt("id") + " AND date_time BETWEEN '" + startTimestamp + "' AND '" + endTimestamp + "' ;");
        ResultSet rs_measurement1 = ps_station1.executeQuery();
        rs_measurement1.next();

        PreparedStatement ps_temp1 = con.prepareStatement("SELECT * FROM temperature_data WHERE measurement_id = " + rs_measurement1.getInt("id") + ";");
        ResultSet rs_temp1 = ps_temp1.executeQuery();



        PreparedStatement ps_stationid2 = con.prepareStatement("SELECT * FROM WeatherStation WHERE name = '" + station2 + "';");
        ResultSet rs_stationid2 = ps_stationid2.executeQuery();

        rs_stationid2.next();


        PreparedStatement ps_station2 = con.prepareStatement("SELECT * FROM measurement WHERE station_id = " + rs_stationid2.getInt("id") + " AND date_time BETWEEN '" + startTimestamp + "' AND '" + endTimestamp + "' ;");
        ResultSet rs_measurement2 = ps_station2.executeQuery();

        rs_measurement2.next();

        PreparedStatement ps_temp2 = con.prepareStatement("SELECT * FROM temperature_data WHERE measurement_id = " + rs_measurement2.getInt("id") + ";");
        ResultSet rs_temp2 = ps_temp2.executeQuery();

        for (int i = 0; i < 1; i++) { //should be daysBetween
            rs_temp1.next();
            rs_temp2.next();

            System.out.println("temp1: " + rs_temp1.getDouble("mid_temp"));
            System.out.println("temp2: " + rs_temp2.getDouble("mid_temp"));

            tempSeries.getData().add(new XYChart.Data(startDate.plusDays(i).toString(),rs_temp1.getDouble("mid_temp") ));
            tempSeries2.getData().add(new XYChart.Data(startDate.plusDays(i).toString(), rs_temp2.getDouble("mid_temp")));

            /*
            rainSeries.getData().add(new XYChart.Data(startDate.plusDays(i).toString(), rainSeries));
            rainSeries2.getData().add(new XYChart.Data(startDate.plusDays(i).toString(), 20));
            */

        }

        BarChart barChartWeather = new BarChart(xAxis, yAxis);
        barChartWeather.getData().add(tempSeries);
        barChartWeather.getData().add(tempSeries2);




        return barChartWeather;

    }


}
