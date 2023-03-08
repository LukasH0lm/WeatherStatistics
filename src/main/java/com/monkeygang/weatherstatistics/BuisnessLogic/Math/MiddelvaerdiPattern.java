package com.monkeygang.weatherstatistics.BuisnessLogic.Math;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;



public class MiddelvaerdiPattern implements MathPatternStrategy {
    @Override
    public double Calculate(XYChart.Series seriesToCalculate) {

        double sum = 0.0;
        int count = 0;

        ObservableList<XYChart.Data<String, Double>> data = seriesToCalculate.getData();


        for (XYChart.Data<String, Double> dataPoint : data) {

            double value = dataPoint.getYValue();

            sum += value;
            count++;
        }

        double average = (sum / count);

        return average;
    }
}
