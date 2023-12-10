package com.example.comp2000restaurantapp;

public class Date {

    public static String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    public static String makeJsonDateString(int year, int month, int day) {
        if (month < 10) {
            if (day < 10) {
                return year + "-0" + month + "-0" + day;
            }
            else {
                return year + "-0" + month + "-" + day;
            }
        }
        else if (day < 10) {
            return year + "-" + month + "-0" + day;
        }
        else {
            return year + "-" + month + "-" + day;
        }
    }

    public static String getMonthFormat(int month) {
        if (month == 1)
            return "January";
        if (month == 2)
            return "February";
        if (month == 3)
            return "March";
        if (month == 4)
            return "April";
        if (month == 5)
            return "May";
        if (month == 6)
            return "June";
        if (month == 7)
            return "July";
        if (month == 8)
            return "August";
        if (month == 9)
            return "September";
        if (month == 10)
            return "October";
        if (month == 11)
            return "November";
        if (month == 12)
            return "December";

        //default should never happen
        return "January";
    }
}
