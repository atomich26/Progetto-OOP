package it.michdev.restwebservice.utils.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import it.michdev.restwebservice.exception.IllegalDatePatternException;

public class DateParser {

    public static String YYYYMMDD = "yyyy-MM-dd";
    public static String YYYYMMDDHHmm = "yyyy-MM-dd-HH:mm";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat();

    public static Calendar parseDate(String dateToParse) throws IllegalDatePatternException {
        try {
            dateFormat.setLenient(false);
            dateFormat.applyPattern(YYYYMMDD);
            Calendar parsedDate = Calendar.getInstance();
            parsedDate.setTime(dateFormat.parse(dateToParse));
            parsedDate.set(Calendar.HOUR_OF_DAY, 0);
            parsedDate.set(Calendar.MINUTE, 0);
            parsedDate.set(Calendar.SECOND, 0);
            parsedDate.set(Calendar.MILLISECOND, 0);
            return parsedDate;
        } catch (ParseException e) {
            throw new IllegalDatePatternException(
                    "Formato della data {" + dateToParse + "} non valido. Formato accettato: {yyyy-MM-dd}.");
        }
    }

    public static String getDateAsString(Calendar dateToString, String pattern) {
        dateFormat.applyPattern(pattern);
        return dateFormat.format(dateToString.getTime());
    }
}
