package org.pre.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateUtils {

    public  static LocalDate getLocalDatefromCalendar(Calendar calendar){
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MMM-dd");
        String date = formatDate.format(calendar.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");// Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        return LocalDate.parse(date, formatter);
    }

    public static DateTimeFormatter getCustomizedDateFormat() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public static DateTimeFormatter getCustomizedTimestampFormat() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    }
}
