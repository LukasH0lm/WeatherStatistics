package com.monkeygang.weatherstatistics;


public class StatisticsAnalyzer {

    //possibly redundant class, java has built in methods for this,
    //although we can customize them to our needs

    static double[] weatherData = {1,2,3,5,10};


    public static int average() {
        int sum = 0;
        for (double weatherDatum : weatherData) {
            sum += weatherDatum;
        }
        return sum / weatherData.length;
    }

    public static double median(){
        double median = 0;
        double[] sortedData = weatherData;
        int n = sortedData.length;
        if (n % 2 == 0) {
            median = (sortedData[n / 2] + sortedData[n / 2 - 1]) / 2;
        } else {
            median = sortedData[n / 2];
        }
        return median;
    }

    public static double averageMiddle(){

        double[] sortedData = weatherData;
        double n = sortedData.length;
        double sum = 0;
        double lowerBound = n/2 - 1;
        double upperBound = n/2 + 1;
        for (int i = (int) lowerBound; i <= upperBound; i++) {
            sum += sortedData[i];
            System.out.println(sum);
        }
        return sum / 3;

    }




}
