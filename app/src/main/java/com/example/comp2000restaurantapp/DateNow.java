package com.example.comp2000restaurantapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateNow {

    public static String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    public static String makeJsonDateString(int year, int month, int day) {
        if (month < 10) {
            if (day < 10) {
                return year + "-0" + month + "-0" + day;
            } else {
                return year + "-0" + month + "-" + day;
            }
        } else if (day < 10) {
            return year + "-" + month + "-0" + day;
        } else {
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

    public static long dateDifference(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formatDateTime = now.format(formatter);

        Date firstDate = sdf.parse(formatDateTime);
        Date secondDate = sdf.parse(date);

        assert secondDate != null;
        assert firstDate != null;
        long diffInSeconds = Math.abs(secondDate.getTime() - firstDate.getTime());

        return TimeUnit.DAYS.convert(diffInSeconds, TimeUnit.MILLISECONDS);
    }
}
