package com.monkeygang.weatherstatistics.BuisnessLogic;

import java.sql.Date;
public class DateFormatter {





    public static Date formatDate(String date) {
        String[] dateArray = date.split(" ");
        int year = Integer.parseInt(dateArray[0]);
        String month = dateArray[1].toLowerCase();
        int day = Integer.parseInt(dateArray[2]);

        switch (month) {
            case "jan" -> month = "01";
            case "feb" -> month = "02";
            case "mar" -> month = "03";
            case "apr" -> month = "04";
            case "may" -> month = "05";
            case "jun" -> month = "06";
            case "jul" -> month = "07";
            case "aug" -> month = "08";
            case "sep" -> month = "09";
            case "okt" -> month = "10";
            case "nov" -> month = "11";
            case "dec" -> month = "12";
        }


        return new Date(year, Integer.parseInt(month), day); //this constructor is deprecated, but it works
    }
}
