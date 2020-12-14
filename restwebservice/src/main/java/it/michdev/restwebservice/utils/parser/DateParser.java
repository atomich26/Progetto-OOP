package it.michdev.restwebservice.utils.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import it.michdev.restwebservice.exception.InvalidPeriodException;

public class DateParser {
    
    public static SimpleDateFormat dateFormat = new SimpleDateFormat();

    public static Calendar parseDate(String dateToParse) throws InvalidPeriodException {
        try {
            //dateFormat.setLenient(false);
            dateFormat.applyPattern("yyyy-MM-dd");
            Calendar parsedDate = Calendar.getInstance();
            parsedDate.setTime(dateFormat.parse(dateToParse));
            return parsedDate;
        } catch (ParseException e) {
            throw new InvalidPeriodException(
                    "Formato della data {" + dateToParse + "} non valido. Il pattern corretto è {yyyy-MM-dd}.");
        }
    }

    public static String getDateAsString(Calendar dateToString, String pattern) {
        dateFormat.applyPattern(pattern);
        return dateFormat.format(dateToString.getTime());
    }
}
