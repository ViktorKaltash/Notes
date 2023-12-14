package com.notes.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilities {
    public static String getDate() {
        Date date = new Date();
        String tmp = (date.getYear()+1900) + "-" + (date.getMonth()+1) + "-" + (date.getDate());
        System.out.println(tmp);
        return tmp;
    }
}
