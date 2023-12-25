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

    public static int[] getRGBA(final String rgb) {
        final int[] ret = new int[4];
        for (int i = 0; i < 4; i++)
        {
            ret[i] = Integer.parseInt(rgb.substring(i * 2, i * 2 + 2), 16);
        }
        return ret;
    }
}
