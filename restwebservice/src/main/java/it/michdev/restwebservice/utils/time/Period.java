package it.michdev.restwebservice.utils.time;

import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.michdev.restwebservice.exception.InvalidPeriodException;
import it.michdev.restwebservice.utils.parser.DateFormat;
import it.michdev.restwebservice.utils.parser.DateParser;

/**
 * La classe <b>Period</code> rappresenta un periodo storico, compreso tra due date ben distinte.
 * Implementa l'interfaccia <code>ITime</code>.
 * 
 * @version 0.6.0
 * @since 0.6.0
 * @author Michele Bevilacqua
 */
public class Period implements ITime {

    Calendar periodStartDate, periodEndDate;

    /**
     * Costruttore per la classe <code>Period</code>.
     * @param startDate data iniziale del periodo.
     * @param endDate data finale del periodo.
     * @throws InvalidPeriodException eccezione generata quando la data inziale è successiva a quella finale.
     */
    public Period(@JsonProperty("start_date") String startDate, @JsonProperty("end_date") String endDate)
            throws InvalidPeriodException {
        this.periodStartDate = DateParser.parseDate(startDate);
        this.periodEndDate = DateParser.parseDate(endDate);
    }

    /**
     * Restituisce la data iniziale del periodo.
     * @return <code>Calendar</code> della data iniziale.
     */
    @Override
    public Calendar getStartDate() {
        return this.periodStartDate;
    }

    /**
     * Restituisce la data iniziale del periodo come stringa.
     * @return data finale del periodo nel formato yyyy-MM-dd
     */
    @JsonProperty("start_date")
    @Override
    public String getStartDateAsString() {
        return DateParser.getDateAsString(periodStartDate, DateFormat.formatYYYYMMDD);
    }

    /**
     * Restituisce la data finale del periodo.
     * 
     * @return Calendar della data finale del periodo.
     */
    @Override
    public Calendar getEndDate() {
        return this.periodEndDate;
    }
    
    /**
     * Restituisce una stringa della data finale del periodo.
     * 
     * @return data nel formato yyyy-MM-dd
     */
    @JsonProperty("end_date")
    @Override
    public String getEndDateAsString() {
        return DateParser.getDateAsString(periodEndDate, "yyyy-MM-dd");
    }

    /**
     * Confronta l'ordine cronologico tra il periodo rappresentato dall'oggetto
     * stesso e la data passata come Calendar. Restitusce un numero intero relativo alla posizione cronologica:
     * <ul>
     * <li><code>-1</code> se data è precedente al periodo;</li>
     * <li> <code>0</code> se la data è inclusa nel periodo(inclusi estremi);</li> 
     * <li><code>1</code> se la data è successiva al periodo;</li>
     * </ul>
     * @param dateToCompare oggetto <code>Calendar</code> da confrontare.
     * @return valore intero rappresentante la posizione cronologica della data.
     * */
    @Override
    public Integer compareTo(Calendar dateToCompare) {
        if (dateToCompare.compareTo(periodStartDate) < 0)
            return -1;
        else if (dateToCompare.compareTo(periodEndDate) > 0)
            return 1;
        else
            return 0;
    }
}