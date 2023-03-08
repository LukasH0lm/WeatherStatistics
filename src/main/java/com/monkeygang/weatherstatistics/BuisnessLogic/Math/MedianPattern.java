package com.monkeygang.weatherstatistics.BuisnessLogic.Math;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianPattern implements MathPatternStrategy{



    @Override
    public double Calculate(XYChart.Series seriesToCalculate) {

        double median = 0.0;
        double result = 0.0;

        ObservableList<XYChart.Data<String, Double>> data = seriesToCalculate.getData();
        List<Double> values = new ArrayList<>();

        for (XYChart.Data<String, Double> dataPoint : data) {
            values.add(dataPoint.getYValue());
        }

        Collections.sort(values);

        int size = values.size();

        if (size % 2 == 0) {
            while (values.size() > 2) {
                Collections.sort(values);
                double low = values.get(0);
                double high = values.get(values.size() - 1);
                values.remove(low);
                values.remove(high);
            }
            result = (values.get(0) + values.get(1))  / 2;
        }
        else {
            while (values.size() > 1) {
                Collections.sort(values);
                double low = values.get(0);
                double high = values.get(values.size() - 1);
                values.remove(low);
                values.remove(high);
            }
            result = values.get(0);
        }

        median = result;

        return median;
    }


}
