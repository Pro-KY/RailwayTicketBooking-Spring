package com.proky.booking.util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;

@Component
public class SqlDateTimeConverter {
    private final static String timeSeparator = ":";
    private final static String dateSeparator = "-";
    private final static String whiteSpaceChar = " ";

    private SqlDateTimeConverter() {}

    public Date convertToSqlDate(String dateUI) {
        final String[] splitDate = dateUI.replace("/", whiteSpaceChar).split(whiteSpaceChar);
        String formatedDate = splitDate[2] + dateSeparator + splitDate[0] + dateSeparator + splitDate[1];
        return Date.valueOf(formatedDate);
    }

    public Time convertToSqlTime(String timeUI) {
        String formatedTime;

        String zeroSeconds = "00";
        int hours = 12;

        final String timeWithoutPM = timeUI.substring(0, timeUI.indexOf(" "));
        final String timePeriod = timeUI.substring(timeUI.indexOf(" ")).trim();

        if (timePeriod.equals("PM")) {
            final String[] splitTime = timeWithoutPM.split(timeSeparator);
            final int timePart = Integer.parseInt(splitTime[0]);
            final String minutesPart = splitTime[1];
            int PmHour = timePart + hours;
            formatedTime = PmHour + timeSeparator + minutesPart + timeSeparator + zeroSeconds;
        } else {
            formatedTime = timeWithoutPM + timeSeparator + zeroSeconds;
        }


        return Time.valueOf(formatedTime);
    }
}
