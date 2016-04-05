package com.lpiotrko.reflection.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatSafety {

    private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>(){
        public static final String DATE_FORMAT = "yyyyMMdd";

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    public Date convertDate(String dateString) throws ParseException {
        return dateFormat.get().parse(dateString);
    }
}