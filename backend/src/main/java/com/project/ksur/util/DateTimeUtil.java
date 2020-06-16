package com.project.ksur.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static Date getDate() {
        return new Date();
    }

    public static String getDateTimeAsString() {
        Date date = new Date();
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(date);
    }
}
