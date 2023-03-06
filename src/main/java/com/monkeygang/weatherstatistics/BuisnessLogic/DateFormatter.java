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

    public static String intToDate(int day) {

        String result = "";

        switch (day) {
            case 1 -> result = "January";
            case 2 -> result = "February";
            case 3 -> result = "March";
            case 4 -> result = "April";
            case 5 -> result = "May";
            case 6 -> result = "June";
            case 7 -> result = "July";
            case 8 -> result = "August";
            case 9 -> result = "September";
            case 10 -> result = "October";
            case 11 -> result = "November";
            case 12 -> result = "December";

        }

        return result;


    }

    public static String intToMonth(int month){

        String result = "";

        switch (month) {
            case 1 -> result = "Monday";
            case 2 -> result = "Tuesday";
            case 3 -> result = "Wednesday";
            case 4 -> result = "Thursday";
            case 5 -> result = "Friday";
            case 6 -> result = "Saturday";
            case 7 -> result = "Sunday";

        }

        return result;

    }


}
