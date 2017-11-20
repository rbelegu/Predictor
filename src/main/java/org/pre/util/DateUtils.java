package org.pre.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateUtils {



    public static DateTimeFormatter getCustomizedDateFormat() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public static DateTimeFormatter getCustomizedTimestampFormat() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    }

    public static java.sql.Date convertLocalDateToSQLDate(LocalDate localDate){

        if( localDate == null){
            //add log and return;
            return null;
        }
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        return sqlDate;

    }

    public static LocalDate convertSQLDateToLocalDate(java.sql.Date sqlDate){

        if( sqlDate == null){
            //add log and return;
            return null;
        }
        LocalDate localDate = sqlDate.toLocalDate();
        return localDate;

    }
}
