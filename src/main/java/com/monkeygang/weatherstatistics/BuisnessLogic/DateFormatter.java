package com.monkeygang.weatherstatistics.BuisnessLogic;

import java.sql.Date;
public class DateFormatter {





    public static Date formatDate(String date) {
        String[] dateArray = date.split(" ");
        int year = Integer.parseInt(dateArray[0]);
        String month = dateArray[1];
        int day = Integer.parseInt(dateArray[2]);

        switch (month) {
            case "Jan" -> month = "01";
            case "Feb" -> month = "02";
            case "Mar" -> month = "03";
            case "Apr" -> month = "04";
            case "May" -> month = "05";
            case "Jun" -> month = "06";
            case "Jul" -> month = "07";
            case "Aug" -> month = "08";
            case "Sep" -> month = "09";
            case "Oct" -> month = "10";
            case "Nov" -> month = "11";
            case "Dec" -> month = "12";
        }


        return new Date(year, Integer.parseInt(month), day); //this constructor is deprecated, but it works
    }
}
