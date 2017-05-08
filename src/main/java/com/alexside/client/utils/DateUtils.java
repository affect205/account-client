package com.alexside.client.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Created by Alex on 08.05.2017.
 */
public class DateUtils {
    private static ThreadLocal<DateTimeFormatter> DATETIME_FORMATTER;

    static {
        DATETIME_FORMATTER = new ThreadLocal<DateTimeFormatter>() {
            @Override protected DateTimeFormatter initialValue() {
                return ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
            }
        };
    }

    public static DateTimeFormatter dateTimeFormatter() {
        return DATETIME_FORMATTER.get();
    }

    public static String convertToDateTime(long millis) {
        return DATETIME_FORMATTER.get().format(Instant.ofEpochMilli(millis));
    }
}
