package com.monkeygang.weatherstatistics.BuisnessLogic.Math;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiddelvaerdiTreMidterstePattern implements MathPatternStrategy {


    @Override
    public double Calculate(XYChart.Series seriesToCalculate) {

        double middelværdiTreEllerFjerdeMidterste = 0.0;
        double result = 0.0;

        ObservableList<XYChart.Data<String, Double>> data = seriesToCalculate.getData();
        List<Double> values = new ArrayList<>();

        for (XYChart.Data<String, Double> dataPoint : data) {
            values.add(dataPoint.getYValue());
        }

        Collections.sort(values);

        int size = values.size();


        // Har valgt de fire midterste, hvis det er et lige tal, og de 3 midterste hvis det er et ulige tal, eller ville det ikke give mening.

        if (size % 2 == 0) {
            while (values.size() > 4) {
                Collections.sort(values);
                double low = values.get(0);
                double high = values.get(values.size() - 1);
                values.remove(low);
                values.remove(high);
            }
            result = (values.get(0) + values.get(1) + values.get(2) + values.get(3))  / 4;
        }
        else {
            while (values.size() > 3) {
                Collections.sort(values);
                double low = values.get(0);
                double high = values.get(values.size() - 1);
                values.remove(low);
                values.remove(high);
            }
            result = (values.get(0) + values.get(1) + values.get(2))  / 3;
        }

        middelværdiTreEllerFjerdeMidterste = result;

        return middelværdiTreEllerFjerdeMidterste;
    }


}
