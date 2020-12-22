package it.michdev.restwebservice.utils.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import it.michdev.restwebservice.exception.IllegalDatePatternException;

/**
 * La classe <code>DateParser</code> implementa metodi per convertire stringhe di date
 * in oggetti <code>Calendar</code> e viceversa.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.parser.DecimalParser
 * @see it.michdev.restwebservice.utils.parser.JsonParser
 */
public class DateParser {

    public static String YYYYMMDD = "yyyy-MM-dd";
    public static String YYYYMMDDHHmm = "yyyy-MM-dd-HH:mm";
    public static String YYYYMMDDHHmmss = "yyyy-MM-dd-HH:mm:ss";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat();

    /**
     * Converte una stringa di una data in oggetto <code>Calendar</code>
     * rappresentante la data stessa.
     * 
     * @param dateToParse stringa della data da convertire.
     * @return <code>Calendar</code> della data passata come argomento.
     * @throws IllegalDatePatternException eccezione generata in caso di date mal formattate
     */
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

    /**
     * Converte un oggetto <code>Calendar</code> in una <code>String</code> con il
     * formato specificato come argomento.
     * 
     * @param dateToString <code>Calendar</code> da convertire.
     * @param pattern      formato della data.
     * @return stringa della data convertita.
     */
    public static String getDateAsString(Calendar dateToString, String pattern) {
        dateFormat.applyPattern(pattern);
        return dateFormat.format(dateToString.getTime());
    }
}
